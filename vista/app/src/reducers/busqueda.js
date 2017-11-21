import {SET_BUSQUEDA} from '../actions/types';

const initialState = [];

export default function (state = initialState, action = {}) {
  switch (action.type) {
    case SET_BUSQUEDA:
      return action.payload;
    default:
      return state;
  }
} 