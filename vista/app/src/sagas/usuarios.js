import { put, takeEvery } from 'redux-saga/effects';

import {post} from '../utils/api';
import {USUARIO_ARTISTA, USUARIO_BANDA, USUARIO_OYENTE} from '../utils/constants';
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
    const mailDisponible = yield post('/usuario/registro/checkMail', {mail: user.usuarioFields.usuario.value});
    if (!mailDisponible) { throw new Error ("Ya existe el mail");}

    const payload = {};
    payload.usuarioForm = {
      nombre: user.usuarioFields.nombre.value,
      apellido: user.usuarioFields.apellido.value,
      fechaNacimiento: user.usuarioFields.fechaNacimiento.value.format('DD-MM-YYYY'),
      mail: user.usuarioFields.usuario.value,
      password: user.usuarioFields.password.value,
      usuarioTipo: user.usuarioFields.tipoUsuario.value
    };

    if (user.usuarioFields.tipoUsuario.value !== USUARIO_OYENTE.id) {
      payload.artistaForm = {
        nombreFantasia: user.artistaFields.nombreFantasia.value,
        descripcion: user.artistaFields.descripcion.value,
        fechaInicio: user.artistaFields.fechaInicio.value.format('DD-MM-YYYY'),
        generos: user.artistaFields.generos.value
      }

      if (user.usuarioFields.tipoUsuario.value === USUARIO_BANDA.id) {
        payload.artistaForm.integrantes = user.artistaFields.integrantes;
      }
    }

    yield post('/usuario/registro', {...payload});
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