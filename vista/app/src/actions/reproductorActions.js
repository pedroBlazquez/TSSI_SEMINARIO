import {
  REPRODUCIR,
  REPRODUCIR_SIGUIENTE,
  REPRODUCIR_ANTERIOR,
  SET_COLA,
  AGREGAR_A_COLA,
  PAUSAR_REPRODUCCION,
  ESTA_REPRODUCIENDO,
  REMOVER_DE_COLA,
  REPRODUCIR_EN_COLA
} from './types';

export const reproducir = (cancion) => ({type: REPRODUCIR, cancion});
export const siguiente = () => ({type: REPRODUCIR_SIGUIENTE});
export const anterior = () => ({type: REPRODUCIR_ANTERIOR});
export const setCola = (queue) => ({type: SET_COLA, queue});
export const agregarCola = (cancion) => ({type: AGREGAR_A_COLA, cancion});
export const removerDeCola = (posicion) => ({type: REMOVER_DE_COLA, posicion});
export const pausar = () => ({type: PAUSAR_REPRODUCCION});
export const setIsPlaying = (estaReproduciendo) => ({type: ESTA_REPRODUCIENDO, estaReproduciendo});
export const reproducirDeCola = (posicion) => ({type: REPRODUCIR_EN_COLA, posicion});
