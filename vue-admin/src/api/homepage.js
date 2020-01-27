import axios from 'axios'
import service from '../utils/request'

export function getHomeTotal() {
  return axios.post('/homepage/hometotal')
}

export function getHomeDetailItem() {
  return axios.post('/homepage/detailItem')
}

export function getRank() {
  return axios.post('/homepage/investmentRank')
}

export function getUsersCount() {
  return service.get('/api/users/count')
}

export function getStudentsCount() {
  return service.get('/api/students/count')
}

export function getTeachersCount() {
  return service.get('/api/teachers/count')
}
