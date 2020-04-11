<template>
  <div class="m-2">
    <div class="mb-1 h3">题目：</div>
    <p class="mb-1">{{ question.questionDescription.replace(/(#)|（#）/, '()') }}</p>
    <list-group v-if="question.typeId === 1" class="mb-1">
      <list-group-item :text="question.optionA" />
      <list-group-item :text="question.optionB" />
      <list-group-item :text="question.optionC" />
      <list-group-item :text="question.optionD" />
    </list-group>
    <div class="mb-1 h3 cursor-pointer" @click="getAnswer"><svg-icon :icon-class="answer ? 'eye-open' : 'eye'" />答案：</div>
    <div v-if="answer && question.typeId === 3" class="answer-container">
      <i :class="answer.content === 'T' ? 'el-icon-check' : 'el-icon-close'" />
    </div>
    <div v-else-if="answer" class="answer-container">
      <tinymce v-model="answer.content" :height="400" />
    </div>
  </div>
</template>

<script>
import { getQuestion } from '@/api/question'
import { getAnswerByQuestion } from '@/api/answer'
import Tinymce from '@/components/Tinymce'
import ListGroup from '../../components/ListGroup/index'
import ListGroupItem from '../../components/ListGroupItem/index'

export default {
  components: {
    ListGroupItem,
    ListGroup,
    Tinymce
  },
  data() {
    return {
      question: {
        'chapterId': 0,
        'difficultyId': 0,
        'id': 0,
        'questionDescription': 'string',
        'questionId': 0,
        'studentId': 0,
        'teacherId': 0,
        'typeId': 0
      },
      answer: null
    }
  },
  created() {
    this.initQuestion()
    this.setPageTitle()
  },
  methods: {
    initQuestion() {
      getQuestion(this.$route.params.id).then((result) => {
        this.question = result.data
        if (!this.question) {
          this.closeThisTag()
        }
      }).catch((error) => {
        this.$message.error(error)
        this.closeThisTag()
      })
    },
    closeThisTag() {
      this.$store.dispatch('tagsView/delView', this.$store.state.tagsView.visitedViews.find((value) => {
        return value.path === this.$route.path
      })).then(() => {
        this.$router.push('/')
      })
    },
    getAnswer() {
      if (this.answer) {
        return
      }
      getAnswerByQuestion(this.question.questionId).then((result) => {
        this.answer = result.data
      }).catch((error) => {
        this.$message.error(error)
      })
    },
    setPageTitle() {
      const title = '问题'
      document.title = `${title} - ${this.$route.params.id}`
    }
  }
}
</script>
