import {
  REPRODUCIR,
  REPRODUCIR_SIGUIENTE,
  REPRODUCIR_ANTERIOR,
  SET_COLA,
  AGREGAR_A_COLA
} from '../actions/types';

const initialState = {
  current: -1,
  queue: []
}

export default function (state = initialState, action = {}) {
  switch (action.type) {
    case REPRODUCIR:
     return {current: 0, queue: [action.cancion]};
    case AGREGAR_A_COLA:
     return {queue: state.queue.concat([action.cancion]), ...state};
    case SET_COLA:
      return {queue: action.queue, current: 0};
    case REPRODUCIR_SIGUIENTE:
      const siguiente = state.current + 1;
      if (siguiente < state.queue.length) {
        return {current: siguiente, ...state};
      }
      return state;
    case REPRODUCIR_ANTERIOR: 
      const anterior = state.current - 1;
      if (anterior >= 0) {
        return {current: anterior, ...state};
      }
      return state;
    default:
      return state;
  }
}