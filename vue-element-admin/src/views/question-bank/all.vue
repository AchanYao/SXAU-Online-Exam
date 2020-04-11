<template>
  <el-row>
    <el-col :lg="18" :md="24">
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
    </el-col>
    <el-col :lg="6" :md="24">
      <div class="m-2">
        <el-tag
          v-for="course in allCourses"
          :key="course.id"
          effect="plain"
          type="info"
          class="m-1 cursor-pointer"
          @click="getQuestionsByCourse(course.id)"
        >{{ course.nameZh }}</el-tag>
      </div>
    </el-col>
  </el-row>
</template>

<script>
import Pagination from '@/components/Pagination'
import QuestionTable from './components/questions-table'
import questionTypes from './data/questions-types'
import { pageQuestions } from '../../api/question'
import { allCourses } from '../../api/courses'

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
        d: undefined
      },
      allCourses: [],
      questionTypes: questionTypes
    }
  },
  created() {
    this.getRecords()
    this.getAllCourses()
  },
  methods: {
    restQuery() {
      this.listQuery = {
        page: 0,
        size: 30,
        keyword: '',
        t: undefined,
        d: undefined
      }
    },
    getRecords() {
      this.listLoading = true
      pageQuestions(this.listQuery).then((result) => {
        this.records = result.data.records
        this.total = result.data.total
      }).finally(() => { this.listLoading = false })
    },
    getAllCourses() {
      allCourses().then((result) => {
        this.allCourses = result.data
      })
    },
    getQuestionsByCourse(courseId) {
      this.$router.push('/questions/course/' + courseId)
    }
  }
}
</script>

<style>
  .cursor-pointer {
    cursor: pointer;
  }
</style>
