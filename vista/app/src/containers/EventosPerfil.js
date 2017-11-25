import React, {Component} from 'react';
import {connect} from 'react-redux';

import Novedades from '../components/Novedades';
import MainContent from '../components/MainContent';

import {getEventosPerfil} from '../selectors/perfil';

export class EventosPerfil extends Component {
  render () {
    const {eventos} = this.props;
    return (
        <MainContent>
          <Novedades records={eventos}/> 
        </MainContent>
    );
  }
}

const mapStateToProps = (state) => ({
  eventos: getEventosPerfil(state)
});

export default connect(
  mapStateToProps
)(EventosPerfil)