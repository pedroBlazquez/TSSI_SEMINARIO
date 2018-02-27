import {takeEvery, call, put} from 'redux-saga/effects';

import {message} from 'antd';

import {_get, _put, _post, config, _delete} from '../utils/api';
import {GET_LISTAS, PUSH_SONG_TO_LIST, ALTA_LISTA, ELIMINAR_LISTA, MOD_LISTA} from '../actions/types';
import {setListas as setListasModal} from '../actions/listasReproduccionActions';
import {setListasPerfil} from '../actions/perfilActions';


export function* getListasSaga() {
    try {
        const headers = config();
        const response = yield call(_get, '/listas/getUsuario', headers);
        yield put(setListasPerfil(response.data));
        yield put(setListasModal(response.data));
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
        yield put({type: 'GET_LISTAS_PERFIL'});
    } catch (e) {
        console.log(e);
    }
}

export function* bajaLista(action) {
    try {
        const headers = config();
        const lista = action.id;

        yield call(_delete, '/listas/', {data: {idListaReproduccion: lista.toString()}, ...headers});
        yield put({type: GET_LISTAS});
    } catch (e) {
        console.log(e);
    }
}

export function* getListasPerfilSaga() {
    try {
        const headers = config();
        const listas = yield call(_get, '/listas/getUsuario', headers);
        yield put(setListasPerfil(listas.data));
        yield put(setListasModal(listas.data));
    } catch (e) {
        console.log(e);
    }
}

export function* modificarListaSaga(action) {
    try {
        const headers = config();
        const payload = {
            idListaReproduccion: action.lista.idLista,
            nombre: action.lista.nombre,
            privacidad: action.lista.privacidad,
            canciones: action.lista.canciones.map(c => c.id.toString())
        };
        const listas = yield call(_put, '/listas/', payload, headers);
        yield put({type: GET_LISTAS});
    } catch (e) {
        console.log(e);
    }
}

export default function* watchNovedadesSagas () {
    yield takeEvery(ALTA_LISTA, altaLista);
    yield takeEvery(GET_LISTAS, getListasSaga);
    yield takeEvery(PUSH_SONG_TO_LIST, pushSongToListSaga);
    yield takeEvery(ELIMINAR_LISTA, bajaLista);
    yield takeEvery(MOD_LISTA, modificarListaSaga);
}
