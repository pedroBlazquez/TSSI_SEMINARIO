import {put, takeEvery, call} from 'redux-saga/effects';

import {_get, config} from '../utils/api';
import {GET_NOVEDADES_INICIO} from '../actions/types';
import {setNovedadesInicio} from '../actions/novedadesAction';
import {mockedServiceResponse} from '../mockedData';

export function* getNovedadesSaga(action) {
    try {
        const headers = config();
        const response = yield call(_get, '/inicio/', headers);
        const records = response.data;
        yield put(setNovedadesInicio(records));
    } catch (e) {
        console.log(e);
    }
}

export default function* watchNovedadesSagas () {
  yield takeEvery(GET_NOVEDADES_INICIO, getNovedadesSaga);
}