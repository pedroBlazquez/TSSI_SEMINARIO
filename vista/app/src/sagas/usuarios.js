import { put, takeEvery, call } from 'redux-saga/effects';

import {_post, _get, config} from '../utils/api';
import {USUARIO_ARTISTA, USUARIO_BANDA, USUARIO_OYENTE} from '../utils/constants';
import {setAuthToken, getAuthToken} from '../utils/storage';
import {REQUEST_LOGIN, REGISTER_USER, CHECK_TOKEN} from '../actions/types';
import {errorLogin, successLogin} from '../actions/loginActions'; 
import {failRegister, successRegister} from '../actions/registerActions'; 

// Our worker Saga
export function* requestLoginSaga(action) {
  try {
    // Here should be async request
    const response = yield call(_post, '/login', {mail: action.user, password: action.pass});
    const {token, usuario} = response.data;
    yield call(setAuthToken, token);
    const usuarioData = yield call(_get, `/usuario/${usuario.id}`, config());
    yield put(successLogin(usuarioData.data[0]));
  } catch (e) {
    yield put(errorLogin('Usuario o password incorrectos'));
  }
}

export function* checkToken(action) {
  try {
    // Here should be async request
    const token = yield call(getAuthToken);
    if (token) {
      const headers = config();
      const response = yield call(_get, '/usuario/0', headers);
      const usuario = response.data[0];
      yield put(successLogin(usuario));
    }
  } catch (e) {
    console.log(e);
  }
}

export function* altaUsuario ({user}) {
  try {
    const mailDisponible = yield _post('/usuario/registro/checkMail', {mail: user.usuarioFields.usuario.value});
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

    yield _post('/usuario/registro', {...payload});
    yield put(successRegister(true));
  } catch (e) {
    yield put(failRegister(e.message));
  }
}

export function* checkMail ({user}) {
  try {
    yield _post('/usuario/registro/checkMail', {mail: user.usuario});

  } catch (e) {

  }
}  

// Our watcher Saga
export default function* watchLoginSagas () {
  yield takeEvery(REQUEST_LOGIN, requestLoginSaga);
  yield takeEvery(REGISTER_USER, altaUsuario);
  yield takeEvery(CHECK_TOKEN, checkToken);
}