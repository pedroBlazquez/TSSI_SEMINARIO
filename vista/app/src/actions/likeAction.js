import {SEND_LIKE, SEGUIR_USUARIO} from './types';

export const sendLike = (id, typeRecord) => ({type: SEND_LIKE, id, typeRecord});
export const seguirUsuario = (id, typeRecord) => ({type: SEGUIR_USUARIO, id, typeRecord});