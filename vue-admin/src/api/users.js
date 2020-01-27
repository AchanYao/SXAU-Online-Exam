import service from '../utils/request'

export function getRoles() {
  return service.get('/api/roles/all')
}

export function addTeacher(data) {
  return service.post('/api/users/teachers/add', data)
}

export function addStudent(classId, data) {
  return service.post('/api/users/classes/' + classId + '/students/add', data)
}
