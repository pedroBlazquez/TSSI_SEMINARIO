import {TRAER_PERFIL_STATUS} from '../../actions/types';

const initialState = false;

export default function (state = initialState, action) {
  switch(action.type) {
    case TRAER_PERFIL_STATUS:
      return action.isLoading;
    default:
      return state;
  }
}
