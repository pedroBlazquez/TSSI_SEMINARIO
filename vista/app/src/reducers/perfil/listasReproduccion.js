import {ABRIR_MODAL, CERRAR_MODAL, SET_LISTAS, LOGIN} from '../../actions/types';

const initialState = {modalOpen: false};

export default function (state = initialState, action) {
    switch (action.type) {
        case ABRIR_MODAL:
            return Object.assign({}, state, {modalOpen: true, idCancion: action.idCancion});
        case CERRAR_MODAL:
            return Object.assign({}, state, {modalOpen: false});
        case SET_LISTAS:
            return Object.assign({}, state, {listas: action.listas});
        case LOGIN:
            return initialState;
        default:
            return state;
    }
}
