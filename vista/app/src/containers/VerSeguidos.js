import React, {Component} from 'react';
import {connect} from 'react-redux';

import ListaSeguidores from '../components/ListaSeguidores';
import MainContent from '../components/MainContent';
import {getSeguidosPerfil} from '../selectors/perfil'

export class VerSeguidos extends Component {

  render () {
    const {seguidos} = this.props;
    return (
      <MainContent>
        <ListaSeguidores title={'Siguiendo a:'} seguidores={seguidos}/>
      </MainContent>
    );
  }
} 

const mapStateToProps = (state) => ({
  seguidos: getSeguidosPerfil(state)
})

export default connect(
  mapStateToProps
)(VerSeguidos);