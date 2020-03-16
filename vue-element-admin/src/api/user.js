import request from '@/utils/request'

/**
 * 用户表单参数（用户名和密码）
 * @typedef {Object} UserParams
 * @property {string} username - 用户名
 * @property {string} password - 用户密码
 */
/**
 * 登录
 * @param {UserParams} data
 * @returns {AxiosPromise}
 */
export function login(data) {
  return request({
    url: '/auth/login',
    method: 'post',
    data: data
  })
}

/**
 * @param {string} token
 */
export function refreshToken(token) {
  return request.post('/auth/refresh?token=' + token)
}

export function getInfo() {
  return request({
    url: '/auth/me',
    method: 'get'
  })
}

/**
 * @param {string} accessToken
 * @param {string} refreshToken
 * @returns {AxiosPromise}
 */
export function logout(accessToken, refreshToken) {
  const params = { 'access-token': accessToken, 'refresh-token': refreshToken }
  return request({
    url: '/auth/logout',
    method: 'post',
    params: params
  })
}

/**
 * 账户是否可用
 * @param {number} id
 */
export function changeStatus(id) {
  return request.put(`/users/change-status/${id}`)
}
