import {PERFIL_USUARIO, PERFIL_RESTORE, PERFIL_UPDATE_TREE, ACTUALIZAR_FOTO_PERFIL} from '../../actions/types';
import {merge} from 'lodash';

const initialState = {};

export default function (state = initialState, action) {
  switch(action.type) {
    case PERFIL_USUARIO:
      return action.usuario || initialState;
    case PERFIL_UPDATE_TREE:
      let {nombre, apellido, nombreFantasia, descripcion, integrantes} = action.data;
      state.nombre = nombre;
      state.apellido = apellido;
      if (descripcion && nombreFantasia) {
        state.artista[0].nombreFantasia = nombreFantasia;
        state.artista[0].descripcion = descripcion;
        state.artista[0].integrantes = integrantes;
      }
      return {...state};
    case ACTUALIZAR_FOTO_PERFIL:
      state.imagen = action.url;
      return {...state};
    case PERFIL_RESTORE:
      return initialState;
    default:
      return state;
  }
}
