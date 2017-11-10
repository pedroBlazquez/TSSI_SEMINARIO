import { put, takeEvery, call } from 'redux-saga/effects';

import {_post, _get, config} from '../utils/api';
import {USUARIO_ARTISTA, USUARIO_BANDA, USUARIO_OYENTE} from '../utils/constants';
import {setAuthToken} from '../utils/storage';
import {REQUEST_LOGIN, REGISTER_USER} from '../actions/types';
import {errorLogin, successLogin} from '../actions/loginActions'; 
import {failRegister, successRegister} from '../actions/registerActions'; 

// Our worker Saga
export function* requestLoginSaga(action) {
  try {
    // Here should be async request
    const response = yield call(_post, '/login', {mail: action.user, password: action.pass});
    const {token, usuario} = response.data;
    yield call(setAuthToken, token);
    if (usuario.tipoUsuario !== USUARIO_OYENTE.id) {
      const detalleArtista = yield call(_get, `/artistas/getbyUsuario/${usuario.id}`, config());
      const usuarioConDetalle = {...usuario, idArtista: detalleArtista.data.id};
      yield put(successLogin(usuarioConDetalle));
    } else {
      yield put(successLogin(usuario));
    }
  } catch (e) {
    yield put(errorLogin('Usuario o password incorrectos'));
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
}