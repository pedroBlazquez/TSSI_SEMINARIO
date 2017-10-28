import { all } from 'redux-saga/effects'

import loginSagas from './loginSagas';

// single entry point to start all Sagas at once
export default function* rootSaga() {
  yield all([
    loginSagas()
  ])
}
