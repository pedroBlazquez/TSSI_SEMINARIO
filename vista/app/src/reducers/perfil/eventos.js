import {PERFIL_EVENTOS, PERFIL_RESTORE} from '../../actions/types';

const initialState = [];

export default function (state = initialState, action) {
  switch(action.type) {
    case PERFIL_EVENTOS:
      return action.eventos || initialState;
    case PERFIL_RESTORE:
      return initialState;
    default:
      return state;
  }
}