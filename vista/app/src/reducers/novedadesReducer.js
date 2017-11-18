import {SET_NOVEDADES_INICIO} from '../actions/types';

const initialState = {
  records: []
};

export default function (state = initialState, action = {}) {
    switch (action.type) {
        case SET_NOVEDADES_INICIO:
            return {records: action.records};
        default:
            return state;
    }
}