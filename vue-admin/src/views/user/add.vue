<template>
  <div class="components-container">
    <div class="container">
      <el-tabs @tab-click="handleClick" type="card" v-model="activeName">
        <!-- 基本信息 -->
        <el-tab-pane label="新增用户" name="first">
          <h3 class="title">新增用户</h3>
          <!-- 基本信息-表单内容 -->
          <el-form :model="userForm" :rules="rules" class="basic-form" label-width="100px" ref="userForm">
            <el-form-item label="用户名" prop="username">
              <el-input placeholder="教师号或学生学号" v-model="userForm.username"></el-input>
            </el-form-item>
            <el-form-item label="姓名" prop="name">
              <el-input placeholder="用户真实姓名" v-model="userForm.name"></el-input>
            </el-form-item>
            <el-form-item label="用户角色" prop="roleId">
              <el-select placeholder="用户角色" v-model="userForm.roleId">
                <el-option
                  :disabled="item.id > 2"
                  :key="item.id"
                  :label="item.nameZh"
                  :value="item.id"
                  v-for="item in roles">
                </el-option>
              </el-select>
            </el-form-item>
            <el-collapse-transition>
              <el-form-item label="学生班级" prop="classId" v-show="userForm.roleId === 1">
                <el-select :loading="loading" :remote-method="searchClass" filterable placeholder="学生班级" remote
                           v-model="userForm.classId">
                  <el-option
                    :key="item.id"
                    :label="item.name"
                    :value="item.id"
                    v-for="item in classes">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-collapse-transition>

            <h4 class="title">用户详细信息</h4>
            <div class="content">
              <el-form-item label="身份证号" prop="idNumber">
                <el-input v-model="userForm.idNumber"></el-input>
              </el-form-item>
              <el-form-item label="联系电话" prop="phone">
                <el-input v-model="userForm.phone"></el-input>
              </el-form-item>
              <el-form-item label="电子邮箱" prop="email">
                <el-input v-model="userForm.email"></el-input>
              </el-form-item>
            </div>

            <el-form-item>
              <el-button @click="submitForm('userForm')" type="primary">保存</el-button>
              <el-button @click="cancel">取消</el-button>
            </el-form-item>
          </el-form>
        </el-tab-pane>
        <!--
                &lt;!&ndash; 部门管理 &ndash;&gt;
                <el-tab-pane label="部门管理" name="second">
                  &lt;!&ndash; 左侧树形 &ndash;&gt;
                  <el-col :span="6">
                    <el-tree :data="departData" :props="defaultProps"></el-tree>
                  </el-col>
                  <el-col :span="1">
                  </el-col>
                  &lt;!&ndash; 右侧内容 &ndash;&gt;
                  <el-col :span="16">
                    <div>
                      <el-select v-model="value" placeholder="部门类型选择">
                        <el-option
                          v-for="item in departOptions"
                          :key="item.value"
                          :label="item.label"
                          :value="item.value">
                        </el-option>
                      </el-select>
                      <el-input v-model="content" width="auto" placeholder="请输入内容"></el-input>
                    </div>
                  </el-col>
                </el-tab-pane>
        -->
      </el-tabs>
    </div>
  </div>
</template>

<script>
  import {addStudent, addTeacher, getRoles} from '../../api/users'
  import {searchClasses} from '../../api/classes'

  export default {
    data() {
      return {
        activeName: 'first',
        userForm: {
          username: '',
          name: '',
          roleId: '',
          classId: '',
          idNumber: '',
          phone: '',
          email: ''
        },
        // 基本信息验证规则
        rules: {
          username: [
            {required: true, message: '请输入用户编号', trigger: 'blur'},
            {min: 5, max: 20, message: '长度在 5 到 20 个字符', trigger: 'blur'}
          ],
          name: [
            {required: true, message: '请输入用户名称', trigger: 'blur'},
            {min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur'}
          ],
          roleId: [
            {required: true, message: '请选择用户角色', trigger: 'blur'}
          ],
          classId: [
            {required: false, message: '请选择用户班级', trigger: 'blur'}
          ],
          idNumber: [
            {min: 16, max: 18, message: '长度在 16 到 18 个字符', trigger: 'blur'}
          ],
          phone: [
            {min: 6, max: 11, message: '长度在 6 到 11 个字符', trigger: 'blur'}
          ],
          email: [
            {min: 5, message: '长度至少5个字符', trigger: 'blur'}
          ]
        },
        roles: [],
        classes: [],
        loading: false
      }
    },
    methods: {
      // 信息提交
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            let params = null
            if (this.userForm.roleId === 1) {
              if (!Number(this.userForm.classId)) {
                return
              }
              // 学生
              params = {
                student: {
                  number: this.userForm.username,
                  name: this.userForm.name,
                  idNumber: this.userForm.idNumber,
                  phone: this.userForm.phone,
                  email: this.userForm.email,
                  classId: this.userForm.classId
                },
                user: {
                  username: this.userForm.username
                }
              }
              addStudent(this.userForm.classId, params).then(result => {
                this.$message({
                  message: '添加用户成功',
                  type: 'success'
                })
                this.cancel()
              }).catch(() => {
                this.$message({
                  message: '操作失败',
                  type: 'danger'
                })
              })
            } else if (this.userForm.roleId === 2) {
              // 教师
              params = {
                teacher: {
                  number: this.userForm.username,
                  name: this.userForm.name,
                  idNumber: this.userForm.idNumber,
                  phone: this.userForm.phone,
                  email: this.userForm.email
                },
                user: {
                  username: this.userForm.username
                }
              }
              addTeacher(params).then(result => {
                this.$message({
                  message: '添加用户成功',
                  type: 'success'
                })
                this.cancel()
              }).catch(() => {
                this.$message({
                  message: '操作失败',
                  type: 'error'
                })
              })
            }
          } else {
            return false
          }
        })
      },
      // 重置
      cancel() {
        this.$refs['userForm'].resetFields()
      },
      // tab切换点击事件
      handleClick(tab, e) {
        if (tab.name === 'second') {
          // nothing
        }
      },
      searchClass(query) {
        try {
          if (this.userForm.roleId !== 1) {
            this.classes = []
            return
          }
        } catch (e) {
          if (e instanceof TypeError) {
            this.classes = []
            return
          }
        }
        this.loading = true
        searchClasses(query).then(result => {
          this.classes = result.data
        }).catch(() => {
          console.log('获取班级信息错误')
        }).finally(() => {
          this.loading = false
        })
      }
    },
    created() {
    },
    mounted() {
      getRoles().then(result => {
        this.roles = result.data
      }).catch((e) => {
        console.log('获取用户角色错误')
      })
    },
    watch: {
      'userForm.roleId': {
        handler: function(newValue, oldValue) {
          if (Number(newValue) === 1) {
            this.rules.classId[0].required = true
          } else {
            this.rules.classId[0].required = false
          }
        },
        immediate: true
      }
    }
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
