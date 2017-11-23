import React, {Component} from 'react';
import {connect} from 'react-redux';

import Novedades from '../components/Novedades';
import MainContent from '../components/MainContent';

import {getAlbumesPerfil} from '../selectors/perfil';

export class AlbumesPerfil extends Component {
  render () {
    const {albumes} = this.props;
    return (
        <MainContent>
          <Novedades records={albumes}/> 
        </MainContent>
    );
  }
}

const mapStateToProps = (state) => ({
  albumes: getAlbumesPerfil(state)
});

export default connect(
  mapStateToProps
)(AlbumesPerfil)