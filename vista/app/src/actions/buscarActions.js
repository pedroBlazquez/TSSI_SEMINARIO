import {SET_BUSQUEDA, BUSCAR, SET_STATUS_BUSQUEDA} from './types';

export const buscar = (parametros) => ({type: BUSCAR, parametros});

export const setResultadosBusqueda = (payload) => ({type: SET_BUSQUEDA, payload});

export const limpiarResultadosBusqueda = () => ({type: SET_BUSQUEDA, payload: []});

export const setStatusBusqueda = (isBusqueda) => ({type: SET_STATUS_BUSQUEDA, isBusqueda});
