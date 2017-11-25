import {ABRIR_MODAL, CERRAR_MODAL, SET_LISTAS, GET_LISTAS, PUSH_SONG_TO_LIST} from './types';

export const mostrarListas = (idCancion) => ({type: ABRIR_MODAL, idCancion});

export const ocultarListas = () => ({type: CERRAR_MODAL});

export const setListas = (listas) => ({type: SET_LISTAS, listas});

export const getListas = () => ({type: GET_LISTAS});

export const pushSongToList = (idCancion, idLista) => ({type: PUSH_SONG_TO_LIST, idCancion, idLista});
