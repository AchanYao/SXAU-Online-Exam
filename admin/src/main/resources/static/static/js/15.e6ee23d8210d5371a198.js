webpackJsonp([15],{"2vmX":function(e,t,r){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var a=r("Dd8w"),s=r.n(a),i=r("5k9n"),n=r("NYxO"),o={data:function(){return{activeName:"first",courseForm:{number:"",name:"",nameZh:""},rules:{number:[{required:!0,message:"请输入课程编号",trigger:"blur"},{min:3,max:20,message:"长度在 3 到 20 个字符",trigger:"blur"}],name:[{required:!0,message:"请输入课程名称（英）",trigger:"blur"},{min:1,max:40,message:"长度在 1 到 40 个字符",trigger:"blur"}],nameZh:[{required:!0,message:"请输入课程名称（中）",trigger:"blur"},{min:1,max:40,message:"长度在 1 到 20 个字符",trigger:"blur"}]},loading:!1}},computed:s()({},Object(n.c)(["visitedTabsView"])),methods:s()({},Object(n.b)(["delVisitedTabsView"]),{submitForm:function(e){var t=this;this.$refs[e].validate(function(e){if(!e)return!1;Object(i.h)(t.courseForm).then(function(e){e.data&&(t.$message.success("操作成功"),t.cancel())})})},cancel:function(){this.init()},handleClick:function(e,t){e.name},generateRoute:function(){return!!this.$route.name&&this.$route},isActive:function(e){return e.path===this.$route.path||e.name===this.$route.name},closeThisTabView:function(){var e=this,t=this.generateRoute();this.delVisitedTabsView(t).then(function(r){if(e.isActive(t)){var a=r.slice(-1)[0];a?e.$router.push(a.path):e.$router.push("/")}})},init:function(){var e=this,t=this.$route.params.id;if(!Number(t))return this.$message.error("id应为数字"),void this.closeThisTabView();Object(i.d)(t).then(function(t){null===t.data&&(e.$message.error("查询数据失败"),e.closeThisTabView()),e.courseForm=t.data}).catch(function(){e.$message.error("查询数据失败"),e.closeThisTabView()})}}),created:function(){},mounted:function(){this.init()},watch:{}},c={render:function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("div",{staticClass:"components-container"},[r("div",{staticClass:"container"},[r("el-tabs",{attrs:{type:"card"},on:{"tab-click":e.handleClick},model:{value:e.activeName,callback:function(t){e.activeName=t},expression:"activeName"}},[r("el-tab-pane",{attrs:{label:"新增课程",name:"first"}},[r("h3",{staticClass:"title"},[e._v("新增课程")]),e._v(" "),r("el-form",{ref:"courseForm",staticClass:"basic-form",attrs:{model:e.courseForm,rules:e.rules,"label-width":"120px"}},[r("el-form-item",{attrs:{label:"课程编号",prop:"number"}},[r("el-input",{attrs:{placeholder:"课程编号"},model:{value:e.courseForm.number,callback:function(t){e.$set(e.courseForm,"number",t)},expression:"courseForm.number"}})],1),e._v(" "),r("el-form-item",{attrs:{label:"课程名（英）",prop:"name"}},[r("el-input",{attrs:{placeholder:"Course Name"},model:{value:e.courseForm.name,callback:function(t){e.$set(e.courseForm,"name",t)},expression:"courseForm.name"}})],1),e._v(" "),r("el-form-item",{attrs:{label:"课程名（中）",prop:"nameZh"}},[r("el-input",{attrs:{placeholder:"课程名"},model:{value:e.courseForm.nameZh,callback:function(t){e.$set(e.courseForm,"nameZh",t)},expression:"courseForm.nameZh"}})],1),e._v(" "),r("el-form-item",[r("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.submitForm("courseForm")}}},[e._v("保存")]),e._v(" "),r("el-button",{on:{click:e.cancel}},[e._v("取消")])],1)],1)],1)],1)],1)])},staticRenderFns:[]};var u=r("VU/8")(o,c,!1,function(e){r("WmD/")},"data-v-6943b439",null);t.default=u.exports},"WmD/":function(e,t){}});
//# sourceMappingURL=15.e6ee23d8210d5371a198.js.map