import { all } from 'redux-saga/effects'

import usuarios from './usuarios';
import contenido from './contenido';
import perfil from './perfil';

// single entry point to start all Sagas at once
export default function* rootSaga() {
  yield all([
    usuarios(),
    contenido(),
    perfil()
  ])
}
