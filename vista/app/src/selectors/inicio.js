import {createSelector} from 'reselect';

import {getRecords} from './novedades';
import {getBusqueda} from './busqueda';

export const getRecordsInicio = createSelector(
  [getRecords, getBusqueda],
  (records, busqueda) => {
    if (busqueda.resultados && busqueda.resultados.length) {
      // Si hay una busqueda actual, devolver para el inicio los resultados de la busqueda
      return busqueda.resultados;
    } else if (busqueda.status) {
      return ['noResultsFromSearch'];
    }
    // Si no, devolver los records de novedades
    return records;
  }
);
