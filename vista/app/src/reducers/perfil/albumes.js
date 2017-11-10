import {PERFIL_ALBUMES, PERFIL_RESTORE} from '../../actions/types';

const initialState = [];

export default function (state = initialState, action) {
  switch(action.type) {
    case PERFIL_ALBUMES:
      return action.albumes || initialState;
    case PERFIL_RESTORE:
      return initialState;
    default:
      return state;
  }
}