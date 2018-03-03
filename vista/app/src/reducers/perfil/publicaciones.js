import {PERFIL_PUBLICACIONES, PERFIL_RESTORE, SEND_COMPARTIR} from '../../actions/types';

const initialState = [];

export default function (state = initialState, action) {
  switch(action.type) {
    case SEND_COMPARTIR:
      return state.filter(p => {
        return !(p.object_type === action.typeRecord && p.id === action.id && p.compartido);
      });
    case PERFIL_PUBLICACIONES:
      return action.publicaciones ? action.publicaciones.map(p => {
        const objectType = p.object_type.toLowerCase();
        return {
          ...p,
          ...p[objectType]
        };
      }) : initialState;
    case PERFIL_RESTORE:
      return initialState;
    default:
      return state;
  }
}
