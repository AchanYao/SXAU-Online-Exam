import service from '@/utils/request'

export function getAnswerByQuestion(questionId) {
  return service.get('/answers/question/' + questionId)
}
