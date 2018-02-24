import {SEND_COMPARTIR, DELETE_COMPARTIR} from './types';

export const sendCompartir = (id, typeRecord) => ({type: SEND_COMPARTIR, id, typeRecord});

export const deleteCompartirFromTree = (id, typeRecord) => ({type: DELETE_COMPARTIR, id, typeRecord});
