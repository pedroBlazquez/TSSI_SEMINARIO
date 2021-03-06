import {PERFIL_CANCIONES, PERFIL_RESTORE} from '../../actions/types';

const initialState = [];

export default function (state = initialState, action) {
  switch(action.type) {
    case PERFIL_CANCIONES:
      return action.canciones || initialState;
    case PERFIL_RESTORE:
      return initialState;
    default:
      return state;
  }
}