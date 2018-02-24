import React, {Component} from 'react';
import {connect} from 'react-redux';

import withProfile from '../hoc/withProfile';
import {withRouter} from 'react-router-dom';
import MainContent from '../components/MainContent';
import ListaReproduccion from '../components/ListaReproduccion';

import {getListasPerfil} from '../selectors/perfil';
import AdministrarListasContainer from './AdministrarListasContainer';


export class ListasPerfil extends Component {
  render () {
    const {listas, esPerfilPropio} = this.props;
    return (
      esPerfilPropio ? 
        <AdministrarListasContainer/> :
        <MainContent>
          {listas.map(lista => 
            <ListaReproduccion key={lista.id} lista={lista}/>
          )}
        </MainContent>
    );
  }
}

const mapStateToProps = (state) => ({
  listas: getListasPerfil(state)
});

export default withRouter(withProfile(connect(
  mapStateToProps
)(ListasPerfil)));