import React, {Component} from 'react';
import {withRouter} from 'react-router-dom';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';

import {getNovedades} from '../actions/novedadesAction';

import {Card} from 'antd';
import MainContent from '../components/MainContent';
import NuevaPublicacion from '../containers/NuevaPublicacion';
import withProfile from '../hoc/withProfile';
import Novedades from '../components/Novedades';

import {getNovedadesPorTipoUsuario} from '../selectors/perfil';

class NovedadesPerfil extends Component {
  componentWillMount() {
    this.props.getUltimasPublicaciones();
  }

  render () {
    const {esPerfilPropio, esArtista, records} = this.props;
    return (
      <MainContent>
        {esPerfilPropio && esArtista ? <NuevaPublicacion /> : null}
        <Novedades conPublicacion={esPerfilPropio} records={records} />
      </MainContent>
    );
  }
}

const mapStateToProps = (state) => ({
  records: getNovedadesPorTipoUsuario(state)
});

const mapDispatchToProps = (dispatch) => ({
  getUltimasPublicaciones: bindActionCreators(getNovedades, dispatch)
});

export default withRouter(withProfile(connect(
  mapStateToProps,
  mapDispatchToProps
)(NovedadesPerfil)
));
