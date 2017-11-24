import {createSelector} from 'reselect';

export const getReproductor = (state) => state.reproductor;

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