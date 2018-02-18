import {PERFIL_COMPARTIDO, PERFIL_RESTORE, DELETE_COMPARTIR} from '../../actions/types';

const initialState = [];

export default function (state = initialState, action) {
  switch(action.type) {
    case PERFIL_COMPARTIDO:
      return action.compartidos || initialState;
    case DELETE_COMPARTIR:
      let elementIndex = state.findIndex((e) =>
        e.object_type === action.typeRecord
        && e[e.object_type.toLowerCase()].id === action.id
      );
      state.splice(elementIndex, 1);
      return [...state];
    case PERFIL_RESTORE:
      return initialState;
    default:
      return state;
  }
}
