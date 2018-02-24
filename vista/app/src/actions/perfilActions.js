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
  PERFIL_USUARIO,
  PERFIL_ALBUMES,
  PERFIL_EVENTOS,
  PERFIL_COMPARTIDO,
  GUARDAR_FOTO_PERFIL,
  ACTUALIZAR_FOTO_PERFIL
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
export const setAlbumesPerfil = (albumes) => ({type: PERFIL_ALBUMES, albumes});
export const setEventosPerfil = (eventos) => ({type: PERFIL_EVENTOS, eventos});
export const setCompartidosPerfil = (compartidos) => ({type: PERFIL_COMPARTIDO, compartidos});
export const guardarFotoPerfil = (url) => ({type: GUARDAR_FOTO_PERFIL, url});
export const actualizarFotoPerfil = (url) => ({type: ACTUALIZAR_FOTO_PERFIL, url});
