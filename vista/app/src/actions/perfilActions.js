import {
  TRAER_PERFIL,
  TRAER_PERFIL_STATUS,
  PERFIL_CANCIONES,
  PERFIL_DISCOS,
  PERFIL_LISTAS,
  PERFIL_PUBLICACIONES,
  PERFIL_RESTORE,
  PERFIL_SEGUIDORES,
  PERFIL_SEGUIDOS,
  PERFIL_USUARIO
} from './types';

export const cargarPerfil = (id) => ({type: TRAER_PERFIL, id});
export const loadingStatus = (isLoading) => ({type: TRAER_PERFIL_STATUS, isLoading});
export const setCancionesPerfil = (canciones) => ({type: PERFIL_CANCIONES, canciones});
export const setDiscosPerfil  = (discos) => ({type: PERFIL_DISCOS, discos});
export const setListasPerfil  = (listas) => ({type: PERFIL_LISTAS, listas});
export const setPublicacionesPerfil = (publicaciones) => ({type: PERFIL_PUBLICACIONES, publicaciones});
export const setSeguidoresPerfil  = (seguidores) => ({type: PERFIL_SEGUIDORES, seguidores});
export const setSeguidosPerfil  = (seguidos) => ({type: PERFIL_SEGUIDOS, seguidos});
export const restorePerfil = () => ({type: PERFIL_RESTORE});
export const setUsuarioPerfil = (usuario) => ({type: PERFIL_USUARIO, usuario});
