import React from 'react';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';

import {anterior, siguiente} from '../actions/reproductorActions';

import Reproductor from '../components/Reproductor';

const mapStateToProps = (state) => ({
  track: state.reproductor.queue[state.reproductor.current],
  artista: state.reproductor.queue[state.reproductor.current] && state.reproductor.queue[state.reproductor.current].artista
});

const mapDispatchToProps = (dispatch) => ({
  next: bindActionCreators(siguiente, dispatch),
  previous: bindActionCreators(anterior, dispatch)
});

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Reproductor);
