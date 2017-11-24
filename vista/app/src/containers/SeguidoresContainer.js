import React from 'react';
import {connect} from 'react-redux';

import ListaUsuarios from '../components/ListaUsuarios';

const mapStateToProps = (state) => ({
  records: state.perfilReducer.seguidores
});

export default connect(
  mapStateToProps
)(ListaUsuarios);