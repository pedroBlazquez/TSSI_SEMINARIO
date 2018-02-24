import { put, takeEvery, call } from 'redux-saga/effects';

import {_get, _post, config} from '../utils/api';
import {agregarArtista} from '../utils/utils';
import {USUARIO_OYENTE} from '../utils/constants';
import {TRAER_PERFIL, GUARDAR_FOTO_PERFIL} from '../actions/types';
import {
  loadingStatus,
  setCancionesPerfil,
  setCompartidosPerfil,
  setDiscosPerfil,
  setListasPerfil,
  setPublicacionesPerfil,
  setSeguidoresPerfil,
  setSeguidosPerfil,
  setUsuarioPerfil,
  setAlbumesPerfil,
  setEventosPerfil,
  actualizarFotoPerfil
} from '../actions/perfilActions'; 

// Our worker Saga
export function* traerPerfil(action) {
  try {
    const headers = config();
    const idPerfil = action.id;
    yield put(loadingStatus(true));

    // Traemos los datos del usuario que estamos viendo actualmente
    const usuarioData = yield call(_get, `/usuario/${idPerfil}`, headers);
    const usuario = usuarioData.data;
    const idUsuario = usuario.id;
    const esArtista = usuario.usuarioTipo.id !== USUARIO_OYENTE.id;

    // Llamadas comunes a todos los tipos de usuario
    const seguidos = yield call(_get, `/usuario/getSeguidos/${idUsuario}`, headers);
    const seguidores = yield call(_get, `/usuario/getSeguidores/${idUsuario}`, headers );
    const listas = yield call(_get, `/listas/getUsuario/${idUsuario}`, headers);
    
    if (esArtista) {
      // Llamadas relacionadas a artistas
      const artista = usuario.artista[0]
      const idArtista = artista.id;
      const publicaciones = yield call(_get, `/publicaciones/getArtista/${idArtista}`, headers);
      const canciones = yield call(_get, `/canciones/getArtista/${idArtista}`, headers);
      const discos = yield call(_get, `/discos/getArtista/${idArtista}`, headers);
      const albumes = yield call(_get, `/albums/getArtista/${idArtista}`, headers);
      const eventos = yield call(_get, `/eventos/getArtista/${idArtista}`, headers);

      yield put(setPublicacionesPerfil(agregarArtista(publicaciones.data, artista)));
      yield put(setCancionesPerfil(agregarArtista(canciones.data, artista)));
      yield put(setDiscosPerfil(agregarArtista(discos.data, artista)));
      yield put(setAlbumesPerfil(agregarArtista(albumes.data, artista)));
      yield put(setEventosPerfil(agregarArtista(eventos.data, artista)));
    } else {
      const compartidos = yield call(_get, `/compartir/getCompartidos/${idUsuario}`, headers);
      yield put(setCompartidosPerfil(compartidos.data));
    }

    yield put(setSeguidoresPerfil(seguidores.data));
    yield put(setSeguidosPerfil(seguidos.data));
    yield put(setListasPerfil(listas.data));
    yield put(setUsuarioPerfil(usuario));

  } catch (e) {
    console.log(e);
  } finally {
    yield put(loadingStatus(false));
  }
}

export function* guardarFotoPerfilSaga (action) {
  try {
    const headers = config();
    yield call(
      _post,
      '/usuario/guardarFoto',
      {url: action.url},
      headers
    );
  } catch (e) {
    console.log(e);
  }
}

// Our watcher Saga
export default function* watchLoginSagas () {
  yield takeEvery(TRAER_PERFIL, traerPerfil);
  yield takeEvery(GUARDAR_FOTO_PERFIL, guardarFotoPerfilSaga);
}