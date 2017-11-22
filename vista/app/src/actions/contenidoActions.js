import {
  ALTA_ALBUM,
  ALTA_DISCO,
  ALTA_CANCION,
  MOD_ALBUM,
  MOD_DISCO,
  MOD_CANCION,
  BAJA_ALBUM,
  BAJA_DISCO,
  BAJA_CANCION,
  ALTA_EVENTO,
  MOD_EVENTO,
  BAJA_EVENTO
} from './types';

export const altaAlbum = (album) => ({type: ALTA_ALBUM, album});
export const modAlbum = (album) => ({type: MOD_ALBUM, album});
export const bajaAlbum = (album) => ({type: BAJA_ALBUM, album});
export const altaDisco = (disco) => ({type: ALTA_DISCO, disco});
export const modDisco = (disco) => ({type: MOD_DISCO, disco});
export const bajaDisco = (disco) => ({type: BAJA_DISCO, disco});
export const altaCancion = (cancion) => ({type: ALTA_CANCION, cancion});
export const modCancion = (cancion) => ({type: MOD_CANCION, cancion});
export const bajaCancion = (cancion) => ({type: BAJA_CANCION, cancion});
export const altaEvento = (evento) => ({type: ALTA_EVENTO, evento});
export const bajaEvento = (evento) => ({type: BAJA_EVENTO, evento});
export const modEvento = (evento) => ({type: MOD_EVENTO, evento});
