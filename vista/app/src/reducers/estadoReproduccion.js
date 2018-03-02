import moment from 'moment';
import {
  PAUSAR_REPRODUCCION,
  ESTA_REPRODUCIENDO,
  REPRODUCIR,
  SET_COLA,
  REPRODUCIR_SIGUIENTE,
  REPRODUCIR_ANTERIOR
} from '../actions/types';

const initialState = {
  latestPlay: moment().valueOf(),
  latestPause: moment().valueOf(),
  estaReproduciendo: false
}

export default function (state = initialState, action = {}) {
  switch (action.type) {
    case REPRODUCIR:
    case SET_COLA:
    case REPRODUCIR_SIGUIENTE:
    case REPRODUCIR_ANTERIOR:
      return {...state, latestPlay: moment().valueOf()};
    case PAUSAR_REPRODUCCION: 
      return {...state, latestPause: moment().valueOf()};
    case ESTA_REPRODUCIENDO:
      return {
        ...state,
        estaReproduciendo: action.estaReproduciendo
      };
    default:
      return state;
  }
}