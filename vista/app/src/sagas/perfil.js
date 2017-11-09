import { put, takeEvery, call } from 'redux-saga/effects';

import {_get, config} from '../utils/api';
import {getAuthToken} from '../utils/storage';
import {GENEROS} from '../utils/constants';
import {TRAER_PERFIL} from '../actions/types';
import {
  loadingStatus,
  setCancionesPerfil,
  setDiscosPerfil,
  setListasPerfil,
  setPublicacionesPerfil,
  setSeguidoresPerfil,
  setSeguidosPerfil,
  setUsuarioPerfil
} from '../actions/perfilActions'; 
import {failRegister, successRegister} from '../actions/registerActions';

// Our worker Saga
export function* traerPerfil(action) {
  try {
    const headers = config();
    const idPerfil = action.id;
    yield put(loadingStatus(true));
    const usuario = {}; //aca va la llamada al usuario
    const idUsuario = 8; // CAMBIAR
    const esArtista = true; // CAMBIAR

    // LLAMADAS
    const seguidos = yield call(_get, `/usuario/getSeguidos/${idUsuario}`, headers);
    const seguidores = yield call(_get, `/usuario/getSeguidores/${idUsuario}`, headers );
    const listas = yield call(_get, `/listas/getUsuario/${idUsuario}`, headers);
    
    if (esArtista) {
      const idArtista = 3; //CAMBIAR
      const publicaciones = yield call(_get, `/publicaciones/getArtista/${idArtista}`, headers);
      const canciones = yield call(_get, `/canciones/getArtista/${idArtista}`, headers);
      const discos = yield call(_get, `/discos/getArtista/${idArtista}`, headers);
      const albums = yield call(_get, `/albums/getArtista/${idArtista}`, headers);
      const eventos = yield call(_get, `/eventos/getArtista/${idArtista}`, headers);

      yield put(setPublicacionesPerfil(publicaciones.data));
      yield put(setCancionesPerfil(canciones.data));
      yield put(setDiscosPerfil(discos.data));
      // FALTA ALBUMS Y EVENTOS
    } else {
      const publicaciones = yield call(_get, '/compartidos/');
      yield put(setPublicacionesPerfil(publicaciones.data));
    }

    yield put(setSeguidoresPerfil(seguidores.data));
    yield put(setSeguidosPerfil(seguidos.data));
    yield put(setListasPerfil(listas.data));

  } catch (e) {
    console.log(e);
  } finally {
    yield put(loadingStatus(false));
  }
}


// Our watcher Saga
export default function* watchLoginSagas () {
  yield takeEvery(TRAER_PERFIL, traerPerfil);
}