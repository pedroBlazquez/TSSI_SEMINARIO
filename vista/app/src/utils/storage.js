const storage = localStorage;

export function setAuthToken (token) {
  storage.setItem('auth', `Bearer ${token}`);
}

export function getAuthToken () {
  return storage.getItem('auth');
}

export function unsetAuthToken () {
  storage.removeItem('auth');
}
