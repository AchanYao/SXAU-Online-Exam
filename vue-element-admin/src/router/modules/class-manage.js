/**
 * 班级管理页面
 */
import Layout from '@/layout'

const classManageRouter =
  {
    path: '/classes',
    component: Layout,
    redirect: '/classes/index',
    name: 'Classes',
    meta: {
      icon: 'el-icon-s-cooperation'
    },
    children: [
      {
        path: 'index',
        component: () => import('@/views/classes'),
        name: 'ClassesManage',
        meta: { title: '班级管理', roles: ['admin', 'developer'] }
      }
    ]
  }

export default classManageRouter
