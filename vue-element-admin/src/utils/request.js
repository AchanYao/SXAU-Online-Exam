import axios from 'axios'
import { Message, MessageBox } from 'element-ui'
import { getToken } from '@/utils/auth'
// import router from '@/router'
import store from '@/store'

// create an axios instance
const service = axios.create({
  baseURL: process.env.VUE_APP_BASE_API, // url = base url + request url
  // withCredentials: true, // send cookies when cross-domain requests
  timeout: 5000 // request timeout
})

// request interceptor
service.interceptors.request.use(
  config => {
    // do something before request is sent

    const token = getToken('access_token')
    if (token && !config.url.includes('/auth/refresh')) {
      // let each request carry token
      config.headers['Authorization'] = 'Bearer ' + token
    }
    return config
  },
  error => {
    // do something with request error
    console.log(error) // for debug
    return Promise.reject(error)
  }
)

// response interceptor
service.interceptors.response.use(
  /**
   * If you want to get http information such as headers or status
   * Please return  response => response
   */

  /**
   * Determine the request status by custom code
   * Here is just an example
   * You can also judge the status by HTTP Status Code
   */
  response => {
    const res = response.data

    // if the custom code is not 20000, it is judged as an error.
    if (res.code !== 20000) {
      Message({
        message: res.message || 'Error',
        type: 'error',
        duration: 5 * 1000
      })

      return Promise.reject(new Error(res.message || 'Error'))
    } else {
      return res
    }
  },
  error => {
    console.log('err' + error) // for debug
    const status = error.response.status
    if (status !== 401) {
      Message({
        message: error.message,
        type: 'error',
        timeout: 5 * 1000
      })
      return Promise.reject(error)
    } else {
      MessageBox.confirm('登录过期，请重新登录', 'Confirm logout', {
        confirmButtonText: 'Re-Login',
        cancelButtonText: 'Cancel',
        type: 'warning'
      }).then(() => {
        store.dispatch('user/refreshToken').then(() => {
          error.response.config.headers['Authorization'] = 'Bearer ' + getToken('access_token')
          return service(error.response.config)
        }).catch(error => {
          return Promise.reject(error)
        })
        return Promise.reject(error)
      })
    }
  }
)

export default service
