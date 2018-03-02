import {createSelector} from 'reselect';

export const getReproductor = (state) => state.reproductor;
export const getEstadoReproduccion = state => state.estadoReproduccion;

export const estaReproduciendo = createSelector(
  [getEstadoReproduccion],
  reproductor => reproductor.estaReproduciendo
);

export const latestPlay = createSelector(
  [getEstadoReproduccion],
  reproductor => reproductor.latestPlay
);

export const latestPause = createSelector(
  [getEstadoReproduccion],
  reproductor => reproductor.latestPause
);

export const getCurrentSong = createSelector(
  [getReproductor],
  reproductor => {
    const {current, queue} = reproductor;
    if (queue.length && current !== -1) {
      return queue[current];
    }

    return {};
  }
);
