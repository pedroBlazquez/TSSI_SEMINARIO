import {
  REPRODUCIR,
  REPRODUCIR_SIGUIENTE,
  REPRODUCIR_ANTERIOR,
  SET_COLA,
  AGREGAR_A_COLA,
  PAUSAR_REPRODUCCION,
  REANUDAR_REPRODUCCION
} from '../actions/types';

const initialState = {
  current: -1,
  queue: [],
  reproduciendo: false
}

export default function (state = initialState, action = {}) {
  switch (action.type) {
    case REANUDAR_REPRODUCCION:
      return {...state, reproduciendo: true};
    case PAUSAR_REPRODUCCION: 
      return {...state, reproduciendo: false};
    case REPRODUCIR:
     return {current: 0, queue: [action.cancion], reproduciendo: true};
    case AGREGAR_A_COLA:
     return {...state, queue: state.queue.concat([action.cancion])};
    case SET_COLA:
      return {queue: action.queue, current: 0, reproduciendo: true};
    case REPRODUCIR_SIGUIENTE:
      const siguiente = state.current + 1;
      if (siguiente < state.queue.length) {
        return {...state, current: siguiente};
      }
      return state;
    case REPRODUCIR_ANTERIOR: 
      const anterior = state.current - 1;
      if (anterior >= 0) {
        return {...state, current: anterior};
      }
      return state;
    default:
      return state;
  }
}