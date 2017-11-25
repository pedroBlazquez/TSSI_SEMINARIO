import {
  ABRIR_MODAL,
  CERRAR_MODAL,
  SET_LISTAS,
  GET_LISTAS,
  PUSH_SONG_TO_LIST,
  ALTA_LISTA,
  ELIMINAR_LISTA
} from './types';

export const mostrarListas = (idCancion) => ({type: ABRIR_MODAL, idCancion});

export const ocultarListas = () => ({type: CERRAR_MODAL});

export const setListas = (listas) => ({type: SET_LISTAS, listas});

export const getListas = () => ({type: GET_LISTAS});

export const pushSongToList = (idCancion, idLista) => ({type: PUSH_SONG_TO_LIST, idCancion, idLista});

export const crearLista = (lista) => ({type: ALTA_LISTA, lista});

export const eliminarLista = (id) => ({type: ELIMINAR_LISTA, id});
