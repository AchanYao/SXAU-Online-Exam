import service from '../utils/request'

export function page(page, size) {
  return service.get('/api/teachers/page?page=' + page + '&size=' + size)
}

export function pageWithKeyword(page, size, keyword) {
  return service.get('/api/teachers/page?page=' + page + '&size=' + size + '&keyword=' + keyword)
}

export function deleteTeacher(id) {
  return service.delete('/api/users/teachers/delete/' + id)
}

export function listTeachers() {
  return service.get('/api/teachers/list')
}
