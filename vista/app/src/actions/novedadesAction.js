import {GET_NOVEDADES_INICIO, SET_NOVEDADES_INICIO} from './types';

export const getNovedades = () => ({type: GET_NOVEDADES_INICIO});

export const setNovedadesInicio = (records) => ({type: SET_NOVEDADES_INICIO, records});