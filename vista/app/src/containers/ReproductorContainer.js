import React from 'react';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';

import {anterior, siguiente, pausar, reanudar} from '../actions/reproductorActions';
import {getCurrentSong, estaReproduciendo} from '../selectors/reproductor';

import Reproductor from '../components/Reproductor';

const mapStateToProps = (state) => ({
  track: getCurrentSong(state),
  artista: getCurrentSong(state).artista,
  reproduciendo: estaReproduciendo(state)
});

const mapDispatchToProps = (dispatch) => ({
  pausar: bindActionCreators(pausar, dispatch),
  reanudar: bindActionCreators(reanudar, dispatch),
  next: bindActionCreators(siguiente, dispatch),
  previous: bindActionCreators(anterior, dispatch)
});

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Reproductor);
