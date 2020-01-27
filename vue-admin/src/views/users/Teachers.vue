<template>
  <div class="">
    <el-row class="row-bg" justify="end" type="flex">
      <el-col :lg="8" :md="20">
        <el-input autosize placeholder="关键字搜索" prefix-icon="el-icon-search"
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
          label="教师号"
          prop="number"
          width="150">
        </el-table-column>
        <el-table-column
          label="姓名"
          prop="name"
          width="120">
        </el-table-column>
        <el-table-column
          label="身份证号"
          min-width="170"
          prop="idNumber"
          width="200">
          <template slot-scope="scope">{{scope.row.email.length === 0 ? '该用户没有填写身份证号' : scope.row.email.length}}
          </template>
        </el-table-column>
        <el-table-column
          label="邮箱"
          prop="email"
          width="170">
          <template slot-scope="scope">{{scope.row.email.length === 0 ? '该用户没有设置邮箱' : scope.row.email.length}}
          </template>
        </el-table-column>
        <el-table-column
          label="联系电话"
          min-width="150"
          prop="phone" width="200">
          <template slot-scope="scope">{{scope.row.phone.length === 0 ? '该用户没有设置联系电话' : scope.row.phone.length}}
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button size="mini">重置密码
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
  import {page, pageWithKeyword, deleteTeacher} from '../../api/teachers'

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
      handleDownload() {
        this.downloadLoading = true
        import('@/vendor/Export2Excel').then(excel => {
          const tHeader = ['影片', '上映天数', '累计票房', '实时票房', '票房占比', '排片占比', '上座率', '排座占比', '场次', '人次', '场均人次', '场均收入', '平均票价']
          const filterVal = ['影片', '上映天数', '累计票房', '实时票房', '票房占比', '排片占比', '上座率', '排座占比', '场次', '人次', '场均人次', '场均收入', '平均票价']
          const list = this.tableData
          const data = this.formatJson(filterVal, list)
          excel.export_json_to_excel({
            header: tHeader,
            data,
            filename: this.filename || 'excel-list',
            autoWidth: this.autoWidth
          })
          this.downloadLoading = false
        })
      },
      formatJson(filterVal, jsonData) {
        return jsonData.map(v => filterVal.map(j => {
          if (j === '影片') {
            return v['movieName']
          } else {
            return v[j]
          }
        }))
      },
      formatMovieData(srcData) {
        srcData.forEach(item => {
          Object.values(item.attribute).forEach((v, i, t) => {
            // console.log(v)
            item[v.attrName] = v.attrValue
          })
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
        pageWithKeyword(this.currentPage, this.pageSize, this.keyword.trim()).then(result => {
          this.tableData = result.data.records
          this.totalPage = result.data.pages
        }).finally(() => {
          this.tableDataLoading = false
          this.downloadLoading = false
        })
      },
      confirmDelete(id) {
        this.$confirm('此操作将永久删除该教师, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          deleteTeacher(id).then(() => {
            this.$message({
              type: 'success',
              message: '删除成功!'
            })
            this._getTable()
          })
        }).catch(() => {
        })
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
