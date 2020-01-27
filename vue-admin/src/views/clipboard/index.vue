<template>
  <div>
    <el-tabs v-model="activeName">
      <el-tab-pane label="原生js方式" name="js">
        <el-input placeholder="Please input" ref="input1" style='width:400px;' v-model="inputData"></el-input>
        <wired-button @click='handleCopy1' elevation="3">copy</wired-button>
        <p class="title">粘贴一下：</p>
        <el-input :rows="10" style="width: 400px;" type="textarea"></el-input>
      </el-tab-pane>

      <el-tab-pane @click="console.log('s')" label="用clipboard插件" name="directly">
        <el-input placeholder="Please input" ref="input2" style='width:400px;' v-model="inputData"></el-input>
        <el-button @click='handleCopy2(inputData,$event)' type="primary">copy</el-button>
        <p class="title">粘贴一下：</p>
        <el-input :rows="10" style="width: 400px;" type="textarea"></el-input>
      </el-tab-pane>

      <el-tab-pane label="通过指令的方式调用clipboard" name="v-directive">
        <el-input placeholder="please input" style='width:400px;' v-model="inputData"></el-input>
        <el-button type="primary" v-clipboard:copy='inputData' v-clipboard:success='clipboardSuccess'>copy</el-button>
        <p class="title">粘贴一下：</p>
        <el-input :rows="10" style="width: 400px;" type="textarea"></el-input>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
  import clip from '@/utils/clipboard'
  import clipboard from '@/directive/clipboard'
  /* eslint-disable no-unused-vars */
  export default {
    directives: {
      clipboard
    },
    data() {
      return {
        activeName: 'js',
        inputData: 'https://github.com/Achan'
      }
    },
    methods: {
      handleCopy1() {
        this.$refs.input1.$refs['input'].select()
        document.execCommand('Copy')
        this.$notify({
          title: 'success',
          message: 'copy成功',
          type: 'success'
        })
      },
      handleCopy2(t, e) {
        clip(t, e)
      },
      clipboardSuccess() {
        this.$notify({
          title: 'success',
          message: 'copy成功',
          type: 'success'
        })
      }
    }
  }
</script>

<style lang="stylus" scoped>
  .title
    margin-top 20px

  wired-button
    background lightblue
</style>
