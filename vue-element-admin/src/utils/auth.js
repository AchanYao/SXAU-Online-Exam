import { session, storage } from '@/utils/storage'

const _ = require('lodash')

/**
 * @param {'access_token' | 'refresh_token'} name
 */
export function getToken(name) {
  switch (name) {
    case 'refresh_token':
      return storage.get(name)
    case 'access_token':
      return session.get(name)
  }
}

/**
 * @param {'access_token' | 'refresh_token'} name
 * @param {string} token
 */
export function setToken(name, token) {
  switch (name) {
    case 'access_token':
      session.set(name, token)
      break
    case 'refresh_token':
      storage.set(name, token)
      break
    default: return
  }
}

/**
 * @param {'access_token' | 'refresh_token'} name
 */
export function removeToken(name) {
  switch (name) {
    case 'refresh_token':storage.remove(name)
      break
    case 'access_token':session.remove(name)
  }
}

export function loginOperate(data) {
  _.forIn(data, (value, key) => setToken(key, value))
}

export function logoutOperate() {
  removeToken('access_token')
  removeToken('refresh_token')
}
