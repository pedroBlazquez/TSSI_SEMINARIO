import { all } from 'redux-saga/effects'

import usuarios from './usuarios';

// single entry point to start all Sagas at once
export default function* rootSaga() {
  yield all([
    usuarios()
  ])
}
