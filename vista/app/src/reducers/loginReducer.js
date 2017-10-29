import {LOGIN} from '../actions/types';

const initialState = {
  success: false,
  error: null
};

export default function (state = initialState, action = {}) {
  switch (action.type) {
    case LOGIN:
      return {success: action.success, error: action.error};
    default:
      return state;
  } 
}
