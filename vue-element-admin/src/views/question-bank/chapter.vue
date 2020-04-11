<template>
  <el-card class="box-card m-2">
    <div slot="header" class="clearfix">
      <div class="filter-container">
        <el-input
          v-model="listQuery.keyword"
          class="filter-item"
          placeholder="Search"
          style="width: 200px;"
          @keyup.enter.native="getRecords"
        />
        <el-button class="filter-item" icon="el-icon-search" type="primary" @click="getRecords">
          查找
        </el-button>
        <el-select v-model="listQuery.t" clearable class="ml-1 filter-item" placeholder="类型" @change="getRecords">
          <el-option
            v-for="type in questionTypes"
            :key="type.id"
            :label="type.nameZh"
            :value="type.id"
          />
        </el-select>
        <el-select v-model="listQuery.d" clearable class="ml-1 filter-item" placeholder="难度" @change="getRecords">
          <el-option value="1" label="简单" />
          <el-option value="2" label="中等" />
          <el-option value="3" label="困难" />
        </el-select>
      </div>
    </div>
    <question-table :list-loading="listLoading" :records="records" />
    <pagination
      v-show="total>0"
      :limit.sync="listQuery.size"
      :page.sync="listQuery.page"
      :total="total"
      @pagination="getRecords"
    />
  </el-card>
</template>

<script>
import Pagination from '@/components/Pagination'
import QuestionTable from './components/questions-table'
import questionTypes from './data/questions-types'
import { pageQuestions } from '../../api/question'

export default {
  name: 'QuestionList',
  components: { Pagination, QuestionTable },
  data() {
    return {
      listLoading: true,
      'records': [],
      'total': 0,
      'orders': [],
      'searchCount': true,
      listQuery: {
        page: 0,
        size: 30,
        keyword: '',
        t: undefined,
        d: undefined,
        c: undefined
      },
      questionTypes: questionTypes
    }
  },
  created() {
    this.listQuery.c = this.$route.params.id
    this.getRecords()
  },
  methods: {
    getRecords() {
      this.listLoading = true
      if (!Number(this.listQuery.c)) {
        // 指定课程id不存在，关闭当前页面，重定向到/
        this.$store.dispatch('tagsView/delView', this.$store.state.tagsView.visitedViews.find((value) => {
          return value.path === this.$route.path
        })).then(() => {
          this.$router.push('/')
        })
      }
      pageQuestions(this.listQuery).then((result) => {
        this.records = result.data.records
        this.total = result.data.total
      }).finally(() => { this.listLoading = false })
    }
  }
}
</script>
