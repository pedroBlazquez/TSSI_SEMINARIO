import {combineReducers} from 'redux';
import loginReducer from './loginReducer';
import altaUsuarioStatus from './altaUsuarioStatus';
import perfilReducer from './perfil';
import novedadesReducer from './novedadesReducer';
import busqueda from './busqueda';

const rootReducer = combineReducers({
  // Here should be the reducers
  loginReducer,
  altaUsuarioStatus,
  perfilReducer,
  novedadesReducer,
  busqueda
});

export default rootReducer;
