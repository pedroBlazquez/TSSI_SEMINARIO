import React, {Component} from 'react';
import {connect} from 'react-redux';

import Novedades from '../components/Novedades';
import MainContent from '../components/MainContent';

import {getDiscosPerfil} from '../selectors/perfil';

export class DiscosPerfil extends Component {
  render () {
    const {discos} = this.props;
    return (
      <MainContent>
        {<Novedades records={discos}/>}
      </MainContent>
    );
  }
}

const mapStateToProps = (state) => ({
  discos: getDiscosPerfil(state)
});

export default connect(
  mapStateToProps
)(DiscosPerfil)