<template>
  <div class="m-2">
    <h3 class="text-center">{{ course ? course.nameZh : '课程名称' }}</h3>
    <el-container class="mt-2">
      <el-table :data="chapters" row-class-name="cursor-pointer" highlight-current-row @current-change="handleClick">
        <el-table-column label="#" width="50" type="index" />
        <el-table-column label="章节" min-width="150">
          <template slot-scope="{row}">
            <span :title="row.description ? row.description.substring(0, 50) : row.name">{{ row.name }}</span>
          </template>
        </el-table-column>
        <el-table-column width="30">
          <template>
            <span> > </span>
          </template>
        </el-table-column>
      </el-table>
    </el-container>
  </div>
</template>

<script>
import { listChaptersByCourse } from '@/api/chapter'
import { getCourseById } from '@/api/courses'
export default {
  name: 'CourseChapter',
  data() {
    return {
      course: null,
      chapters: [],
      currentRow: null
    }
  },
  created() {
    this.getChapters()
    this.getCourse()
  },
  methods: {
    getChapters() {
      const courseId = this.$route.params.id
      listChaptersByCourse(courseId).then((result) => {
        this.chapters = result.data
      }).catch(() => {
        // 指定课程id不存在，关闭当前页面，重定向到/
        this.$store.dispatch('tagsView/delView', this.$store.state.tagsView.visitedViews.find((value) => {
          return value.path === this.$route.path
        })).then(() => {
          this.$router.push('/')
        })
      })
    },
    getCourse() {
      const courseId = this.$route.params.id
      getCourseById(courseId).then((result) => {
        this.course = result.data
      })
    },
    handleClick(val) {
      this.$router.push('/questions/chapter/' + val.id)
    }
  }
}
</script>
