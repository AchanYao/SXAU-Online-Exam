import service from '../utils/request'

export function page(page, size) {
  return service.get('/api/students/page?page=' + page + '&size=' + size)
}

export function pageWithKeyword(page, size, keyword) {
  return service.get('/api/students/page?page=' + page + '&size=' + size + '&keyword=' + keyword)
}

export function deleteStudent(id) {
  return service.delete('/api/users/students/delete/' + id)
}
