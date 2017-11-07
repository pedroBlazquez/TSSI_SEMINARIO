import {createSelector} from 'reselect';

export const getAltaUsuarioStatus = (state) => state.altaUsuarioStatus

export const getAltaError = createSelector(
  [getAltaUsuarioStatus],
  (status) => !status.success && status.message
);

export const getAltaSuccess = createSelector(
  [getAltaUsuarioStatus],
  (status) => status.success && status.message
);