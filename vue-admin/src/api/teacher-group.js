import service from '../utils/request'

export function getGroup(id) {
  return service.get('/api/teacher-groups/' + id)
}

export function setPrincipal(groupId, teacherId) {
  return service.put('/api/teacher-groups/' + groupId + '/principal/' + teacherId)
}

export function newGroup(data) {
  return service.post('/api/teacher-groups/new', data)
}

export function page(page, size, keyword) {
  if (keyword === undefined || keyword === null || keyword.trim().length === 0) {
    return service.get('/api/teacher-groups/page?page=' + page + '&size=' + size)
  } else {
    return service.get('/api/teacher-groups/page?page=' + page + '&size=' + size + '&keyword=' + keyword)
  }
}

export function deleteTeacherGroup(id) {
  return service.delete('/api/teacher-groups/delete/' + id)
}

export function findTeacherGroupByCourseId(courseId) {
  return service.get('/api/teacher-groups/one?courseId=' + courseId)
}

export function updateTeacherGroup(data) {
  return service.put('/api/teacher-groups/update', data)
}
