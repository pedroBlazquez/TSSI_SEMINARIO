import { put, takeEvery, call } from 'redux-saga/effects';

import {_get, config} from '../utils/api';
import {USUARIO_OYENTE} from '../utils/constants';
import {TRAER_PERFIL} from '../actions/types';
import {
  loadingStatus,
  setCancionesPerfil,
  setDiscosPerfil,
  setListasPerfil,
  setPublicacionesPerfil,
  setSeguidoresPerfil,
  setSeguidosPerfil,
  setUsuarioPerfil,
  setAlbumesPerfil
} from '../actions/perfilActions'; 

// Our worker Saga
export function* traerPerfil(action) {
  try {
    const headers = config();
    const idPerfil = action.id;
    yield put(loadingStatus(true));

    // Traemos los datos del usuario que estamos viendo actualmente
    const usuario = yield call(_get, `/usuario/${idPerfil}`, headers);
    const idUsuario = usuario.data.id
    const esArtista = usuario.data.tipoUsuario !== USUARIO_OYENTE.id;

    // Llamadas comunes a todos los tipos de usuario
    const seguidos = yield call(_get, `/usuario/getSeguidos/${idUsuario}`, headers);
    const seguidores = yield call(_get, `/usuario/getSeguidores/${idUsuario}`, headers );
    const listas = yield call(_get, `/listas/getUsuario/${idUsuario}`, headers);
    
    if (esArtista) {
      // Llamadas relacionadas a artistas
      const artista = yield call(_get, `/artistas/getbyUsuario/${idUsuario}`, headers);
      const idArtista = artista.data.id;
      const publicaciones = yield call(_get, `/publicaciones/getArtista/${idArtista}`, headers);
      const canciones = yield call(_get, `/canciones/getArtista/${idArtista}`, headers);
      const discos = yield call(_get, `/discos/getArtista/${idArtista}`, headers);
      const albumes = yield call(_get, `/albums/getArtista/${idArtista}`, headers);
      const eventos = yield call(_get, `/eventos/getArtista/${idArtista}`, headers);

      yield put(setPublicacionesPerfil(publicaciones.data));
      yield put(setCancionesPerfil(canciones.data));
      yield put(setDiscosPerfil(discos.data));
      yield put(setAlbumesPerfil(albumes.data));
      // FALTA EVENTOS
    } else {
      const publicaciones = yield call(_get, `/compartir/getCompartidos/${idUsuario}`, headers);
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