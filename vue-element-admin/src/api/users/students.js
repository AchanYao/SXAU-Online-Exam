import request from '@/utils/request'

/**
 * @param {{page, size, keywords}} params
 */
export function pageStudents(params) {
  return request({
    method: 'get',
    url: '/students/page',
    params: params
  })
}

/**
 * @param {string} number
 */
export function getStudentByNumber(number) {
  return request.get('/students/one/' + number)
}

/**
 * 获取指定班级的全部学生
 * @param {number} classId
 */
export function allByClass(classId) {
  return request.get('/students/classes/' + classId)
}

/**
 * @param data
 */
export function addStudent(data) {
  return request.post(`/users/students/add`, data)
}

/**
 * @param {number} id
 */
export function deleteStudentById(id) {
  return request.delete('/users/students/delete/' + id)
}

/**
 * @param data
 */
export function update(data) {
  return request.put('/users/students/update', data)
}
