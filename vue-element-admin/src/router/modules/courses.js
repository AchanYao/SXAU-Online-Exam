/**
 * 班级管理页面
 */
import Layout from '@/layout'

const courseManageRouter =
  {
    path: '/courses',
    component: Layout,
    redirect: '/courses/index',
    name: 'Courses',
    meta: {
      icon: 'education',
      roles: ['admin', 'developer']
    },
    children: [
      {
        path: 'index',
        component: () => import('@/views/courses'),
        name: 'CoursesManage',
        meta: { title: '课程管理', roles: ['admin', 'developer'] }
      }
    ]
  }

export default courseManageRouter
