/**
 * 用户管理页面
 */
import Layout from '@/layout'

const userManageRouter =
  {
    path: '/users',
    component: Layout,
    redirect: '/users/students',
    name: 'Users',
    meta: {
      title: '用户管理',
      icon: 'peoples'
    },
    children: [
      {
        path: 'students',
        component: () => import('@/views/users/students'),
        name: 'StudentsManage',
        meta: { title: '学生管理', roles: ['admin', 'developer'] }
      },
      {
        path: 'teachers',
        component: () => import('@/views/users/teachers'),
        name: 'TeachersManage',
        meta: { title: '教师管理', roles: ['admin', 'developer'] }
      }
    ]
  }

export default userManageRouter
