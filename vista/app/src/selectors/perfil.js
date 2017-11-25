import {createSelector} from 'reselect';
import {USUARIO_OYENTE} from '../utils/constants';
import {getCurrentUser} from './login';

export const getPerfil = (state) => state.perfilReducer;

export const getUsuarioPerfil = createSelector(
  [getPerfil],
  perfil => perfil.usuario
);

export const getCancionesPerfil = createSelector(
  [getPerfil],
  perfil => perfil.canciones
);

export const getDiscosPerfil = createSelector(
  [getPerfil],
  perfil => perfil.discos
);

export const getAlbumesPerfil = createSelector(
  [getPerfil],
  perfil => perfil.albumes
);

export const getListasPerfil = createSelector(
  [getPerfil],
  perfil => perfil.listas
);

export const getEventosPerfil = createSelector(
  [getPerfil],
  perfil => perfil.eventos
);


export const getLoadingPerfil = createSelector(
  [getPerfil],
  perfil => perfil.loadingStatus
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
  [getPerfil],
  perfil => perfil.publicaciones
);

export const getCompartidosPerfil = createSelector(
  [getPerfil],
  perfil => perfil.compartidos
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