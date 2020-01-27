<template>
  <div class="components-container">
    <div class="container">
      <el-card class="box-card">
        <div class="clearfix" slot="header">
          <h3>新增课程组</h3>
        </div>
        <el-form :model="teacherGroupForm" :rules="rules" class="basic-form" label-width="120px" ref="teacherGroupForm">
          <el-form-item label="教授课程" prop="courseId">
            <el-select filterable placeholder="教授课程" v-model="teacherGroupForm.courseId">
              <el-option
                :key="item.id"
                :label="item.nameZh"
                :value="item.id"
                v-for="item in courses">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="课程组编号" prop="number">
            <el-input placeholder="课程组编号" v-model="teacherGroupForm.number"></el-input>
          </el-form-item>
          <el-form-item label="成员选择" prop="teachers">
            <template>
              <el-transfer
                style="min-width: 700px"
                :data="teachers"
                :props="{
                  key: 'id',
                  label: 'name'
                }"
                :titles="['未在组内', '组内成员']"
                filter-placeholder="请输入教师姓名"
                filterable
                v-model="teacherGroupForm.teachers">
              </el-transfer>
            </template>
          </el-form-item>
          <el-form-item label="课程组组长" prop="principalId">
            <el-select filterable placeholder="请选择" v-model="teacherGroupForm.principalId">
              <el-option
                :key="item"
                :label="getLocalTeacherById(item).name"
                :value="getLocalTeacherById(item).id"
                v-for="item in teacherGroupForm.teachers">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button @click="submitForm('teacherGroupForm')" type="primary">保存</el-button>
            <el-button @click="cancel">取消</el-button>
          </el-form-item>
        </el-form>
      </el-card>
    </div>
  </div>
</template>

<script>
  import {noTeacherGroupCourses} from '../../api/courses'
  import {listTeachers} from '../../api/teachers'
  import {newGroup} from '../../api/teacher-group'

  let _ = require('lodash')

  export default {
    data() {
      return {
        teacherGroupForm: {
          number: '',
          principalId: '',
          courseId: '',
          teachers: []
        },
        // 基本信息验证规则
        rules: {
          number: [
            {required: true, message: '请输入用户编号', trigger: 'blur'},
            {min: 5, max: 20, message: '长度在 5 到 20 个字符', trigger: 'blur'}
          ],
          principalId: [
            {required: true, message: '请选择教师组组长', trigger: 'blur'}
          ],
          courseId: [
            {required: true, message: '请选择教授课程id', trigger: 'blur'}
          ],
          teachers: [
            {required: true, message: '请选择教师组成员', trigger: 'blur'}
          ]
        },
        courses: [],
        loading: false,
        teachers: []
      }
    },
    methods: {
      // 信息提交
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            let requestData = {
              teacherGroupOverview: {
                number: this.teacherGroupForm.number,
                principalId: this.teacherGroupForm.principalId,
                courseId: this.teacherGroupForm.courseId
              },
              teachers: _.map(this.teacherGroupForm.teachers, this.getLocalTeacherById)
            }
            newGroup(requestData).then(result => {
              this.$message.success('操作成功')
              this.cancel()
            })
          } else {
            return false
          }
        })
      },
      // 重置
      cancel() {
        this.$refs['teacherGroupForm'].resetFields()
      },
      getLocalTeacherById(id) {
        return _.filter(this.teachers, function (teacher) {
          return teacher.id === id
        })[0]
      }
    },
    created() {
    },
    mounted() {
      noTeacherGroupCourses().then(result => {
        this.courses = result.data
      })
      listTeachers().then(result => {
        this.teachers = result.data
      })
    },
    watch: {}
  }
</script>
<style lang="stylus" scoped>
  .basic-form
    width 500px

  .sys-mark-wrapper
    .input-two
      position absolute
      top 0
      right -110px
      width 100px
</style>
