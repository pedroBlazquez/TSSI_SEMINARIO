import {REQUEST_LOGIN, LOGIN} from './types';

export const requestLogin = (user, pass) => ({type: REQUEST_LOGIN, user, pass});
export const successLogin = (user) => ({type: LOGIN, success: true, error: null, user});
export const logOut = () => ({type: LOGIN, success: false, error: null, user: null});
export const errorLogin = (error) => ({type: LOGIN, success: false, error, user: null});
