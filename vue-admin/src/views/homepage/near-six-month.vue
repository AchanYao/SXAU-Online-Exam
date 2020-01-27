<template>
  <div></div>
</template>
<script>
  import { debounce } from '@/utils'
  let echarts = require('echarts/lib/echarts')
  require('echarts/lib/chart/line')
  require('echarts/lib/component/tooltip')
  export default {
    name: 'near-six-month',
    props: {
      width: {
        type: String,
        default: ''
      },
      height: {
        type: String,
        default: ''
      }
    },
    data() {
      return {
        optionData: [],
        legendData: ['用户量', '试题数量'],
        xAxisData: this.getNearSixMonth()
      }
    },
    methods: {
      getNearSixMonth() {
        let value = []
        let currentMonth = 0
        if (new Date().getMonth() >= 6) {
          currentMonth = new Date().getMonth()
          for (let i = 0; i < 6; i++) {
            value.push(new Date().getFullYear() + '-' + (currentMonth + i - 5))
          }
        } else {
          currentMonth = new Date().getMonth()
          for (let i = 0; i < 6; i++) {
            if ((currentMonth + i - 5) < 1) {
              value.push((new Date().getFullYear() - 1) + '-' + (currentMonth + i + 7))
            } else {
              value.push(new Date().getFullYear() + '-' + (currentMonth + i - 5))
            }
          }
        }
        return value
      },
      initChart() {
        this.charts = echarts.init(this.$el)
        this.charts.setOption({
          tooltip: {
            trigger: 'axis'
          },
          grid: {
            left: '50px',
            right: '10px',
            bottom: '30px',
            top: '10px'
          },
          legend: {
            data: ['用户量', '试题数量']
          },
          xAxis: {
            type: 'category',
            boundaryGap: true,
            data: this.xAxisData
          },
          yAxis: {
            type: 'value'
          },
          series: [
            {
              name: '用户量',
              type: 'line',
              stack: '总量',
              data: [120, 132, 101, 134, 90, 230]
            },
            {
              name: '试题数量',
              type: 'line',
              stack: '总量',
              data: [220, 182, 191, 234, 290, 330]
            }
          ]
        })
      }
    },
    mounted() {
      this.$el.style.width = this.width
      this.$el.style.height = this.height
      this.initChart()
      this.__resizeHanlder = debounce(() => {
        if (this.charts) {
          this.charts.resize()
        }
      }, 100)
      window.addEventListener('resize', this.__resizeHanlder)
    }
  }
</script>
