import {SET_NOVEDADES_INICIO, SEND_LIKE, SEND_COMPARTIR} from '../actions/types';

const initialState = {
  records: []
};

//id, typeRecord})

export default function (state = initialState, action = {}) {
    switch (action.type) {
        case SEND_LIKE:
        case SEND_COMPARTIR:
          return {
            records: state.records.map(record => {
              if (record.object_type === action.typeRecord && record.id === action.id) {
                switch(action.type) {
                  case SEND_LIKE:
                    return {
                      ...record,
                      seguido: !record.seguido,
                      likes: !record.seguido ? record.likes-- : record.likes++
                    };
                  case SEND_COMPARTIR:
                    return {...record, compartido: !record.compartido };
                }
              } else {
                return record;
              }
            })
          };
        case SET_NOVEDADES_INICIO:
            return {records: action.records};
        default:
            return state;
    }
}