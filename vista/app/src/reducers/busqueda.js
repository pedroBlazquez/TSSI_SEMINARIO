import {SET_BUSQUEDA, SET_STATUS_BUSQUEDA} from '../actions/types';

const initialState = {status: false, resultados: []};

export default function (state = initialState, action = {}) {
  switch (action.type) {
    case SET_BUSQUEDA:
      state.resultados = action.payload;
      state.status = true;
      return {...state};
    case SET_STATUS_BUSQUEDA:
      state.status = action.isBusqueda;
      return {...state};
    default:
      return state;
  }
}
