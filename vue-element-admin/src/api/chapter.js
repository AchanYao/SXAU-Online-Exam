import service from '@/utils/request'

export function allChapters() {
  return service.get('/chapters/all')
}

export function updateChapter(data) {
  return service({
    method: 'put',
    data: data,
    url: '/chapters/update'
  })
}

export function deleteChapter(id) {
  return service({
    method: 'delete',
    url: `/chapters/delete/${id}`
  })
}

export function addChapter(data) {
  return service({
    method: 'post',
    url: '/chapters/add',
    data: data
  })
}

export function listChaptersByCourse(courseId) {
  return service.get('/chapters/list/courses/' + courseId)
}
