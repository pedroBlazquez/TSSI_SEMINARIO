import {SUCCESS_REGISTER} from '../actions/types';

const initialState = {
  success: false,
  message: ''
};

export default function (state = initialState, action = {}) {
  switch (action.type) {
    case SUCCESS_REGISTER:
      return {success: action.success, message: action.message};
    default:
      return state;
  }
}
