import {takeEvery, call} from 'redux-saga/effects';

import {_post, config} from '../utils/api';
import {SEND_LIKE} from '../actions/types';
import {sendLike} from '../actions/compartirAction';

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

export default function* watchNovedadesSagas () {
    yield takeEvery(SEND_LIKE, sendLikeSaga);
}