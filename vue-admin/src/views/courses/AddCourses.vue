<template>
  <div class="components-container">
    <div class="container">
      <el-tabs @tab-click="handleClick" type="card" v-model="activeName">
        <!-- 基本信息 -->
        <el-tab-pane label="新增课程" name="first">
          <h3 class="title">新增课程</h3>
          <!-- 基本信息-表单内容 -->
          <el-form :model="courseForm" :rules="rules" class="basic-form" label-width="120px" ref="courseForm">
            <el-form-item label="课程编号" prop="number">
              <el-input placeholder="课程编号" v-model="courseForm.number"></el-input>
            </el-form-item>
            <el-form-item label="课程名（英）" prop="name">
              <el-input placeholder="Course Name" v-model="courseForm.name"></el-input>
            </el-form-item>
            <el-form-item label="课程名（中）" prop="nameZh">
              <el-input placeholder="课程名" v-model="courseForm.nameZh"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button @click="submitForm('courseForm')" type="primary">保存</el-button>
              <el-button @click="cancel">取消</el-button>
            </el-form-item>
          </el-form>
        </el-tab-pane>
      </el-tabs>
    </div>
  </div>
</template>

<script>
  import {addCourse} from '../../api/courses'

  export default {
    data() {
      return {
        activeName: 'first',
        courseForm: {
          number: '',
          name: '',
          nameZh: ''
        },
        // 基本信息验证规则
        rules: {
          number: [
            {required: true, message: '请输入课程编号', trigger: 'blur'},
            {min: 3, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur'}
          ],
          name: [
            {required: true, message: '请输入课程名称（英）', trigger: 'blur'},
            {min: 1, max: 40, message: '长度在 1 到 40 个字符', trigger: 'blur'}
          ],
          nameZh: [
            {required: true, message: '请输入课程名称（中）', trigger: 'blur'},
            {min: 1, max: 40, message: '长度在 1 到 20 个字符', trigger: 'blur'}
          ]
        },
        loading: false
      }
    },
    methods: {
      // 信息提交
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            addCourse(this.courseForm).then(result => {
              if (result.data) {
                this.$message.success('操作成功')
                this.cancel()
              }
            })
          } else {
            return false
          }
        })
      },
      // 重置
      cancel() {
        this.$refs['courseForm'].resetFields()
      },
      // tab切换点击事件
      handleClick(tab, e) {
        if (tab.name === 'second') {
          // nothing
        }
      }
    },
    created() {
    },
    mounted() {
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
