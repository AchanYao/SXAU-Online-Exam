import request from '@/utils/request'

/**
 * @param {{page, size, keyword}} params
 */
export function pageTeachers(params) {
  return request({
    method: 'get',
    url: '/teachers/page',
    params: params
  })
}

export function addTeacher(data) {
  return request({
    method: 'post',
    url: '/users/teachers/add',
    data: data
  })
}

/**
 * @param {string} number
 */
export function getTeacherByNumber(number) {
  return request.get('/teachers/one/' + number)
}

/**
 * @param {number} groupId
 */
export function allByGroup(groupId) {
  return request.get(`/groups/${groupId}/teachers`)
}

/**
 * @param {number} id
 */
export function deleteTeacherById(id) {
  return request.delete('/users/teachers/delete/' + id)
}

/**
 * @param data
 */
export function updateTeacher(data) {
  return request.put('/teachers/update', data)
}

export function listTeachers() {
  return request.get('/teachers/list')
}

/**
 * @param {number} groupId
 */
export function groupPrincipal(groupId) {
  return request.get(`/groups/${groupId}/principal`)
}

export function allTeachers() {
  return request.get('/teachers/list')
}
