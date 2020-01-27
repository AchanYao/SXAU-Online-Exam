import Vue from 'vue'
import Router from 'vue-router'

import Login from '@/views/login'
import Layout from '@/layout/layout'

Vue.use(Router)

/**
 * alwaysShow: true           if set true, will always show the root menu, whatever its child routes length
 *                            if not set alwaysShow, only more than ont route under the children
 *                            it will becomes nested mode, otherwise not show the root menu
 * alwaysShow: true           如果设置为true,它将总是现在在根目录。如果不设置的话，当它只有1个子路由的时候，会把
 *                            它的唯一子路由放到跟目录上来，而不显示它自己本身。
 */

export const constantRouterMap = [
  {
    path: '/login',
    name: 'login',
    hidden: true,
    component: Login,
    meta: {
      title: '登录'
    }
  },
  {
    path: '/404',
    component: () => import('@/views/error-page/404'),
    hidden: true
  },
  {
    path: '/',
    hidden: true,
    component: Layout,
    redirect: '/home',
    children: [
      {
        path: 'home',
        name: 'home',
        component: () => import('@/views/homepage'),
        meta: {title: '首页'}
      }
    ]
  },
  {
    path: '/user',
    component: Layout,
    hidden: true,
    meta: {
      icon: 'tickets',
      title: '个人中心'
    },
    children: [
      {
        path: 'profile',
        name: 'Profile',
        component: () => import('@/views/user/profile'),
        meta: {icon: 'warning', title: '个人中心'}
      },
      {
        path: 'avatar',
        name: 'Avatar',
        component: () => import('@/views/user/profile'),
        meta: {icon: 'warning', title: '修改头像'}
      }
    ]
  },
  {
    path: '/introduction',
    component: Layout,
    redirect: '/introduction/index',
    // alwaysShow: true,
    meta: {
      icon: 'svg-aperture',
      title: '公告'
    },
    children: [
      {
        path: 'index',
        name: 'Introduction',
        component: () => import('@/views/introduction/index'),
        meta: {icon: 'svg-aperture', title: '公告'}
      }
    ]
  },
  /*
    {
      path: '/nested',
      component: Layout,
      meta: {
        icon: 'svg-layers',
        title: '路由嵌套'
      },
      children: [
        {
          path: 'menu1',
          meta: { icon: 'share', title: '嵌套路由1' },
          component: () => import('@/views/nested/menu1/index'),
          children: [
            {
              path: 'menu1-1',
              name: 'menu1-1',
              component: () => import('@/views/introduction/index'),
              meta: {icon: 'success', title: '嵌套路由1-1'}
            },
            {
              path: 'menu1-2',
              name: 'menu1-2',
              component: () => import('@/views/nested/menu1/menu1-2/index'),
              alwaysShow: true,
              meta: {icon: 'error', title: '嵌套路由1-2'},
              children: [
                {
                  path: 'menu1-2-1',
                  component: () => import('@/views/introduction/index'),
                  name: 'menu1-2-1',
                  meta: {icon: 'warning', title: '嵌套路由1-2-1'}
                }
              ]
            }
          ]
        },
        {
          path: 'menu2',
          name: 'menu2',
          component: () => import('@/views/user/add'),
          meta: {icon: 'star-on', title: '嵌套路由2'}
        }
      ]
    },
  */
  {
    path: '/users',
    component: Layout,
    redirect: '/users/add',
    meta: {
      icon: 'user-solid',
      title: '用户管理'
    },
    children: [
      {
        path: 'add',
        name: 'AddUser',
        component: () => import('../views/user/add'),
        meta: {icon: 'user-solid', title: '新增用户'}
      },
      {
        path: 'students',
        name: 'Students',
        component: () => import('../views/users/Students'),
        meta: {icon: 'user', title: '学生管理'}
      },
      {
        path: 'teachers',
        name: 'Teachers.vue',
        component: () => import('../views/users/Teachers.vue'),
        meta: {icon: 'user', title: '教师管理'}
      }
    ]
  },
  {
    path: '/classes',
    component: Layout,
    redirect: '/classes/all',
    meta: {
      icon: 'office-building',
      title: '班级管理'
    },
    children: [
      {
        path: 'all',
        name: 'AllClasses',
        component: () => import('../views/classes/index'),
        meta: {icon: 'office-building', title: '班级管理'}
      }
    ]
  },
  {
    path: '/courses',
    component: Layout,
    meta: {
      icon: 'collection',
      title: '课程管理'
    },
    children: [
      {
        path: 'all',
        name: 'AllCourses',
        component: () => import('../views/courses/AllCourses'),
        meta: {icon: 'notebook-2', title: '课程查看'}
      },
      {
        path: 'add',
        name: 'AddCourses',
        component: () => import('../views/courses/AddCourses'),
        meta: {icon: 'notebook-1', title: '新增课程'}
      },
      {
        path: 'update/:id',
        name: 'update-course',
        component: () => import('../views/courses/update'),
        hidden: true,
        meta: {icon: 'notebook-1', title: '修改课程'}
      }
    ]
  },
  {
    path: '/teacher-groups',
    component: Layout,
    meta: {
      icon: 's-management',
      title: '课程组管理'
    },
    children: [
      {
        path: 'all',
        name: 'all-teacher-groups',
        component: () => import('../views/teacher-groups/index'),
        meta: {icon: 'notebook-1', title: '查看课程组'}
      },
      {
        path: 'update/:id',
        name: 'update-teacher-group',
        component: () => import('../views/teacher-groups/update'),
        meta: {icon: 'notebook-2', title: '编辑课程组'},
        hidden: true
      },
      {
        path: 'add',
        name: 'add-teacher-group',
        component: () => import('../views/teacher-groups/add'),
        meta: {icon: 'notebook-2', title: '新增课程组'}
      }
    ]
  },
  /*
    {
      path: '/custom-component',
      component: Layout,
      meta: {
        icon: 'question',
        title: '自定义组件'
      },
      redirect: '/custom-component/index',
      children: [
        {
          path: 'index',
          name: 'CustomComponent',
          component: () => import('@/views/custom-component/index'),
          meta: {icon: 'guide', title: '自定义组件'}
        }
      ]
    },
  */
  {
    path: '/echarts',
    component: Layout,
    redirect: 'index',
    alwaysShow: true,
    meta: {
      title: '可视化',
      icon: 'svg-droplet'
    },
    children: [
      {
        path: 'index',
        name: '数量统计',
        component: () => import('@/views/echarts'),
        meta: {title: '数量统计', icon: 'svg-heart'}
      }
    ]
  },
  {
    path: '/rich-editor',
    component: Layout,
    alwaysShow: true,
    meta: {
      icon: 's-order',
      title: '富文本'
    },
    redirect: 'quill',
    children: [
      {
        path: 'quill',
        name: 'QuillEditor',
        component: () => import('@/views/rich-editor/quill'),
        meta: {icon: 's-order', title: 'quill'}
      },
      {
        path: 'tinymce',
        name: 'TinyMCE',
        component: () => import('@/views/rich-editor/tinymce'),
        meta: {icon: 'svg-type', title: 'tinymce'}
      }
    ]
  },
  {
    path: '/import',
    component: Layout,
    alwaysShow: true,
    meta: {
      title: '信息导入',
      icon: 'date'
    },
    children: [
      {
        path: 'students',
        name: '导入学生',
        component: () => import('../views/upload/Students'),
        meta: {icon: 'upload', title: '导入学生'}
      },
      {
        path: 'teachers',
        name: '导入教师',
        component: () => import('@/views/upload/Teachers'),
        meta: {icon: 'upload', title: '导入教师'}
      },
      {
        path: 'classes',
        name: '导入班级',
        component: () => import('../views/upload/Classes'),
        meta: {icon: 'upload', title: '导入班级'}
      }
    ]
  },
  {
    path: '*',
    redirect: '/404'
  }
]

export default new Router({
  // mode: 'history',  require service support
  // scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap
})

export const asyncRouterMap = [
  /*
    {
      path: '/clipboard',
      component: Layout,
      redirect: 'index',
      meta: {
        icon: 'document',
        title: '剪切板'
      },
      children: [
        {
          path: 'index',
          name: 'clipBoard',
          component: () => import('@/views/clipboard'),
          meta: {title: '剪切板示例', icon: 'document'}
        }
      ]
    },
  */
  {
    path: '/theme',
    component: Layout,
    meta: {
      icon: 'document',
      title: 'theme'
    },
    children: [
      {
        path: 'index',
        component: () => import('@/views/theme/index'),
        name: 'Theme',
        meta: {title: '主题', icon: 'svg-pifu'}
      }
    ]
  },
  {path: '*', redirect: '/', hidden: true}
]
