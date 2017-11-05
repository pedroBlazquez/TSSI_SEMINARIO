import {combineReducers} from 'redux';
import loginReducer from './loginReducer';
import altaUsuarioStatus from './altaUsuarioStatus';

const rootReducer = combineReducers({
  // Here should be the reducers
  loginReducer,
  altaUsuarioStatus
});

export default rootReducer;
