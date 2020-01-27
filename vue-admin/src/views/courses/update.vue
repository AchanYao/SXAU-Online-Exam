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
  import {updateCourse, getCourse} from '../../api/courses'
  import {mapActions, mapGetters} from 'vuex'

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
    computed: {
      ...mapGetters([
        'visitedTabsView'
      ])
    },
    methods: {
      ...mapActions([
        'delVisitedTabsView'
      ]),
      // 信息提交
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            updateCourse(this.courseForm).then(result => {
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
        this.init()
      },
      // tab切换点击事件
      handleClick(tab, e) {
        if (tab.name === 'second') {
          // nothing
        }
      },
      generateRoute() {
        if (this.$route.name) {
          return this.$route
        }
        return false
      },
      isActive(route) {
        return route.path === this.$route.path || route.name === this.$route.name
      },
      closeThisTabView() {
        let tag = this.generateRoute()
        this.delVisitedTabsView(tag).then((tags) => {
          // 如果关闭的是当前显示的页面，就去到前一个 tab-view 页面
          if (this.isActive(tag)) {
            const lastTag = tags.slice(-1)[0]
            // 前一个 tab-view 页面存在，就跳；不存在就到首页
            if (lastTag) {
              this.$router.push(lastTag.path)
            } else {
              this.$router.push('/')
            }
          }
        })
      },
      init() {
        let id = this.$route.params.id
        if (!Number(id)) {
          this.$message.error('id应为数字')
          this.closeThisTabView()
          return
        }
        getCourse(id).then(result => {
          if (result.data === null) {
            this.$message.error('查询数据失败')
            this.closeThisTabView()
          }
          this.courseForm = result.data
        }).catch(() => {
          this.$message.error('查询数据失败')
          this.closeThisTabView()
        })
      }
    },
    created() {
    },
    mounted() {
      this.init()
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
