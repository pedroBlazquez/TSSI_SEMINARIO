import {
  PAUSAR_REPRODUCCION,
  ESTA_REPRODUCIENDO,
  REPRODUCIR,
  SET_COLA,
  REPRODUCIR_SIGUIENTE,
  REPRODUCIR_ANTERIOR
} from '../actions/types';

const initialState = {
  forzarReproducir: false,
  estaReproduciendo: false
}

export default function (state = initialState, action = {}) {
  switch (action.type) {
    case REPRODUCIR:
    case SET_COLA:
    case REPRODUCIR_SIGUIENTE:
    case REPRODUCIR_ANTERIOR:
      return {...state, forzarReproducir: true};
    case PAUSAR_REPRODUCCION: 
      return {...state, forzarReproducir: false};
    case ESTA_REPRODUCIENDO:
      return {...state, estaReproduciendo: action.estaReproduciendo}
    default:
      return state;
  }
}