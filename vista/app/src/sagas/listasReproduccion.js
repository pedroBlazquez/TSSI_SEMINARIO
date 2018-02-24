import {takeEvery, call, put} from 'redux-saga/effects';

import {message} from 'antd';

import {_get, _put, _post, config} from '../utils/api';
import {GET_LISTAS, PUSH_SONG_TO_LIST, ALTA_LISTA} from '../actions/types';
import {setListas} from '../actions/listasReproduccionActions';
import {setListasPerfil} from '../actions/perfilActions';

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

export function* altaLista(action) {
    try {
        const headers = config();
        const lista = action.lista;
        const payload = {
            ...lista,
            privacidad: false,
            canciones: []
        };

        yield call(_post, '/listas/', payload, headers);
        const listas = yield call(_get, '/listas/getUsuario', headers);
        yield put(setListasPerfil(listas.data));
        
    } catch (e) {
        console.log(e);
    }
}

export default function* watchNovedadesSagas () {
    yield takeEvery(ALTA_LISTA, altaLista);
    yield takeEvery(GET_LISTAS, getListasSaga);
    yield takeEvery(PUSH_SONG_TO_LIST, pushSongToListSaga);
}
