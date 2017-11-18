import {SEND_LIKE} from './types';

export const sendLike = (id, typeRecord) => ({type: SEND_LIKE, id, typeRecord});