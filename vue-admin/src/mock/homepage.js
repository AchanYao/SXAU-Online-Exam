import Mock from 'mockjs'

export function getHomeTotal() {
  return [
    {
      title: '用户数',
      color: Mock.mock('@hex'),
      data: [Mock.mock('@integer(1, 120)'), Mock.mock('@integer(1, 120)'), Mock.mock('@integer(1, 120)'), Mock.mock('@integer(1, 120)'), Mock.mock('@integer(1, 120)'), Mock.mock('@integer(1, 120)'), Mock.mock('@integer(1, 120)')]
    },
    {
      title: '课程数',
      color: Mock.mock('@hex'),
      data: [Mock.mock('@integer(1, 120)'), Mock.mock('@integer(1, 120)'), Mock.mock('@integer(1, 120)'), Mock.mock('@integer(1, 120)'), Mock.mock('@integer(1, 120)'), Mock.mock('@integer(1, 120)'), Mock.mock('@integer(1, 120)')]
    },
    {
      title: '班级数',
      color: Mock.mock('@hex'),
      data: [Mock.mock('@integer(1, 120)'), Mock.mock('@integer(1, 120)'), Mock.mock('@integer(1, 120)'), Mock.mock('@integer(1, 120)'), Mock.mock('@integer(1, 120)'), Mock.mock('@integer(1, 120)'), Mock.mock('@integer(1, 120)')]
    },
    {
      title: '题目数',
      value: Mock.mock('@natural(0, 9999999)'),
      color: Mock.mock('@hex'),
      data: [Mock.mock('@integer(1, 120)'), Mock.mock('@integer(1, 120)'), Mock.mock('@integer(1, 120)'), Mock.mock('@integer(1, 120)'), Mock.mock('@integer(1, 120)'), Mock.mock('@integer(1, 120)'), Mock.mock('@integer(1, 120)')]
    }
  ]
}

export function getHomeDetailItem() {
  return [
    {
      name: '注册用户数',
      value: Mock.mock('@natural(0, 999999)'),
      color: '#ec407a'
    },
    {
      name: '活跃用户数',
      value: Mock.mock('@natural(0, 999999)'),
      color: '#ab47bc'
    },
    {
      name: '人均投资金额',
      value: Mock.mock('@natural(0, 999999)'),
      color: '#2196f3'
    },
    {
      name: '网站日均访问量',
      value: Mock.mock('@natural(0, 999999)'),
      color: '#009688'
    }
  ]
}

const rankList = []
const count = 30
for (let i = 0; i < count; i++) {
  rankList.push(Mock.mock({
    name: '@cname',
    value: '@natural(0, 9999999)',
    avatar: 'https://avatars.githubusercontent.com/u/' + '@natural(1000000, 9999999)'
  }))
}
export function getHomeInvestmentRank() {
  return rankList
}
