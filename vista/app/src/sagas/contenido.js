import { put, takeEvery, call } from 'redux-saga/effects';

import {_post, _put, _delete, _get, config} from '../utils/api';
import {getAuthToken} from '../utils/storage';
import {GENEROS} from '../utils/constants';
import {ALTA_CANCION, BAJA_CANCION, MOD_CANCION} from '../actions/types';
import {setCancionesPerfil} from '../actions/perfilActions';

// Our worker Saga
export function* altaCancion(action) {
  try {
    const {cancion, idArtista} = action;
    const headers = config();
    cancion.genero = GENEROS.find(g => g.id === cancion.genero).value;

    yield call(_post, '/canciones/', {...cancion}, headers);
    
    // Despues de hacer el alta, buscamos las canciones actualizadas
    const cancionesActualizadas = yield call(_get, `/canciones/getArtista/${idArtista}`, headers);
    yield put(setCancionesPerfil(cancionesActualizadas.data));
  } catch (e) {
    console.log(e);
  }
}

export function* bajaCancion(action) {
  try {
    const {cancion, idArtista} = action;
    const headers = config();
    yield call(_delete, '/canciones/', {data: {idCancion: cancion.toString()}, ...headers});

    // Despues de hacer la baja, buscamos las canciones actualizadas
    const cancionesActualizadas = yield call(_get, `/canciones/getArtista/${idArtista}`, headers);
    yield put(setCancionesPerfil(cancionesActualizadas.data));
  } catch (e) {
    console.log(e);
  }
}

export function* modificarCancion(action) {
  try {
    const {cancion, idArtista} = action;
    const headers = config();
    yield call(_put, '/canciones/', {...cancion}, headers);
    
    // Despues de hacer la baja, buscamos las canciones actualizadas
    const cancionesActualizadas = yield call(_get, `/canciones/getArtista/${idArtista}`, headers);
    yield put(setCancionesPerfil(cancionesActualizadas.data));
  } catch (e) {
    console.log(e);
  }
}


// Our watcher Saga
export default function* watchLoginSagas () {
  yield takeEvery(ALTA_CANCION, altaCancion);
  yield takeEvery(BAJA_CANCION, bajaCancion);
  yield takeEvery(MOD_CANCION, modificarCancion)
}