import service from '@/utils/request'

export function pageQuestions(params) {
  return service({
    method: 'get',
    url: '/questions/page',
    params: params
  })
}

export function deleteQuestion(id) {
  return service.delete('/questions/delete/' + id)
}

export function getQuestion(id) {
  return service.get('/questions/' + id)
}
