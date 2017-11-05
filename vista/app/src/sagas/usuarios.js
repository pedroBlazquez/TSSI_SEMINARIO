import { put, takeEvery } from 'redux-saga/effects';

import {post} from '../utils/api';
import {REQUEST_LOGIN, REGISTER_USER} from '../actions/types';
import {errorLogin, successLogin} from '../actions/loginActions'; 
import {failRegister, successRegister} from '../actions/registerActions'; 

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

export function* altaUsuario ({user}) {
  try {
    const mailExists = yield post('/usuario/registro/checkMail', {mail: user.usuario});
    if (mailExists) {
      throw new Error ("Ya existe el mail");
    }
    yield post('/usuario/registro', {
      "mainForm": {
        "nombre": user.nombre,
        "apellido": user.apellido,
        "fechaNacimiento": user.fechaNacimiento.format('DD-MM-YYYY'),
        "mail": user.usuario,
        "password": user.password,
        "usuarioTipo": 1
      }
    });
    yield put(successRegister(true));
  } catch (e) {
    yield put(failRegister(e.message));
  }
}

export function* checkMail ({user}) {
  try {
    yield post('/usuario/registro/checkMail', {mail: user.usuario});

  } catch (e) {

  }
}  

// Our watcher Saga
export default function* watchLoginSagas () {
  yield takeEvery(REQUEST_LOGIN, requestLoginSaga);
  yield takeEvery(REGISTER_USER, altaUsuario);
}