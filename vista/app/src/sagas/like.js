import {takeEvery, call, put} from 'redux-saga/effects';

import {_post, _get, config} from '../utils/api';
import {SEND_LIKE, SEGUIR_USUARIO} from '../actions/types';
import {setSeguidoresPerfil} from '../actions/perfilActions'; 

export function* sendLikeSaga(action) {
    try {
        const headers = config();
        const response = yield call(
            _post,
            '/like/',
            {'id': action.id.toString(), 'tipo': action.typeRecord},
            headers
        );

    } catch (e) {
        console.log(e);
    }
}

export function* seguirUsuario(action) {
    try {
        const headers = config();
        const response = yield call(
            _post,
            '/usuario/seguir',
            {idUsuario: action.id.toString(), tipo: action.typeRecord},
            headers
        );
        // Actualizamos los seguidores del perfil
        const seguidores = yield call(_get, `/usuario/getSeguidores/${action.id}`, headers );
        yield put(setSeguidoresPerfil(seguidores.data));
    } catch (e) {
        console.log(e);
    }
}

export default function* watchNovedadesSagas () {
    yield takeEvery(SEND_LIKE, sendLikeSaga);
    yield takeEvery(SEGUIR_USUARIO, seguirUsuario);
}