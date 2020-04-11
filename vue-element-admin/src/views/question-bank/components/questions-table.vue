<template>
  <div class="scroll-overflow-x">
    <el-table v-loading="listLoading" :data="records" fit highlight-current-row border style="width: 100%">
      <el-table-column label="#" width="50" type="index" />
      <el-table-column label="题目" min-width="150">
        <template slot-scope="{row}">
          <span class="link-type" @click="toQuestionPage(row.id)">
            {{ row.description.length > 100 ? row.description.substring(0, 100).replace(/(#)|（#）/, '()') : row.description.replace(/(#)|（#）/, '()') }}
          </span>
        </template>
      </el-table-column>
      <el-table-column label="难度" width="90">
        <template slot-scope="{row}">
          <el-tag v-if="row.difficultyId === 1" type="success">简单</el-tag>
          <el-tag v-else-if="row.difficultyId === 2" type="warning">中等</el-tag>
          <el-tag v-else type="danger">困难</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="类型" width="90">
        <template slot-scope="{row}">
          <el-tag type="info" effect="plain">{{ getQuestionTypeName(row.id) }}</el-tag>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import questionTypes from '../data/questions-types'
export default {
  name: 'QuestionTable',
  props: {
    records: {
      default: () => {
        return []
      },
      type: Array
    },
    listLoading: {
      type: Boolean,
      default: true
    }
  },
  data() {
    return {
    }
  },
  methods: {
    getQuestionTypeName(typeId) {
      return questionTypes.find((value) => {
        return value.id === typeId
      }).nameZh
    },
    toQuestionPage(questionId) {
      this.$router.push('/questions/' + questionId)
    }
  }
}
</script>
