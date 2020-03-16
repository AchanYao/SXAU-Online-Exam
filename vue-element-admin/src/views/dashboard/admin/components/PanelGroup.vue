<template>
  <el-row :gutter="40" class="panel-group">
    <el-col :lg="6" :sm="12" :xs="12" class="card-panel-col">
      <div class="card-panel" @click="handleSetLineChartData('newVisitis')">
        <div class="card-panel-icon-wrapper icon-people">
          <svg-icon class-name="card-panel-icon" icon-class="peoples" />
        </div>
        <div class="card-panel-description">
          <div class="card-panel-text">
            User
          </div>
          <count-to :duration="2600" :end-val="usersCount" :start-val="0" class="card-panel-num" />
        </div>
      </div>
    </el-col>
    <el-col :lg="6" :sm="12" :xs="12" class="card-panel-col">
      <div class="card-panel" @click="handleSetLineChartData('messages')">
        <div class="card-panel-icon-wrapper icon-message">
          <svg-icon class-name="card-panel-icon" icon-class="message" />
        </div>
        <div class="card-panel-description">
          <div class="card-panel-text">
            Question
          </div>
          <count-to :duration="3000" :end-val="questionsCount" :start-val="0" class="card-panel-num" />
        </div>
      </div>
    </el-col>
    <el-col :lg="6" :sm="12" :xs="12" class="card-panel-col">
      <div class="card-panel" @click="handleSetLineChartData('purchases')">
        <div class="card-panel-icon-wrapper icon-course">
          <svg-icon class-name="card-panel-icon" icon-class="education" />
        </div>
        <div class="card-panel-description">
          <div class="card-panel-text">
            Course
          </div>
          <count-to :duration="3200" :end-val="coursesCount" :start-val="0" class="card-panel-num" />
        </div>
      </div>
    </el-col>
    <el-col :lg="6" :sm="12" :xs="12" class="card-panel-col">
      <div class="card-panel" @click="handleSetLineChartData('shoppings')">
        <div class="card-panel-icon-wrapper icon-shopping">
          <svg-icon class-name="card-panel-icon" icon-class="shopping" />
        </div>
        <div class="card-panel-description">
          <div class="card-panel-text">
            Class
          </div>
          <count-to :duration="3600" :end-val="classesCount" :start-val="0" class="card-panel-num" />
        </div>
      </div>
    </el-col>
  </el-row>
</template>

<script>
import CountTo from 'vue-count-to'
import { clazzCount, coursesCount, questionsCount, usersCount } from '../../../../api/statistics'

export default {
  components: {
    CountTo
  },
  data() {
    return {
      usersCount: 102400,
      questionsCount: 81212,
      coursesCount: 9280,
      classesCount: 13600
    }
  },
  mounted() {
    this.getUserCount()
    this.getClassesCount()
    this.getQuestionsCount()
    this.getCoursesCount()
  },
  methods: {
    handleSetLineChartData(type) {
      this.$emit('handleSetLineChartData', type)
    },
    getUserCount() {
      usersCount().then(result => {
        this.usersCount = result.data
      })
    },
    getCoursesCount() {
      coursesCount().then(result => {
        this.coursesCount = result.data
      })
    },
    getQuestionsCount() {
      questionsCount().then(result => {
        this.questionsCount = result.data
      })
    },
    getClassesCount() {
      clazzCount().then(result => {
        this.classesCount = result.data
      })
    }
  }
}
</script>

<style lang="scss" scoped>
  .panel-group {
    margin-top: 18px;

    .card-panel-col {
      margin-bottom: 32px;
    }

    .card-panel {
      height: 108px;
      cursor: pointer;
      font-size: 12px;
      position: relative;
      overflow: hidden;
      color: #666;
      background: #fff;
      box-shadow: 4px 4px 40px rgba(0, 0, 0, .05);
      border-color: rgba(0, 0, 0, .05);

      &:hover {
        .card-panel-icon-wrapper {
          color: #fff;
        }

        .icon-people {
          background: #40c9c6;
        }

        .icon-message {
          background: #36a3f7;
        }

        .icon-course {
          background: #f4516c;
        }

        .icon-shopping {
          background: #34bfa3
        }
      }

      .icon-people {
        color: #40c9c6;
      }

      .icon-message {
        color: #36a3f7;
      }

      .icon-course {
        color: #f4516c;
      }

      .icon-shopping {
        color: #34bfa3
      }

      .card-panel-icon-wrapper {
        float: left;
        margin: 14px 0 0 14px;
        padding: 16px;
        transition: all 0.38s ease-out;
        border-radius: 6px;
      }

      .card-panel-icon {
        float: left;
        font-size: 48px;
      }

      .card-panel-description {
        float: right;
        font-weight: bold;
        margin: 26px;
        margin-left: 0px;

        .card-panel-text {
          line-height: 18px;
          color: rgba(0, 0, 0, 0.45);
          font-size: 16px;
          margin-bottom: 12px;
        }

        .card-panel-num {
          font-size: 20px;
        }
      }
    }
  }

  @media (max-width: 550px) {
    .card-panel-description {
      display: none;
    }

    .card-panel-icon-wrapper {
      float: none !important;
      width: 100%;
      height: 100%;
      margin: 0 !important;

      .svg-icon {
        display: block;
        margin: 14px auto !important;
        float: none !important;
      }
    }
  }
</style>
