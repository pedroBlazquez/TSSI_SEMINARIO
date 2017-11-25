import {combineReducers} from 'redux';
import usuario from './usuario';
import seguidos from './seguidos';
import seguidores from './seguidores';
import listas from './listas';
import discos from './discos';
import canciones from './canciones';
import albumes from './albumes';
import loadingStatus from './loadingStatus';
import eventos from './eventos';
import publicaciones from './publicaciones';
import compartidos from './compartidos';
import listasReproduccion from './listasReproduccion';

export default combineReducers({
  loadingStatus,
  usuario,
  seguidores,
  seguidos,
  listas,
  discos,
  canciones,
  albumes,
  eventos,
  publicaciones,
  compartidos,
  listasReproduccion
});
