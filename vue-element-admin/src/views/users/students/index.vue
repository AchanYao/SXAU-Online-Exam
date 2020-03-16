<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input
        v-model="listQuery.keyword"
        class="filter-item"
        placeholder="Search"
        style="width: 200px;"
        @keyup.enter.native="handleFilter"
      />
      <el-button v-waves class="filter-item" icon="el-icon-search" type="primary" @click="handleFilter">
        查找
      </el-button>
      <el-button
        class="filter-item"
        icon="el-icon-edit"
        style="margin-left: 10px;"
        type="primary"
        @click="handleCreate"
      >
        增加
      </el-button>
    </div>

    <el-table
      :key="tableKey"
      v-loading="listLoading"
      :data="list"
      border
      fit
      highlight-current-row
      style="width: 100%;"
    >
      <el-table-column sortable align="center" label="ID" prop="id" width="80">
        <template slot-scope="{row}">
          <span>{{ row.id }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="修改日期" width="150px">
        <template slot-scope="{row}">
          <span>{{ row.modifyTime | parseTime('{y}-{m}-{d} {h}:{i}') }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="姓名" width="110px">
        <template slot-scope="{row}">
          <span>{{ row.name }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="学号" width="110px">
        <template slot-scope="{row}">
          <span>{{ row.number }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Email" min-width="150px">
        <template slot-scope="{row}">
          <span class="link-type" @click="handleUpdate(row)">{{ row.email }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="班级" width="110px">
        <template slot-scope="{row}">
          <span>{{ row.clazzName }}</span>
        </template>
      </el-table-column>
      <el-table-column class-name="status-col" label="Status" width="100">
        <template slot-scope="{row}">
          <el-tag :type="row.enable | statusFilter">
            {{ row.enable == 1 ? 'enable' : 'disabled' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column align="center" class-name="small-padding fixed-width" label="Actions" width="230">
        <template slot-scope="{row,$index}">
          <el-button size="mini" type="primary" @click="handleUpdate(row)">
            Edit
          </el-button>
          <el-button v-if="row.enable!='1'" size="mini" type="success" @click="handleModifyStatus(row,'1')">
            Enable
          </el-button>
          <el-button v-if="row.enable!='0'" size="mini" @click="handleModifyStatus(row,'0')">
            Locked
          </el-button>
          <el-button v-if="row.status!='deleted'" size="mini" type="danger" @click="handleDelete(row,$index)">
            Delete
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :limit.sync="listQuery.size"
      :page.sync="listQuery.page"
      :total="total"
      @pagination="getList"
    />

    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form
        ref="dataForm"
        :model="temp"
        :rules="rules"
        label-position="left"
        label-width="70px"
        style="width: 400px; margin-left:50px;"
        @submit.native.prevent
        @keyup.enter.native="dialogStatus==='create'?createData():updateData()"
      >
        <el-form-item label="姓名" prop="name">
          <el-input v-model="temp.name" />
        </el-form-item>
        <el-form-item label="学号" prop="number">
          <el-input v-model="temp.number" />
        </el-form-item>
        <el-form-item label="Email" prop="email">
          <el-input v-model="temp.email" />
        </el-form-item>
        <el-form-item label="身份证" prop="idNumber">
          <el-input v-model="temp.idNumber" />
        </el-form-item>
        <el-form-item label="班级" prop="clazz">
          <el-select v-model="temp.clazzId" class="filter-item" placeholder="Please select">
            <el-option v-for="item in allClasses" :key="item.id" :label="item.name" :value="item.id" />
          </el-select>
        </el-form-item>
        <el-form-item label="联系电话">
          <el-input v-model="temp.phone" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">
          Cancel
        </el-button>
        <el-button type="primary" @click="dialogStatus==='create'?createData():updateData()">
          Confirm
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import waves from '@/directive/waves' // waves directive
import { parseTime } from '@/utils'
import Pagination from '@/components/Pagination' // secondary package based on el-pagination
import { addStudent, deleteStudentById, pageStudents, update } from '../../../api/users/students'
import { changeStatus } from '../../../api/user'
import { allClasses } from '../../../api/classes'

export default {
  name: 'StudentsTable',
  components: { Pagination },
  directives: { waves },
  filters: {
    statusFilter(status) {
      const statusMap = {
        '1': 'success',
        '0': 'danger'
      }
      return statusMap[status]
    }
  },
  data() {
    return {
      tableKey: 0,
      list: null,
      total: 0,
      listLoading: true,
      listQuery: {
        page: 1,
        size: 10,
        keyword: undefined
      },
      statusOptions: ['published', 'draft', 'deleted'],
      allClasses: [],
      temp: {
        id: undefined,
        number: '',
        phone: '',
        clazzId: undefined,
        email: '',
        idNumber: ''
      },
      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: 'Edit',
        create: 'Create'
      },
      rules: {
        email: [
          { required: true, message: 'email is required', trigger: 'blur' },
          { validator: this.checkEmail, trigger: 'blur' }
        ],
        idNumber: [{ required: true, message: 'id-number is required', trigger: 'blur' }],
        phone: [{ required: true, message: 'phone is required', trigger: 'blur' }],
        clazz: [{ type: 'number', validator: this.checkClazz, trigger: 'change' }],
        name: [{ required: true, message: 'name is required', trigger: 'blur' }, { min: 2, max: 5, message: '长度在2-5个字符', trigger: 'blur' }],
        number: [{ required: true, message: 'number is required', trigger: 'blur' }, { min: 11, max: 11, message: '长度应为11个字符', trigger: 'blur' }]
      },
      downloadLoading: false
    }
  },
  created() {
    this.getList()
    this.getAllClasses()
  },
  methods: {
    checkClazz(rule, value, callback) {
      if (Number(this.temp.clazzId)) {
        callback()
      } else {
        callback(new Error('class is required'))
      }
    },
    checkEmail(rule, value, callback) {
      if (/^[\w!#$%&'*+/=?`{|}~^-]+(?:\.[\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\.)+[a-zA-Z]{2,8}$/.test(this.temp.email)) {
        callback()
      } else {
        callback(new Error('请输入合法的email'))
      }
    },
    getList() {
      this.listLoading = true
      pageStudents(this.listQuery).then(response => {
        this.list = response.data.records
        this.total = response.data.total

        // Just to simulate the time of the request
        setTimeout(() => {
          this.listLoading = false
        }, 1.5 * 1000)
      })
    },
    handleFilter() {
      this.listQuery.page = 1
      this.getList()
    },
    handleModifyStatus(row, status) {
      changeStatus(row.userId).then((result) => {
        if (result.data === true) {
          this.$message({
            message: '操作Success',
            type: 'success'
          })
          row.enable = Number(status)
        } else {
          this.$message({
            message: '操作失败',
            type: 'warning'
          })
        }
      }).catch((error) => {
        this.$message({
          message: '操作失败',
          type: 'warning'
        })
        console.log(error)
      })
    },
    sortChange(data) {
      const { prop, order } = data
      if (prop === 'id') {
        this.sortByID(order)
      }
    },
    sortByID(order) {
      if (order === 'ascending') {
        this.listQuery.sort = '+id'
      } else {
        this.listQuery.sort = '-id'
      }
      this.handleFilter()
    },
    resetTemp() {
      this.temp = {
        id: undefined,
        number: '',
        phone: '',
        clazzId: undefined,
        email: '',
        idNumber: ''
      }
    },
    handleCreate() {
      this.resetTemp()
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    createData() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          addStudent(this.temp).then((result) => {
            result.data.enable = 1
            result.data.clazzName = this.allClasses.map((value) => {
              return value.id === result.data.clazzId
            })[0].name
            this.list.unshift(result.data)
            this.dialogFormVisible = false
            this.$notify({
              title: 'Success',
              message: 'Created Successfully',
              type: 'success',
              duration: 2000
            })
          })
        }
      })
    },
    handleUpdate(row) {
      this.temp = Object.assign({}, row) // copy obj
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    updateData() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          const tempData = Object.assign({}, this.temp)
          update(tempData).then(() => {
            const index = this.list.findIndex(v => v.id === this.temp.id)
            this.list.splice(index, 1, this.temp)
            this.dialogFormVisible = false
            this.$notify({
              title: 'Success',
              message: 'Update Successfully',
              type: 'success',
              duration: 2000
            })
          })
        }
      })
    },
    handleDelete(row, index) {
      this.$confirm('此操作将永久删除该用户信息, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteStudentById(row.id).then(() => {
          this.$notify({
            title: 'Success',
            message: 'Delete Successfully',
            type: 'success',
            duration: 2000
          })
          this.list.splice(index, 1)
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除',
          duration: 2000
        })
      })
    },
    handleDownload() {
      this.downloadLoading = true
        import('@/vendor/Export2Excel').then(excel => {
          const tHeader = ['timestamp', 'title', 'type', 'importance', 'status']
          const filterVal = ['timestamp', 'title', 'type', 'importance', 'status']
          const data = this.formatJson(filterVal)
          excel.export_json_to_excel({
            header: tHeader,
            data,
            filename: 'table-list'
          })
          this.downloadLoading = false
        })
    },
    formatJson(filterVal) {
      return this.list.map(v => filterVal.map(j => {
        if (j === 'timestamp') {
          return parseTime(v[j])
        } else {
          return v[j]
        }
      }))
    },
    getAllClasses() {
      allClasses().then((result) => {
        this.allClasses = result.data
      })
    }
  }
}
</script>
