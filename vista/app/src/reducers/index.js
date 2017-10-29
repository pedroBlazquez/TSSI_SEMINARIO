import {combineReducers} from 'redux';
import loginReducer from './loginReducer';

const rootReducer = combineReducers({
  // Here should be the reducers
  loginReducer
});

export default rootReducer;
