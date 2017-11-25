import {PERFIL_COMPARTIDO, PERFIL_RESTORE} from '../../actions/types';

const initialState = [];

export default function (state = initialState, action) {
  switch(action.type) {
    case PERFIL_COMPARTIDO:
      return action.compartidos || initialState;
    case PERFIL_RESTORE:
      return initialState;
    default:
      return state;
  }
}
