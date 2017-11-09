import {PERFIL_LISTAS, PERFIL_RESTORE} from '../../actions/types';

const initialState = [];

export default function (state = initialState, action) {
  switch(action.type) {
    case PERFIL_LISTAS:
      return action.listas || initialState;
    case PERFIL_RESTORE:
      return initialState;
    default:
      return state;
  }
}