import {createSelector} from 'reselect';

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

export const getLoadingPerfil = createSelector(
  [getPerfil],
  perfil => perfil.loadingStatus
);