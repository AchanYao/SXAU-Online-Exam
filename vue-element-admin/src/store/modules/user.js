import { getInfo, login, logout, refreshToken } from '@/api/user'
import { getToken, loginOperate, logoutOperate } from '@/utils/auth'
import router, { resetRouter } from '@/router'

const state = {
  accessToken: getToken('access_token'),
  refreshToken: getToken('refresh_token'),
  name: '',
  avatar: '',
  introduction: '',
  roles: []
}

const mutations = {
  SET_TOKEN: (state, data) => {
    state.accessToken = data['access_token']
    state.refreshToken = data['refresh_token']
  },
  SET_INTRODUCTION: (state, introduction) => {
    state.introduction = introduction
  },
  SET_NAME: (state, name) => {
    state.name = name
  },
  SET_AVATAR: (state, avatar) => {
    state.avatar = avatar
  },
  SET_ROLES: (state, roles) => {
    state.roles = roles
  }
}

const actions = {
  // user login
  login({ commit }, userInfo) {
    const { username, password } = userInfo
    return new Promise((resolve, reject) => {
      login({ username: username.trim(), password: password }).then(response => {
        const { data } = response
        commit('SET_TOKEN', data)
        loginOperate(data)
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // get user info
  getInfo({ commit, state }) {
    return new Promise((resolve, reject) => {
      getInfo(state.token).then(response => {
        const { data } = response

        if (!data) {
          reject('Verification failed, please Login again.')
        }

        const { roles, username, introduction } = data

        // roles must be a non-empty array
        if (!roles || roles.length <= 0) {
          reject('getInfo: roles must be a non-null array!')
        }

        const newRoles = []
        for (const rolesKey in roles) {
          newRoles.push(String(roles[rolesKey].name).replace('ROLE_', ''))
        }
        commit('SET_ROLES', newRoles)
        commit('SET_NAME', username)
        commit('SET_AVATAR', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif')
        commit('SET_INTRODUCTION', introduction)
        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  },

  // user logout
  logout({ commit, state, dispatch }) {
    return new Promise((resolve, reject) => {
      logout(getToken('access_token'), getToken('refresh_token')).then(() => {
        commit('SET_TOKEN', '')
        commit('SET_ROLES', [])
        logoutOperate()
        resetRouter()

        // reset visited views and cached views
        // to fixed https://github.com/PanJiaChen/vue-element-admin/issues/2485
        dispatch('tagsView/delAllViews', null, { root: true })

        router.push('/login')
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // remove token
  refreshToken({ commit }) {
    return new Promise((resolve, reject) => {
      refreshToken(getToken('refresh_token')).then(response => {
        const { data } = response
        commit('SET_TOKEN', data)
        loginOperate(data)
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
