import { put, takeEvery } from 'redux-saga/effects';

import {REQUEST_LOGIN} from '../actions/types';
import {errorLogin, successLogin} from '../actions/loginActions'; 

// Our worker Saga
export function* requestLoginSaga(action) {
  try {
    // Here should be async request
    if (action.user === 'mail@mail.com' && action.pass === '12345') {
      yield put(successLogin());
    } else {
      throw new Error('Usuario o password incorrectos');
    }
  } catch (e) {
    yield put(errorLogin(e.message));
  }
}

// Our watcher Saga
export default function* watchLoginSagas () {
  yield takeEvery(REQUEST_LOGIN, requestLoginSaga)
}