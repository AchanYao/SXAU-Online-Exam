webpackJsonp([14],{"1AFA":function(e,t){},XzBJ:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var r=a("uXZL"),n=a.n(r),l=a("uzBN"),i={data:function(){return{tableData:[],tableHeader:[]}},methods:{handleFilePreview:function(e){this.handleFileChange(e)},handleFileChange:function(e,t){this.uploadFile(e.raw)},readerData:function(e){var t=this,a=new FileReader;a.onload=function(e){for(var a="",r=e.target.result,l=new Uint8Array(r),i=l.byteLength,o=0;o<i;o++)a+=String.fromCharCode(l[o]);var s=n.a.read(a,{type:"binary"}),c=s.SheetNames[0],d=s.Sheets[c],u=t.getHeaderRow(d),h=n.a.utils.sheet_to_json(d);t.generateDate({header:u,results:h})},a.readAsArrayBuffer(e)},generateDate:function(e){var t=e.header,a=e.results;this.tableHeader=t,this.tableData=a},fixdata:function(e){for(var t="",a=0,r=10240;a<e.byteLength/r;++a)t+=String.fromCharCode.apply(null,new Uint8Array(e.slice(a*r,a*r+r)));return t+=String.fromCharCode.apply(null,new Uint8Array(e.slice(a*r)))},getHeaderRow:function(e){var t=[],a=n.a.utils.decode_range(e["!ref"]),r=void 0,l=a.s.r;for(r=a.s.c;r<=a.e.c;++r){var i=e[n.a.utils.encode_cell({c:r,r:l})],o="UNKNOWN "+r;i&&i.t&&(o=n.a.utils.format_cell(i)),t.push(o)}return t},uploadFile:function(e){var t=this,a=this.$loading({lock:!0,text:"Uploading"});Object(l.c)(e).then(function(){t.readerData(e),t.$message.success("操作成功")}).catch(function(e){t.$message.error("操作失败")}).finally(function(){a.close()})}}},o={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{},[a("el-alert",{attrs:{title:"注意",type:"warning",description:"只支持.csv格式文件且满足格式要求（教师表为两列：教师号、教师姓名；学生表为三列：学号、姓名、班级id；班级表为两列：班级id、班级名）",closable:!1,"show-icon":""}}),e._v(" "),a("el-upload",{staticClass:"upload-demo",attrs:{"auto-upload":!1,limit:1,multiple:!1,"on-change":e.handleFileChange,"on-preview":e.handleFilePreview,accept:".csv",action:"",drag:"","list-type":"text"}},[a("i",{staticClass:"el-icon-upload"}),e._v(" "),a("div",{staticClass:"el-upload__text"},[e._v("将文件拖到此处，或"),a("em",[e._v("点击上传")])])]),e._v(" "),a("el-table",{staticStyle:{width:"100%","margin-top":"20px"},attrs:{data:e.tableData,border:"","highlight-current-row":""}},e._l(e.tableHeader,function(e){return a("el-table-column",{key:e.id,attrs:{label:e,prop:e}})}),1)],1)},staticRenderFns:[]};var s=a("VU/8")(i,o,!1,function(e){a("1AFA")},"data-v-71cf2b6f",null);t.default=s.exports}});
//# sourceMappingURL=14.6816f6a8d5fb255d111f.js.map