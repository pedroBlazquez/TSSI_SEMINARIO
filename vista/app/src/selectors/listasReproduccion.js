import {createSelector} from 'reselect';

export const getListasReproduccion = state => state.listasReproduccion;

export const getCancionAgregarALista = createSelector(
  [getListasReproduccion],
  listasReproduccion => listasReproduccion.cancion
);
