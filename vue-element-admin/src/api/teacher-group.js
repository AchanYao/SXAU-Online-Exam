import service from '@/utils/request'

export function pageTeacherGroup(params) {
  return service({
    params: params,
    url: '/teacher-groups/page',
    method: 'get'
  })
}

export function getGroupByCourse(courseId) {
  return service.get('/teacher-groups/one?course-id=' + courseId)
}

export function getGroupById(id) {
  return service.get('/teacher-groups/' + id)
}

export function addGroup(data) {
  return service({
    method: 'post',
    data: data,
    url: '/teacher-groups/new'
  })
}

export function updateGroup(data) {
  return service({
    method: 'put',
    data: data,
    url: '/teacher-groups/update'
  })
}

export function deleteGroup(id) {
  return service({
    method: 'delete',
    url: '/teacher-groups/delete/' + id
  })
}
