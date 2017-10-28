import {REQUEST_LOGIN} from './types';

export const requestLogin = () => ({type: REQUEST_LOGIN, success: false, error: null});
export const successLogin = () => ({type: REQUEST_LOGIN, success: true, error: null});
export const errorLogin = (error) => ({type: REQUEST_LOGIN, success: false, error});
