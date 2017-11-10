import { put, takeEvery, call, select } from 'redux-saga/effects';

import {_post, _put, _delete, _get, config} from '../utils/api';
import {getAuthToken} from '../utils/storage';
import {GENEROS} from '../utils/constants';
import {
  ALTA_CANCION,
  BAJA_CANCION,
  MOD_CANCION,
  ALTA_DISCO,
  BAJA_DISCO,
  MOD_DISCO,
  ALTA_ALBUM,
  BAJA_ALBUM,
  MOD_ALBUM
} from '../actions/types';
import {setCancionesPerfil, setDiscosPerfil, setAlbumesPerfil} from '../actions/perfilActions';
import {getCurrentUser} from '../selectors/login';

// Our worker Saga
export function* altaCancion(action) {
  try {
    const {cancion} = action;
    const user = yield select(getCurrentUser);
    const headers = config();
    cancion.genero = GENEROS.find(g => g.id === cancion.genero).value;

    yield call(_post, '/canciones/', {...cancion}, headers);
    
    // Despues de hacer el alta, buscamos las canciones actualizadas
    const cancionesActualizadas = yield call(_get, `/canciones/getArtista/${user.idArtista}`, headers);
    yield put(setCancionesPerfil(cancionesActualizadas.data));
  } catch (e) {
    console.log(e);
  }
}

export function* bajaCancion(action) {
  try {
    const {cancion} = action;
    const user = yield select(getCurrentUser);
    const headers = config();
    yield call(_delete, '/canciones/', {data: {idCancion: cancion.toString()}, ...headers});

    // Despues de hacer la baja, buscamos las canciones actualizadas
    const cancionesActualizadas = yield call(_get, `/canciones/getArtista/${user.idArtista}`, headers);
    yield put(setCancionesPerfil(cancionesActualizadas.data));
  } catch (e) {
    console.log(e);
  }
}

export function* modificarCancion(action) {
  try {
    const {cancion} = action;
    const user = yield select(getCurrentUser);
    const headers = config();
    yield call(_put, '/canciones/', {...cancion}, headers);
    
    // Despues de hacer la baja, buscamos las canciones actualizadas
    const cancionesActualizadas = yield call(_get, `/canciones/getArtista/${user.idArtista}`, headers);
    yield put(setDiscosPerfil(cancionesActualizadas.data));
  } catch (e) {
    console.log(e);
  }
}

export function* altaDisco(action) {
  try {
    const {disco} = action;
    const user = yield select(getCurrentUser);
    const headers = config();
    const payload = {
      nombre: disco.nombre,
      genero: GENEROS.find(g => g.id === disco.genero).value,
      canciones: disco.canciones.map(c => c.id)
    }
    yield call(_post, '/discos/', {...payload}, headers);
    
    // Despues de hacer la baja, buscamos las canciones actualizadas
    const discosActualizados = yield call(_get, `/discos/getArtista/${user.idArtista}`, headers);
    yield put(setDiscosPerfil(discosActualizados.data));
  } catch (e) {
    console.log(e);
  }
}

export function* bajaDisco(action) {
  try {
    const {disco} = action;
    const user = yield select(getCurrentUser);
    const headers = config();
    yield call(_delete, '/discos/', {data: {idDisco: disco.toString()}, ...headers});

    // Despues de hacer la baja, buscamos las canciones actualizadas
    const discosActualizados = yield call(_get, `/discos/getArtista/${user.idArtista}`, headers);
    yield put(setDiscosPerfil(discosActualizados.data));
  } catch (e) {
    console.log(e);
  }
}

export function* altaAlbum(action) {
  try {
    const {album} = action;
    const user = yield select(getCurrentUser);
    const headers = config();
    const payload = {
      nombre: album.nombre,
      discos: album.discos.map(c => c.id)
    }
    yield call(_post, '/albums/', {...payload}, headers);
    
    const albumsActualizados = yield call(_get, `/albums/getArtista/${user.idArtista}`, headers);
    yield put(setAlbumesPerfil(albumsActualizados.data));
  } catch (e) {
    console.log(e);
  }
}

export function* bajaAlbum(action) {
  try {
    const {album} = action;
    const user = yield select(getCurrentUser);
    const headers = config();
    yield call(_delete, '/albums/', {data: {idAlbum: album.toString()}, ...headers});

    const albumsActualizados = yield call(_get, `/albums/getArtista/${user.idArtista}`, headers);
    yield put(setAlbumesPerfil(albumsActualizados.data));
  } catch (e) {
    console.log(e);
  }
}


// Our watcher Saga
export default function* watchLoginSagas () {
  yield takeEvery(ALTA_CANCION, altaCancion);
  yield takeEvery(BAJA_CANCION, bajaCancion);
  yield takeEvery(MOD_CANCION, modificarCancion);
  yield takeEvery(ALTA_DISCO, altaDisco);
  yield takeEvery(BAJA_DISCO, bajaDisco);
  yield takeEvery(ALTA_ALBUM, altaAlbum);
  yield takeEvery(BAJA_ALBUM, bajaAlbum);
}