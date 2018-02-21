import {PERFIL_USUARIO, PERFIL_RESTORE, PERFIL_UPDATE_TREE} from '../../actions/types';

const initialState = {};

export default function (state = initialState, action) {
  switch(action.type) {
    case PERFIL_USUARIO:
      return action.usuario || initialState;
    case PERFIL_UPDATE_TREE:
      return {...Object.assign(state, action.nombreCompleto)};
    case PERFIL_RESTORE:
      return initialState;
    default:
      return state;
  }
}
