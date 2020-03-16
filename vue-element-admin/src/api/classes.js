import service from '@/utils/request'

export function allClasses() {
  return service.get('/classes/all')
}

export function pageClasses(params) {
  return service({
    method: 'get',
    params: params,
    url: '/classes/page'
  })
}

export function deleteClassById(id) {
  return service.delete('/classes/delete/' + id)
}

export function addClass(data) {
  return service.post('/classes/add', data)
}

export function updateClass(data) {
  return service.put('/classes/update', data)
}
