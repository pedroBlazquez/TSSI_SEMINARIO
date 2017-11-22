import {
  REPRODUCIR,
  REPRODUCIR_SIGUIENTE,
  REPRODUCIR_ANTERIOR,
  SET_COLA,
  AGREGAR_A_COLA
} from '../types';

export const reproducir = (cancion) => ({type: REPRODUCIR, cancion});
export const siguiente = () => ({type: REPRODUCIR_SIGUIENTE});
export const anterior = () => ({type: REPRODUCIR_ANTERIOR});
export const setCola = (queue) => ({type: SET_COLA, queue});
export const agregarCola = (cancion) => ({type: AGREGAR_A_COLA, cancion});
