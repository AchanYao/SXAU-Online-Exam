import service from '@/utils/request'

export function usersCount() {
  return service.get('/users/count')
}

export function coursesCount() {
  return service.get('/courses/count')
}

export function clazzCount() {
  return service.get('/classes/count')
}

export function questionsCount() {
  return service.get('/questions/count')
}
