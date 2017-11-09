import {
  ALTA_ALBUM,
  ALTA_DISCO,
  ALTA_CANCION,
  MOD_ALBUM,
  MOD_DISCO,
  MOD_CANCION,
  BAJA_ALBUM,
  BAJA_DISCO,
  BAJA_CANCION
} from './types';

export const altaAlbum = (album) => ({type: ALTA_ALBUM, album});
export const modificarAlbum = (album) => ({type: MOD_ALBUM, album});
export const bajaAlbum = (album) => ({type: BAJA_ALBUM, album});
export const altaDisco = (disco) => ({type: ALTA_DISCO, disco});
export const modDiscto = (disco) => ({type: MOD_DISCO, disco});
export const bajaDisco = (disco) => ({type: BAJA_DISCO, disco});
export const altaCancion = (cancion, idArtista) => ({type: ALTA_CANCION, cancion, idArtista});
export const modCancion = (cancion, idArtista) => ({type: MOD_CANCION, cancion, idArtista});
export const bajaCancion = (cancion, idArtista) => ({type: BAJA_CANCION, cancion, idArtista});

