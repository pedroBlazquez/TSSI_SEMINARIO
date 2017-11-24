import { all } from 'redux-saga/effects'

import usuarios from './usuarios';
import contenido from './contenido';
import perfil from './perfil';
import novedades from './novedades';
import compartir from './compartir';
import like from './like';
import buscar from './buscar';
import publicar from './publicar';

// single entry point to start all Sagas at once
export default function* rootSaga() {
  yield all([
    usuarios(),
    contenido(),
    perfil(),
    novedades(),
    compartir(),
    like(),
    buscar(),
    publicar()
  ])
}
