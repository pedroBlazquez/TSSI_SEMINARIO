import {REQUEST_LOGIN, LOGIN} from './types';

export const requestLogin = (user, pass) => ({type: REQUEST_LOGIN, user, pass});
export const successLogin = () => ({type: LOGIN, success: true, error: null});
export const errorLogin = (error) => ({type: LOGIN, success: false, error});
