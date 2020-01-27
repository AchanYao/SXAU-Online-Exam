<template>
  <div class="">
    <el-row class="row-bg" justify="start" type="flex">
      <el-col :lg="8" :md="20">
        <el-input autosize placeholder="关键字搜索，只支持学号班级姓名" prefix-icon="el-icon-search"
                  v-model="keyword"></el-input>
      </el-col>
      <el-col :lg="2" :md="2">
        <el-button :loading="downloadLoading" @click="handleSearch" icon="document" style='margin:0 0 10px 10px;'
                   type="primary">搜索
        </el-button>
      </el-col>
      <el-col :lg="2" :md="1" :sm="1">
        <el-button @click="_getTable" class="timer" icon="el-icon-refresh" type="text">重置搜索</el-button>
      </el-col>
      <el-col :lg="2" :md="1" :offset="8" :sm="1">
        <el-button @click="addClasses" class="timer" icon="el-icon-plus" round type="success">新增班级</el-button>
      </el-col>
    </el-row>

    <el-container>
      <el-table
        :data="tableData"
        style="margin: .5rem;"
        v-loading="tableDataLoading">
        <el-table-column
          label="id"
          prop="id"
          width="120">
        </el-table-column>
        <el-table-column
          label="班级"
          width="150">
          <template slot-scope="scope">
            <el-input @input="scope.row.changed = true" v-model.trim="scope.row.name"></el-input>
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button
              @click="confirmDelete(scope.row.id)"
              size="mini"
              type="danger">删除
            </el-button>
            <el-button :disabled="!scope.row.changed" @click="confirmChange(scope.row)" size="mini" type="success">
              保存修改
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-container>
  </div>
</template>

<script>
  import {addClass, searchClasses, deleteClass, updateClass} from '../../api/classes'

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
        searchClasses(this.keyword).then(resp => {
          this.tableData = resp.data
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
        searchClasses(this.keyword.trim()).then(result => {
          this.tableData = result.data
        }).finally(() => {
          this.tableDataLoading = false
          this.downloadLoading = false
        })
      },
      confirmDelete(id) {
        this.$confirm('删除班级可能会导致无法预料的问题, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          deleteClass(id).then(() => {
            this.$message({
              type: 'success',
              message: '删除成功!'
            })
            this._getTable()
          })
        })
      },
      confirmChange(data) {
        this.$confirm('是否修改该班级?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'info'
        }).then(() => {
          updateClass(data).then(() => {
            this.$message({
              type: 'success',
              message: '修改成功!'
            })
            this._getTable()
          })
        })
      },
      addClasses() {
        this.$prompt('请输入班级名称', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          inputErrorMessage: '输入格式不正确',
          inputPattern: /\S*/
        }).then(({value}) => {
          if (this.classNameValidator(value)) {
            addClass(value).then(() => {
              this.$message.success('操作成功')
              this._getTable()
            })
          } else {
            this.$message.warning('非法输入')
          }
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '取消输入'
          })
        })
      },
      classNameValidator(name) {
        return name !== undefined && name !== null && name.length >= 4
      }
    },
    mounted() {
      this._getTable()
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
