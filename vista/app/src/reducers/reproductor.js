import {
  REPRODUCIR,
  REPRODUCIR_SIGUIENTE,
  REPRODUCIR_ANTERIOR,
  SET_COLA,
  AGREGAR_A_COLA
} from '../actions/types';

const initialState = {
  current: -1,
  queue: [
    // HARDCODEADAS LAS CANCIONES CON MOTIVOS DE TESTING
    {
      path: 'https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3' 
    },
    {
      path: 'https://www.soundhelix.com/examples/mp3/SoundHelix-Song-2.mp3' 
    },
    {
      path: 'https://www.soundhelix.com/examples/mp3/SoundHelix-Song-3.mp3' 
    }
  ]
}

export default function (state = initialState, action = {}) {
  switch (action.type) {
    case REPRODUCIR:
     return {current: 0, queue: [action.cancion]};
    case AGREGAR_A_COLA:
     return {...state, queue: state.queue.concat([action.cancion])};
    case SET_COLA:
      return {queue: action.queue, current: 0};
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