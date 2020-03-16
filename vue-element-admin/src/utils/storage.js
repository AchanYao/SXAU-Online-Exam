class LocalStorage {
  /**
   * @param {string} key
   * @param {*} value
   */
  set(key, value) {
    localStorage.setItem(key, value)
  }

  /**
   * @param {string} key
   * @returns {string}
   */
  get(key) {
    return localStorage.getItem(key)
  }

  /**
   * @param {string} key
   * @returns {boolean}
   */
  has(key) {
    return Boolean(this.get(key))
  }

  /**
   * @param {string} key
   */
  remove(key) {
    localStorage.removeItem(key)
  }

  clear() {
    localStorage.clear()
  }
}

class Session {
  /**
   * @param {string} key
   * @param {string} value
   */
  set(key, value) {
    sessionStorage.setItem(key, value)
  }

  /**
   * @param {string} key
   * @returns {string}
   */
  get(key) {
    return sessionStorage.getItem(key)
  }

  /**
   * @param {string} key
   * @returns {boolean}
   */
  has(key) {
    return Boolean(this.get(key))
  }

  /**
   * @param {string} key
   */
  remove(key) {
    sessionStorage.removeItem(key)
  }

  clear() {
    sessionStorage.clear()
  }
}

const storage = new LocalStorage()
const session = new Session()

export { storage, session }
