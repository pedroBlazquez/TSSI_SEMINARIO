import {createSelector} from 'reselect';
import {USUARIO_OYENTE} from '../utils/constants';
import {getCurrentUser} from './login';

export const getPerfil = (state) => state.perfilReducer;

export const getUsuarioPerfil = createSelector(
  [getPerfil],
  perfil => perfil.usuario
);

export const getCancionesPerfil = createSelector(
  [getPerfil, getUsuarioPerfil],
  (perfil, user) => appendUser(perfil.canciones, user)
);

export const getDiscosPerfil = createSelector(
  [getPerfil, getUsuarioPerfil],
  (perfil, user )=> appendUser(perfil.discos, user)
);

export const getAlbumesPerfil = createSelector(
  [getPerfil, getUsuarioPerfil],
  (perfil, user) => appendUser(perfil.albumes, user)
);

export const getListasPerfil = createSelector(
  [getPerfil],
  perfil => perfil.listas
);

export const getEventosPerfil = createSelector(
  [getPerfil, getUsuarioPerfil],
  (perfil, user)=> appendUser(perfil.eventos, user)
);


export const getLoadingPerfil = createSelector(
  [getPerfil],
  (perfil) => perfil.loadingStatus
);

export const getSeguidosPerfil = createSelector(
  [getPerfil],
  perfil => perfil.seguidos
);

export const getSeguidoresPerfil = createSelector(
  [getPerfil],
  perfil => perfil.seguidores
);

export const getPublicacionesPerfil = createSelector(
  [getPerfil, getUsuarioPerfil],
  (perfil, user) => appendUser(perfil.publicaciones, user)
);

export const getCompartidosPerfil = createSelector(
  [getPerfil, getUsuarioPerfil],
  (perfil, user) => appendUser(perfil.compartidos, user)
);

export const getNovedadesPorTipoUsuario = createSelector(
  [getUsuarioPerfil, getPublicacionesPerfil, getCompartidosPerfil],
  (user, publicaciones, compartidos) => {
    const tipoUsuario = user.usuarioTipo;
    if (!tipoUsuario) return [];
    
    if (tipoUsuario.id !== USUARIO_OYENTE.id) {
      return publicaciones;
    }

    return compartidos.map(c => c[c.object_type.toLowerCase()]);
  }
);

export const getSiguiendoPerfil = createSelector(
  [getCurrentUser, getSeguidoresPerfil],
  (user, seguidores) => {
    const siguiendo = seguidores.find(s => s.id === user.id);

    return !!siguiendo;
  }
);

function appendUser(record, currUser) {
  if (!record) return record;
  if (currUser.usuarioTipo === USUARIO_OYENTE.id) return record;

  return record.map(r => ({...r, artista: {...r.artista, usuario: currUser}}));
}