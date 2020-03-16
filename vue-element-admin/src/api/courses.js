import service from '@/utils/request'

export function allCourses() {
  return service.get('/courses/all')
}

export function getCourseById(id) {
  return service.get('/courses/' + id)
}

export function pageCourses(params) {
  return service({
    method: 'get',
    params: params,
    url: '/courses/page'
  })
}

export function deleteCourseById(id) {
  return service.delete('/courses/delete/' + id)
}

export function addCourse(data) {
  return service.post('/courses/add', data)
}

export function updateCourse(data) {
  return service.put('/courses/update', data)
}

/**
 * 获取没有课程组的所有课程
 */
export function getAllWithOutGroup() {
  return service.get('/courses/no-teacher-group')
}
