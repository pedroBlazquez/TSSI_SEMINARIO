import {REGISTER_USER, SUCCESS_REGISTER, UPDATE_USER, BAJA_USUARIO} from './types';

export const requestRegister = (user) => ({type: REGISTER_USER, user});
export const successRegister = () => ({type: SUCCESS_REGISTER, success: true, message: 'Usuario creado con éxito'});
export const failRegister = (message) => ({type: SUCCESS_REGISTER, success: false, message});
export const resetStatus = () => failRegister('');
export const updateUser = (user) => ({type: UPDATE_USER, user});
export const bajaUsuario = () => ({type: BAJA_USUARIO});
