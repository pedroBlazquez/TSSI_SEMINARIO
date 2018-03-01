import React from 'react';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';

import {anterior, siguiente, setIsPlaying} from '../actions/reproductorActions';
import {getCurrentSong, estaReproduciendo, forzarReproduccion} from '../selectors/reproductor';

import Reproductor from '../components/Reproductor';

const mapStateToProps = (state) => ({
  track: getCurrentSong(state),
  artista: getCurrentSong(state).artista,
  reproduciendo: forzarReproduccion(state)
});

const mapDispatchToProps = (dispatch) => ({
  estaReproduciendo: bindActionCreators(setIsPlaying, dispatch),
  next: bindActionCreators(siguiente, dispatch),
  previous: bindActionCreators(anterior, dispatch)
});

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Reproductor);
