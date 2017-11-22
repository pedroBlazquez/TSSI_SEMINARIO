import {REQUEST_LOGIN, LOGIN, CHECK_TOKEN} from './types';
import {unsetAuthToken} from '../utils/storage';

export const requestLogin = (user, pass) => ({type: REQUEST_LOGIN, user, pass});
export const checkToken = () => ({type: CHECK_TOKEN});
export const successLogin = (user) => ({type: LOGIN, success: true, error: null, user});
export const logOut = () => {
  unsetAuthToken();
  return {type: LOGIN, success: false, error: null, user: null};
};
export const errorLogin = (error) => ({type: LOGIN, success: false, error, user: null});
