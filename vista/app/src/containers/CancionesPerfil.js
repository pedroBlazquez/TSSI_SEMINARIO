import React, {Component} from 'react';
import {connect} from 'react-redux';

import Novedades from '../components/Novedades';
import MainContent from '../components/MainContent';

import {getCancionesPerfil} from '../selectors/perfil';

export class CancionesPerfil extends Component {
  render () {
    const {canciones} = this.props;
    return (
      <MainContent>
        {<Novedades records={canciones}/>}
      </MainContent>
    );
  }
}

const mapStateToProps = (state) => ({
  canciones: getCancionesPerfil(state)
});

export default connect(
  mapStateToProps
)(CancionesPerfil)