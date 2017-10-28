import {REQUEST_LOGIN} from '../actions/types';

const initialState = {
  success: false,
  error: null
};

export default function (state = initialState, action = {}) {
  switch (action.type) {
    case REQUEST_LOGIN:
      return Object.assign({}, state, {success: action.success, error: action.error});
    default:
      return state;
  } 
}
