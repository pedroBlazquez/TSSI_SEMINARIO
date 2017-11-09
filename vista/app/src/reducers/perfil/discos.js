import {PERFIL_DISCOS, PERFIL_RESTORE} from '../../actions/types';

const initialState = [];

export default function (state = initialState, action) {
  switch(action.type) {
    case PERFIL_DISCOS:
      return action.discos || initialState;
    case PERFIL_RESTORE:
      return initialState;
    default:
      return state;
  }
}