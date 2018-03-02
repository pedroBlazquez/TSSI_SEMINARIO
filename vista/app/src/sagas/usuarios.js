import { put, takeEvery, call } from 'redux-saga/effects';
import {message} from 'antd';
import moment from 'moment';

import {_post, _get, _put, _delete, config} from '../utils/api';
import {USUARIO_ARTISTA, USUARIO_BANDA, USUARIO_OYENTE} from '../utils/constants';
import {setAuthToken, getAuthToken} from '../utils/storage';
import {REQUEST_LOGIN, REGISTER_USER, CHECK_TOKEN, UPDATE_USER, BAJA_USUARIO} from '../actions/types';
import {errorLogin, successLogin} from '../actions/loginActions'; 
import {failRegister, successRegister} from '../actions/registerActions'; 
import {logOut} from '../actions/loginActions';
import { setUsuarioPerfil } from '../actions/perfilActions';

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
    const existeMail = yield _post('/usuario/registro/checkMail', {mail: user.usuarioFields.usuario.value});
    if (existeMail.data) { throw new Error ("Ya existe el mail");}

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
            apellido: i.apellido,
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
            rol: i.rol.id,
            apellido: i.apellido,
            fechaNacimiento: moment(i.fechaNacimiento).format('YYYY-MM-DD')
          }
        });
      }
    }

    yield call(_put, '/usuario/', {...payload}, headers);
    const usuario = yield call(_get, `/usuario/0`, headers);
    yield put(setUsuarioPerfil(usuario.data));
    
    yield call(message.success, 'Actualizado con éxito', 1);
  } catch (e) {
    yield call(message.warn, 'Un error inesperado ocurrió', 1);
  }
}

export function* bajaUsuario () {
  try {
    yield call(_delete, '/usuario/', config());
  } catch (e) {
    console.log(e);
  } finally {
    yield put(logOut);
  }
}


// Our watcher Saga
export default function* watchLoginSagas () {
  yield takeEvery(REQUEST_LOGIN, requestLoginSaga);
  yield takeEvery(REGISTER_USER, altaUsuario);
  yield takeEvery(UPDATE_USER, modificarUsuario);
  yield takeEvery(CHECK_TOKEN, checkToken);
  yield takeEvery(BAJA_USUARIO, bajaUsuario)
}