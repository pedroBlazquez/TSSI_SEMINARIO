import { put, takeEvery, call, select } from 'redux-saga/effects';

import {_post, _get, config} from '../utils/api';
import {agregarArtista} from '../utils/utils';
import {NUEVA_PUBLICACION} from '../actions/types';
import {setPublicacionesPerfil} from '../actions/perfilActions';
import {getCurrentUser} from '../selectors/login';


export function* realizarPublicacion (action) {
  try {
    const nuevaPublicacion = {
      texto: action.publicacion
    };
    const headers = config();
    const user = yield select(getCurrentUser);
    const artista = user.artista[0];
    const idArtista = artista.id;

    yield call(_post, '/publicaciones/', {...nuevaPublicacion}, headers);

    const publicaciones = yield call(_get, `/publicaciones/getArtista/${idArtista}`, headers);
    yield put(setPublicacionesPerfil(agregarArtista(publicaciones.data, artista)));
  } catch (e){
    console.log(e);
  }
}


// Our watcher Saga
export default function* watchLoginSagas () {
  yield takeEvery(NUEVA_PUBLICACION, realizarPublicacion);
}