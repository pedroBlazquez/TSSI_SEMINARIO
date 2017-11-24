import React from 'react';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';

import {anterior, siguiente} from '../actions/reproductorActions';
import {getCurrentSong} from '../selectors/reproductor';

import Reproductor from '../components/Reproductor';

const mapStateToProps = (state) => ({
  track: getCurrentSong(state),
  artista: getCurrentSong(state).artista
});

const mapDispatchToProps = (dispatch) => ({
  next: bindActionCreators(siguiente, dispatch),
  previous: bindActionCreators(anterior, dispatch)
});

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Reproductor);
