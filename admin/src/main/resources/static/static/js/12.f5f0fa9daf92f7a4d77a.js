webpackJsonp([12],{BfBX:function(e,r,t){"use strict";Object.defineProperty(r,"__esModule",{value:!0});var a=t("5k9n"),n={data:function(){return{activeName:"first",courseForm:{number:"",name:"",nameZh:""},rules:{number:[{required:!0,message:"请输入课程编号",trigger:"blur"},{min:3,max:20,message:"长度在 3 到 20 个字符",trigger:"blur"}],name:[{required:!0,message:"请输入课程名称（英）",trigger:"blur"},{min:1,max:40,message:"长度在 1 到 40 个字符",trigger:"blur"}],nameZh:[{required:!0,message:"请输入课程名称（中）",trigger:"blur"},{min:1,max:40,message:"长度在 1 到 20 个字符",trigger:"blur"}]},loading:!1}},methods:{submitForm:function(e){var r=this;this.$refs[e].validate(function(e){if(!e)return!1;Object(a.a)(r.courseForm).then(function(e){e.data&&(r.$message.success("操作成功"),r.cancel())})})},cancel:function(){this.$refs.courseForm.resetFields()},handleClick:function(e,r){e.name}},created:function(){},mounted:function(){},watch:{}},s={render:function(){var e=this,r=e.$createElement,t=e._self._c||r;return t("div",{staticClass:"components-container"},[t("div",{staticClass:"container"},[t("el-tabs",{attrs:{type:"card"},on:{"tab-click":e.handleClick},model:{value:e.activeName,callback:function(r){e.activeName=r},expression:"activeName"}},[t("el-tab-pane",{attrs:{label:"新增课程",name:"first"}},[t("h3",{staticClass:"title"},[e._v("新增课程")]),e._v(" "),t("el-form",{ref:"courseForm",staticClass:"basic-form",attrs:{model:e.courseForm,rules:e.rules,"label-width":"120px"}},[t("el-form-item",{attrs:{label:"课程编号",prop:"number"}},[t("el-input",{attrs:{placeholder:"课程编号"},model:{value:e.courseForm.number,callback:function(r){e.$set(e.courseForm,"number",r)},expression:"courseForm.number"}})],1),e._v(" "),t("el-form-item",{attrs:{label:"课程名（英）",prop:"name"}},[t("el-input",{attrs:{placeholder:"Course Name"},model:{value:e.courseForm.name,callback:function(r){e.$set(e.courseForm,"name",r)},expression:"courseForm.name"}})],1),e._v(" "),t("el-form-item",{attrs:{label:"课程名（中）",prop:"nameZh"}},[t("el-input",{attrs:{placeholder:"课程名"},model:{value:e.courseForm.nameZh,callback:function(r){e.$set(e.courseForm,"nameZh",r)},expression:"courseForm.nameZh"}})],1),e._v(" "),t("el-form-item",[t("el-button",{attrs:{type:"primary"},on:{click:function(r){return e.submitForm("courseForm")}}},[e._v("保存")]),e._v(" "),t("el-button",{on:{click:e.cancel}},[e._v("取消")])],1)],1)],1)],1)],1)])},staticRenderFns:[]};var o=t("VU/8")(n,s,!1,function(e){t("q8TW")},"data-v-805287c8",null);r.default=o.exports},q8TW:function(e,r){}});
//# sourceMappingURL=12.f5f0fa9daf92f7a4d77a.js.map