import service from '../utils/request'

export function uploadStudents(file) {
  let formData = new FormData()
  formData.append('file', file)
  return uploadFile('/api/users/students/import', formData)
}

export function uploadTeachers(file) {
  let formData = new FormData()
  formData.append('file', file)
  return uploadFile('/api/users/teachers/import', formData)
}

export function uploadClasses(file) {
  let formData = new FormData()
  formData.append('file', file)
  return uploadFile('/api/classes/import', formData)
}

function uploadFile(url, data) {
  return service({
    url: url,
    method: 'POST',
    data: data,
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  })
}
