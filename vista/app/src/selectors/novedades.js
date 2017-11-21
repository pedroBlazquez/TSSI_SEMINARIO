import {createSelector} from 'reselect';

export const getNovedades = state => state.novedadesReducer;

export const getRecords = createSelector(
  [getNovedades],
  novedades => novedades.records
);
