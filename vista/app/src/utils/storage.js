const storage = localStorage;

export function setAuthToken (token) {
  storage.setItem('auth', token);
}

export function getAuthToken () {
  return storage.getItem('auth');
}
