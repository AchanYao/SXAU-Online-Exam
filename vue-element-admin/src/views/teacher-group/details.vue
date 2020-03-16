<template>
  <div class="components-container">
    <aside>drag-list base on
      <a href="https://github.com/SortableJS/Vue.Draggable" target="_blank">Vue.Draggable</a>
    </aside>
    <el-form
      ref="teacher-group-form"
      :model="teacherGroupForm.teacherGroupOverview"
      :rules="rules"
      @submit.native.prevent
    >
      <el-form-item label="课程组编号" prop="number">
        <el-input v-model="teacherGroupForm.teacherGroupOverview.number" />
      </el-form-item>
      <el-form-item label="课程组组长" prop="principalId">
        <el-select v-model="teacherGroupForm.teacherGroupOverview.principalId" class="filter-item" placeholder="Please select">
          <el-option v-for="item in teacherGroupForm.teachers" :key="item.id" :label="item.name" :value="item.id" />
        </el-select>
      </el-form-item>
      <el-form-item label="课程" prop="courseId">
        <el-select v-model="teacherGroupForm.teacherGroupOverview.courseId" class="filter-item" placeholder="Please select">
          <el-option v-for="item in courses" :key="item.id" :label="item.nameZh" :value="item.id" />
        </el-select>
      </el-form-item>
      <el-form-item class="editor-container" prop="teachers">
        <teacher-list :list1="teacherGroupForm.teachers" :list2="teachers" list1-title="教师组成员" list2-title="其余教师" />
      </el-form-item>
    </el-form>
    <el-row type="flex" justify="end">
      <el-button type="primary" @click="Number($route.params.id)?handleUpdate():handleCreate()">保存</el-button>
    </el-row>
  </div>
</template>

<script>
import TeacherList from './components/teacherList'
import { getAllWithOutGroup } from '../../api/courses'
import { allTeachers } from '../../api/users/teachers'
import { addGroup, getGroupById, updateGroup } from '../../api/teacher-group'

export default {
  name: 'TeacherGroupDetails',
  components: { TeacherList },
  data() {
    return {
      teacherGroupForm: {
        teacherGroupOverview: {
          number: '',
          principalId: '',
          principalName: '',
          courseId: '',
          courseName: '',
          courseNameZh: ''
        },
        teachers: []
      },
      // 基本信息验证规则
      rules: {
        number: [
          { required: true, message: '请输入编号', trigger: 'blur' },
          { min: 5, max: 20, message: '长度在 5 到 20 个字符', trigger: 'blur' }
        ],
        principalId: [
          { required: true, message: '请选择教师组组长', trigger: 'blur' }
        ],
        courseId: [
          { required: true, message: '请选择教授课程id', trigger: 'blur' }
        ],
        teachers: [
          { validator: this.checkTeachers, trigger: 'blur' }
        ]
      },
      courses: [],
      loading: false,
      teachers: []
    }
  },
  watch: {
    'teachers': {
      handler(value) {
        if (this.teacherGroupForm.teachers.map(t => {
          return t.id === this.teacherGroupForm.teacherGroupOverview.principalId
        }).length === 0) {
          this.teacherGroupForm.teacherGroupOverview.principalId = ''
        }
      },
      immediate: true
    }
  },
  created() {
    this.getData()
  },
  methods: {
    checkTeachers(rule, value, callback) {
      if (this.teacherGroupForm.teachers && this.teacherGroupForm.teachers.length !== 0) {
        callback()
      } else {
        callback(new Error('请选择教师组教师'))
      }
    },
    async getData() {
      this.listLoading = true
      const groupId = this.$route.params.id
      if (Number(groupId)) {
        // id存在为修改
        await getGroupById(groupId).then((result) => {
          this.teacherGroupForm = result.data
        }).then(() => {
          this.courses = [{
            id: this.teacherGroupForm.teacherGroupOverview.courseId,
            name: this.teacherGroupForm.teacherGroupOverview.courseName,
            nameZh: this.teacherGroupForm.teacherGroupOverview.courseNameZh
          }]
        })
      } else {
        // 不存在为创建
        this.getCoursesWithoutGroup()
      }
      allTeachers().then((result) => {
        this.teachers = result.data.map(teacher => {
          if (!this.teacherGroupForm.teachers.includes(teacher)) {
            return teacher
          }
        })
      })
    },
    getCoursesWithoutGroup() {
      getAllWithOutGroup().then((result) => {
        this.courses = result.data
      })
    },
    handleCreate() {
      this.$refs['teacher-group-form'].validate((valid) => {
        if (valid) {
          if (!Number(this.$route.params.id)) {
            this.$confirm('创建新的课程组?', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              addGroup({
                teacherGroupOverview: {
                  id: this.$route.params.id,
                  number: this.teacherGroupForm.teacherGroupOverview.number,
                  courseId: this.teacherGroupForm.teacherGroupOverview.courseId,
                  principalId: this.teacherGroupForm.teacherGroupOverview.principalId
                }, teachers: this.teacherGroupForm.teachers }).then((result) => {
                if (result.data) {
                  this.$notify({
                    title: 'Success',
                    message: 'Created Successfully',
                    type: 'success',
                    duration: 2000
                  })
                  this.$router.push('/teacher-groups/all')
                } else {
                  this.$message.warning('操作失败')
                }
              })
            })
              .catch()
          }
        } else {
          this.$message.warning('非法表单')
        }
      })
    },
    handleUpdate() {
      this.$refs['teacher-group-form'].validate((valid) => {
        if (valid) {
          updateGroup({
            teacherGroupOverview: {
              id: this.$route.params.id,
              number: this.teacherGroupForm.teacherGroupOverview.number,
              courseId: this.teacherGroupForm.teacherGroupOverview.courseId,
              principalId: this.teacherGroupForm.teacherGroupOverview.principalId
            }, teachers: this.teacherGroupForm.teachers }).then((result) => {
            if (result.data) {
              this.$notify({
                title: 'Success',
                message: 'Created Successfully',
                type: 'success',
                duration: 2000
              })
              this.$router.push('/teacher-groups/all')
            } else {
              this.$message.warning('操作失败')
            }
          })
        } else {
          this.$message.warning('非法表单')
        }
      })
    }
  }
}
</script>

