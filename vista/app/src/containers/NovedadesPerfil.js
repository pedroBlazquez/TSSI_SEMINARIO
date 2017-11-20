import React, {Component} from 'react';
import {withRouter} from 'react-router-dom';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';

import {getNovedades} from '../actions/novedadesAction';

import withProfile from '../hoc/withProfile';
import Novedades from '../components/Novedades';

class NovedadesPerfil extends Component {
  componentWillMount() {
    this.props.getUltimasPublicaciones();
  }

  render () {
    const {esPerfilPropio, records} = this.props;
    return (
      !!records ? <Novedades conPublicacion={esPerfilPropio} records={records} /> : null
    );
  }
}

const mapStateToProps = (state) => ({
  records: state.novedadesReducer.records
});

const mapDispatchToProps = (dispatch) => ({
  getUltimasPublicaciones: bindActionCreators(getNovedades, dispatch)
});

export default withRouter(withProfile(connect(
  mapStateToProps,
  mapDispatchToProps
)(NovedadesPerfil)
));
