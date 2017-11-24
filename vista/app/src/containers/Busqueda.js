import React from 'react';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';

import {buscar, limpiarResultadosBusqueda} from '../actions/buscarActions';
import FormBusqueda from '../components/FormBusqueda';

const mapDispatchToProps = (dispatch) => ({
  onSearch: bindActionCreators(buscar, dispatch),
  limpiarBusqueda: bindActionCreators(limpiarResultadosBusqueda, dispatch)
})

export default connect(
  null,
  mapDispatchToProps
)(FormBusqueda);