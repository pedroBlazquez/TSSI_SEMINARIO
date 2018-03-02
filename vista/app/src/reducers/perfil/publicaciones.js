import {PERFIL_PUBLICACIONES, PERFIL_RESTORE} from '../../actions/types';

const initialState = [];

export default function (state = initialState, action) {
  switch(action.type) {
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
