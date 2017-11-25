import { put, takeEvery, call } from 'redux-saga/effects';
import moment from 'moment';

import {_post, _get, _put, config} from '../utils/api';
import {USUARIO_ARTISTA, USUARIO_BANDA, USUARIO_OYENTE} from '../utils/constants';
import {setAuthToken, getAuthToken} from '../utils/storage';
import {REQUEST_LOGIN, REGISTER_USER, CHECK_TOKEN, UPDATE_USER} from '../actions/types';
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
    yield put(successLogin(usuarioData.data));
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
      const usuario = response.data;
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
      fechaNacimiento: user.usuarioFields.fechaNacimiento.value.format('YYYY-MM-DD'),
      mail: user.usuarioFields.usuario.value,
      password: user.usuarioFields.password.value,
      usuarioTipo: user.usuarioFields.tipoUsuario.value
    };

    if (user.usuarioFields.tipoUsuario.value !== USUARIO_OYENTE.id) {
      payload.artistaForm = {
        nombreFantasia: user.artistaFields.nombreFantasia.value,
        descripcion: user.artistaFields.descripcion.value,
        fechaInicio: user.artistaFields.fechaInicio.value.format('YYYY-MM-DD'),
        generos: user.artistaFields.generos.value
      }

      if (user.usuarioFields.tipoUsuario.value === USUARIO_BANDA.id) {
        payload.integrantesLista = user.artistaFields.integrantes.map(i => {
          return {
            nombre: i.nombre,
            rol: i.rol,
            fechaNacimiento: moment(i.fechaNacimiento).format('YYYY-MM-DD')
          }
        });
      }
    }

    yield _post('/usuario/registro', {...payload});
    yield put(successRegister(true));
  } catch (e) {
    yield put(failRegister(e.message));
  }
}

export function* modificarUsuario ({user}) {
  try {
    const payload = {};
    const headers = config();
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
        payload.integrantesLista = user.artistaFields.integrantes.map(i => {
          return {
            nombre: i.nombre,
            rol: i.rol,
            fechaNacimiento: moment(i.fechaNacimiento).format('DD-MM-YYYY')
          }
        });
      }
    }

    yield call(_put, '/usuario/', {...payload}, headers);
  } catch (e) {
    console.log(e);
  }
}


// Our watcher Saga
export default function* watchLoginSagas () {
  yield takeEvery(REQUEST_LOGIN, requestLoginSaga);
  yield takeEvery(REGISTER_USER, altaUsuario);
  yield takeEvery(UPDATE_USER, modificarUsuario);
  yield takeEvery(CHECK_TOKEN, checkToken);
}