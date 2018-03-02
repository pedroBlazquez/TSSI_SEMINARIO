import {
  ABRIR_MODAL,
  CERRAR_MODAL,
  SET_LISTAS,
  GET_LISTAS,
  PUSH_SONG_TO_LIST,
  ALTA_LISTA,
  ELIMINAR_LISTA,
  MOD_LISTA
} from './types';

export const mostrarListas = (cancion) => ({type: ABRIR_MODAL, cancion});

export const ocultarListas = () => ({type: CERRAR_MODAL});

export const setListas = (listas) => ({type: SET_LISTAS, listas});

export const getListas = () => ({type: GET_LISTAS});

export const pushSongToList = (cancion, lista) => ({
  type: PUSH_SONG_TO_LIST,
  idCancion: cancion.id.toString(),
  idLista: lista.id.toString()
});

export const crearLista = (lista) => ({type: ALTA_LISTA, lista});

export const eliminarLista = (id) => ({type: ELIMINAR_LISTA, id});

export const modificarLista = lista => ({type: MOD_LISTA, lista});
