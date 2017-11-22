import {createSelector} from 'reselect';

import {getRecords} from './novedades';
import {getBusqueda} from './busqueda';

export const getRecordsInicio = createSelector(
  [getRecords, getBusqueda],
  (records, busqueda) => {
    if (busqueda.length) {
      // Si hay una busqueda actual, devolver para el inicio los resultados de la busqueda
      return busqueda;
    }
    // Si no, devolver los records de novedades
    return records;
  }
);