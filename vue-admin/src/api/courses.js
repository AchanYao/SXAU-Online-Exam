import service from '../utils/request'

export function addCourse(data) {
  return service.post('/api/courses/add', data)
}

export function deleteCourse(id) {
  return service.delete('/api/courses/delete/' + id)
}

export function getCourse(id) {
  return service.get('/api/courses/' + id)
}

/**
 * 分页获取系统课程信息
 * @param page 页码
 * @param size 页面大小
 * @param keyword 搜索关键字
 * @returns {AxiosPromise<any>}
 */
export function pageWithKeyword(page, size, keyword) {
  if (keyword === null || keyword.trim().length === 0) {
    return service.get('/api/courses/page?page=' + page + '&size=' + size)
  } else {
    return service.get('/api/courses/page?page=' + page + '&size=' + size + '&keyword=' + keyword)
  }
}

export function page(page, size) {
  return service.get('/api/courses/page?page=' + page + '&size=' + size)
}

export function updateCourse(data) {
  return service.put('/api/courses/update', data)
}

export function coursesCount() {
  return service.get('/api/courses/count')
}

export function noTeacherGroupCourses() {
  return service.get('/api/courses/no-teacher-group')
}
