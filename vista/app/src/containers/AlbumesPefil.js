import React, {Component} from 'react';
import {connect} from 'react-redux';

import Novedades from '../components/Novedades';

import {getAlbumesPerfil} from '../selectors/perfil';

export class AlbumesPerfil extends Component {
  render () {
    const {albumes} = this.props;
    return (
        albumes ? <Novedades records={albumes}/> : null
    );
  }
}

const mapStateToProps = (state) => ({
  albumes: getAlbumesPerfil(state)
});

export default connect(
  mapStateToProps
)(AlbumesPerfil)