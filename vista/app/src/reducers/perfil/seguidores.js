import {PERFIL_SEGUIDORES, PERFIL_RESTORE} from '../../actions/types';

const initialState = [];

export default function (state = initialState, action) {
  switch(action.type) {
    case PERFIL_SEGUIDORES:
      return action.seguidores || initialState;
    case PERFIL_RESTORE:
      return initialState;
    default:
      return state;
  }
}
