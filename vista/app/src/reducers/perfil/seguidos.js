import {PERFIL_SEGUIDOS, PERFIL_RESTORE} from '../../actions/types';

const initialState = [];

export default function (state = initialState, action) {
  switch(action.type) {
    case PERFIL_SEGUIDOS:
      return action.seguidos || initialState;
    case PERFIL_RESTORE:
      return initialState;
    default:
      return state;
  }
}
