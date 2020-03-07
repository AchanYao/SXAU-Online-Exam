webpackJsonp([19],{WEKB:function(e,r){},yJQL:function(e,r,s){"use strict";Object.defineProperty(r,"__esModule",{value:!0});var a=s("vLgD");var t=s("XPYn"),l={data:function(){return{activeName:"first",userForm:{username:"",name:"",roleId:"",classId:"",idNumber:"",phone:"",email:""},rules:{username:[{required:!0,message:"请输入用户编号",trigger:"blur"},{min:5,max:20,message:"长度在 5 到 20 个字符",trigger:"blur"}],name:[{required:!0,message:"请输入用户名称",trigger:"blur"},{min:1,max:20,message:"长度在 1 到 20 个字符",trigger:"blur"}],roleId:[{required:!0,message:"请选择用户角色",trigger:"blur"}],classId:[{required:!1,message:"请选择用户班级",trigger:"blur"}],idNumber:[{min:16,max:18,message:"长度在 16 到 18 个字符",trigger:"blur"}],phone:[{min:6,max:11,message:"长度在 6 到 11 个字符",trigger:"blur"}],email:[{min:5,message:"长度至少5个字符",trigger:"blur"}]},roles:[],classes:[],loading:!1}},methods:{submitForm:function(e){var r=this;this.$refs[e].validate(function(e){if(!e)return!1;var s,t,l=null;if(1===r.userForm.roleId){if(!Number(r.userForm.classId))return;l={student:{number:r.userForm.username,name:r.userForm.name,idNumber:r.userForm.idNumber,phone:r.userForm.phone,email:r.userForm.email,classId:r.userForm.classId},user:{username:r.userForm.username}},(s=r.userForm.classId,t=l,a.a.post("/api/users/classes/"+s+"/students/add",t)).then(function(e){r.$message({message:"添加用户成功",type:"success"}),r.cancel()}).catch(function(){r.$message({message:"操作失败",type:"danger"})})}else 2===r.userForm.roleId&&function(e){return a.a.post("/api/users/teachers/add",e)}(l={teacher:{number:r.userForm.username,name:r.userForm.name,idNumber:r.userForm.idNumber,phone:r.userForm.phone,email:r.userForm.email},user:{username:r.userForm.username}}).then(function(e){r.$message({message:"添加用户成功",type:"success"}),r.cancel()}).catch(function(){r.$message({message:"操作失败",type:"error"})})})},cancel:function(){this.$refs.userForm.resetFields()},handleClick:function(e,r){e.name},searchClass:function(e){var r=this;try{if(1!==this.userForm.roleId)return void(this.classes=[])}catch(e){if(e instanceof TypeError)return void(this.classes=[])}this.loading=!0,Object(t.d)(e).then(function(e){r.classes=e.data}).catch(function(){console.log("获取班级信息错误")}).finally(function(){r.loading=!1})}},created:function(){},mounted:function(){var e=this;a.a.get("/api/roles/all").then(function(r){e.roles=r.data}).catch(function(e){console.log("获取用户角色错误")})},watch:{"userForm.roleId":{handler:function(e,r){1===Number(e)?this.rules.classId[0].required=!0:this.rules.classId[0].required=!1},immediate:!0}}},o={render:function(){var e=this,r=e.$createElement,s=e._self._c||r;return s("div",{staticClass:"components-container"},[s("div",{staticClass:"container"},[s("el-tabs",{attrs:{type:"card"},on:{"tab-click":e.handleClick},model:{value:e.activeName,callback:function(r){e.activeName=r},expression:"activeName"}},[s("el-tab-pane",{attrs:{label:"新增用户",name:"first"}},[s("h3",{staticClass:"title"},[e._v("新增用户")]),e._v(" "),s("el-form",{ref:"userForm",staticClass:"basic-form",attrs:{model:e.userForm,rules:e.rules,"label-width":"100px"}},[s("el-form-item",{attrs:{label:"用户名",prop:"username"}},[s("el-input",{attrs:{placeholder:"教师号或学生学号"},model:{value:e.userForm.username,callback:function(r){e.$set(e.userForm,"username",r)},expression:"userForm.username"}})],1),e._v(" "),s("el-form-item",{attrs:{label:"姓名",prop:"name"}},[s("el-input",{attrs:{placeholder:"用户真实姓名"},model:{value:e.userForm.name,callback:function(r){e.$set(e.userForm,"name",r)},expression:"userForm.name"}})],1),e._v(" "),s("el-form-item",{attrs:{label:"用户角色",prop:"roleId"}},[s("el-select",{attrs:{placeholder:"用户角色"},model:{value:e.userForm.roleId,callback:function(r){e.$set(e.userForm,"roleId",r)},expression:"userForm.roleId"}},e._l(e.roles,function(e){return s("el-option",{key:e.id,attrs:{disabled:e.id>2,label:e.nameZh,value:e.id}})}),1)],1),e._v(" "),s("el-collapse-transition",[s("el-form-item",{directives:[{name:"show",rawName:"v-show",value:1===e.userForm.roleId,expression:"userForm.roleId === 1"}],attrs:{label:"学生班级",prop:"classId"}},[s("el-select",{attrs:{loading:e.loading,"remote-method":e.searchClass,filterable:"",placeholder:"学生班级",remote:""},model:{value:e.userForm.classId,callback:function(r){e.$set(e.userForm,"classId",r)},expression:"userForm.classId"}},e._l(e.classes,function(e){return s("el-option",{key:e.id,attrs:{label:e.name,value:e.id}})}),1)],1)],1),e._v(" "),s("h4",{staticClass:"title"},[e._v("用户详细信息")]),e._v(" "),s("div",{staticClass:"content"},[s("el-form-item",{attrs:{label:"身份证号",prop:"idNumber"}},[s("el-input",{model:{value:e.userForm.idNumber,callback:function(r){e.$set(e.userForm,"idNumber",r)},expression:"userForm.idNumber"}})],1),e._v(" "),s("el-form-item",{attrs:{label:"联系电话",prop:"phone"}},[s("el-input",{model:{value:e.userForm.phone,callback:function(r){e.$set(e.userForm,"phone",r)},expression:"userForm.phone"}})],1),e._v(" "),s("el-form-item",{attrs:{label:"电子邮箱",prop:"email"}},[s("el-input",{model:{value:e.userForm.email,callback:function(r){e.$set(e.userForm,"email",r)},expression:"userForm.email"}})],1)],1),e._v(" "),s("el-form-item",[s("el-button",{attrs:{type:"primary"},on:{click:function(r){return e.submitForm("userForm")}}},[e._v("保存")]),e._v(" "),s("el-button",{on:{click:e.cancel}},[e._v("取消")])],1)],1)],1)],1)],1)])},staticRenderFns:[]};var n=s("VU/8")(l,o,!1,function(e){s("WEKB")},"data-v-29360225",null);r.default=n.exports}});
//# sourceMappingURL=19.873d7ac45ec37ef3e23d.js.map