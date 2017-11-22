import {takeEvery, call} from 'redux-saga/effects';

import {_post, config} from '../utils/api';
import {SEND_COMPARTIR} from '../actions/types';
import {sendCompartir} from '../actions/compartirAction';

export function* sendCompartirSaga(action) {
    try {
        const headers = config();
        const response = yield call(
            _post,
            '/compartir/',
            {'id': action.id.toString(), 'tipo': action.typeRecord},
            headers
        );

    } catch (e) {
        console.log(e);
    }
}

export default function* watchNovedadesSagas () {
    yield takeEvery(SEND_COMPARTIR, sendCompartirSaga);
}
