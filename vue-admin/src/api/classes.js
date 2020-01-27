import service from '../utils/request'

export function searchClasses(keyword) {
  if (keyword === null || keyword === '') {
    return service.get('/api/classes/all')
  }
  return service.get('/api/classes/search?keyword=' + keyword)
}

export function addClass(name) {
  return service.post('/api/classes/add', {name: name})
}

export function deleteClass(id) {
  return service.delete('/api/classes/delete/' + id)
}

export function updateClass(data) {
  return service.put('/api/classes/update', data)
}
