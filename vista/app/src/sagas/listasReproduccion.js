import {takeEvery, call, put} from 'redux-saga/effects';

import {message} from 'antd';

import {_get, _put, config} from '../utils/api';
import {GET_LISTAS, PUSH_SONG_TO_LIST} from '../actions/types';
import {setListas} from '../actions/listasReproduccionActions';

export function* getListasSaga() {
    try {
        const headers = config();
        const response = yield call(_get, '/listas/getUsuario', headers);
        yield put(setListas(response.data));
    } catch (e) {
        console.log(e);
    }
}

export function* pushSongToListSaga(action) {
    try {
        const headers = config();
        const payload = {'idListaReproduccion': action.idLista, 'idCancion': action.idCancion};
        const response = yield call(_put, '/listas/agregarCancion', payload, headers);

        yield call(message.success, 'Cancion agregada con exito');
    } catch (e) {
        console.log(e);
        yield call(message.warn, 'Hubo un error, vuelva a intentarlo');
    }
}

export default function* watchNovedadesSagas () {
    yield takeEvery(GET_LISTAS, getListasSaga);
    yield takeEvery(PUSH_SONG_TO_LIST, pushSongToListSaga);
}
