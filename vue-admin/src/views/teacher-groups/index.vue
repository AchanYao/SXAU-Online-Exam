<template>
  <div class="">
    <el-row class="row-bg" justify="end" type="flex">
      <el-col :lg="8" :md="20">
        <el-input autosize placeholder="关键字搜索，支持课程组编号、课程名" prefix-icon="el-icon-search"
                  v-model="keyword"></el-input>
      </el-col>
      <el-col :lg="2" :md="3">
        <el-button :loading="downloadLoading" @click="handleSearch" icon="document" style='margin:0 0 10px 10px;'
                   type="primary">搜索
        </el-button>
      </el-col>
      <el-col :lg="2" :md="1" :sm="1">
        <el-button @click="_getTable" class="timer" icon="el-icon-refresh" type="text">重置搜索</el-button>
      </el-col>
    </el-row>

    <el-container>
      <el-table
        :data="tableData"
        style="width: 100%; margin: .5rem; min-height: 300px"
        v-loading="tableDataLoading">
        <el-table-column
          label="教师组编号"
          prop="number"
          min-width="150">
        </el-table-column>
        <el-table-column
          label="教授课程"
          min-width="150">
          <template slot-scope="scope">
            <el-tag>{{scope.row.courseNameZh}}</el-tag>
          </template>
        </el-table-column>
        <el-table-column
          label="负责教师"
          prop="principalName"
          min-width="150">
        </el-table-column>
        <el-table-column label="操作" min-width="150">
          <template slot-scope="scope">
            <el-button
              @click="updateTeacherGroup(scope.row.id)"
              round
              size="mini"
              type="success">修改
            </el-button>
            <el-button
              @click="confirmDelete(scope.row.id)"
              size="mini"
              type="danger">删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-container>
    <el-row justify="center" type="flex">
      <el-pagination
        :current-page.sync="currentPage"
        :disabled="tableDataLoading"
        :page-count="totalPage"
        :page-size="pageSize"
        layout="prev, pager, next">
      </el-pagination>
    </el-row>
  </div>
</template>

<script>
  import {deleteTeacherGroup, page} from '../../api/teacher-group'

  export default {
    data() {
      return {
        isDev: null,
        downloadLoading: false,
        tableDataLoading: false,
        tableData: [],
        filename: '',
        autoWidth: true,
        refresh: true,
        percentageNum: 100,
        timer: 0,
        updateTime: '',
        currentPage: 1,
        totalPage: 1,
        pageSize: 5,
        keyword: ''
      }
    },
    methods: {
      _getTable() {
        this.tableDataLoading = true
        page(this.currentPage, this.pageSize).then(resp => {
          this.tableData = resp.data.records
          this.totalPage = resp.data.pages
        }).finally(() => {
          this.tableDataLoading = false
        })
      },
      handleDelete(index, row) {
        this.$message({
          message: 'index: ' + index + ', id: ' + row.id,
          type: 'info'
        })
      },
      handleSearch() {
        if (this.keyword === null || this.keyword.trim().length === 0) {
          this._getTable()
          return
        }
        this.tableDataLoading = true
        this.downloadLoading = true
        page(this.currentPage, this.pageSize, this.keyword.trim()).then(result => {
          this.tableData = result.data.records
          this.totalPage = result.data.pages
        }).finally(() => {
          this.tableDataLoading = false
          this.downloadLoading = false
        })
      },
      confirmDelete(id) {
        this.$confirm('此操作将永久删除该课程组, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          deleteTeacherGroup(id).then(() => {
            this.$message({
              type: 'success',
              message: '删除成功!'
            })
            this._getTable()
          })
        }).catch(() => {
        })
      },
      updateTeacherGroup(id) {
        this.$router.push('/teacher-groups/update/' + id)
      }
    },
    mounted() {
    },
    watch: {
      'currentPage': {
        handler: function (newValue, oldValue) {
          this._getTable()
        },
        immediate: true
      }
    }
  }
</script>

<style lang="stylus" scoped>
  .fresh
    float right
    width 350px
    text-align right
    padding-right 20px
</style>
