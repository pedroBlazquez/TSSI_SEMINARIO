import {takeEvery, call, put} from 'redux-saga/effects';
import moment from 'moment';

import {_post, config} from '../utils/api';
import {GENEROS} from '../utils/constants';
import {BUSCAR} from '../actions/types';
import {setResultadosBusqueda} from '../actions/buscarActions';
import { formatFecha } from '../utils/utils';

export function* buscar({parametros}) {
  try {
    const headers = config();
    const genero = GENEROS.find(g => g.id.toString() === parametros.genero);
    const fecha = formatFecha(parametros.fecha) || '';
    const body = {
      'busqueda': parametros.search,
      'genero': genero ? genero.value : '',
      'nombre_artista': parametros.artista,
      'direccion': parametros.ubicacion,
      'desdehasta': parametros.desdeHasta,
      'fecha': fecha
    };
    const resultado = yield call(
      _post, 
      '/inicio/buscar',
      body,
      headers
    );

    yield put(setResultadosBusqueda(resultado.data));
  } catch (e) {
      console.log(e);
  }
}

export default function* watchNovedadesSagas () {
    yield takeEvery(BUSCAR, buscar);
}
