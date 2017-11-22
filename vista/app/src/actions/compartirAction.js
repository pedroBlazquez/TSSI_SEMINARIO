import {SEND_COMPARTIR} from './types';

export const sendCompartir = (id, typeRecord) => ({type: SEND_COMPARTIR, id, typeRecord});