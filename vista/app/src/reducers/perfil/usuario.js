import {PERFIL_USUARIO, PERFIL_RESTORE} from '../../actions/types';

const initialState = {};

export default function (state = initialState, action) {
  switch(action.type) {
    case PERFIL_USUARIO:
      return action.usuario || initialState;
    case PERFIL_RESTORE:
      return initialState;
    default:
      return state;
  }
}
