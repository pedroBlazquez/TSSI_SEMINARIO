import {combineReducers} from 'redux';
import loginReducer from './loginReducer';
import altaUsuarioStatus from './altaUsuarioStatus';
import perfilReducer from './perfil';
import novedadesReducer from './novedadesReducer';
import busqueda from './busqueda';
import reproductor from './reproductor';
import listasReproduccion from './listasReproduccion';
import estadoReproduccion from './estadoReproduccion';

const rootReducer = combineReducers({
  // Here should be the reducers
  loginReducer,
  altaUsuarioStatus,
  perfilReducer,
  novedadesReducer,
  busqueda,
  reproductor,
  estadoReproduccion,
  listasReproduccion
});

export default rootReducer;
