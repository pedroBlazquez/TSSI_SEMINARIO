import {createSelector} from 'reselect';

export const getLoginState = (state) => state.loginReducer

export const isLogged = createSelector(
  [getLoginState],
  (login) => login.success
);

export const loginError = createSelector(
  [getLoginState],
  (login) => login.error
);

export const getCurrentUser = createSelector(
  [getLoginState],
  (login) => login.user
)