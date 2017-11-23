import {SET_BUSQUEDA, BUSCAR} from './types';

export const buscar = (parametros) => ({type: BUSCAR, parametros});

export const setResultadosBusqueda = (payload) => ({type: SET_BUSQUEDA, payload});

export const limpiarResultadosBusqueda = () => ({type: SET_BUSQUEDA, payload: []});
