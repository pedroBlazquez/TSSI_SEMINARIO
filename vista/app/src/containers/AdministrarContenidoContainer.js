import React, {Component} from 'react';
import {connect} from 'react-redux';
import {Tabs} from 'antd';

import '../styles/AdministrarContenido.css';

import PerfilWrapper from '../components/PerfilContentWrapper';
import AdministrarDiscos from './AdministrarDiscosContainer';
import AdministrarCanciones from './AdministrarCancionesContainer';
import AdministrarEventos from './AdministrarEventosContainer';
import AdministrarAlbumes from './AdminsitrarAlbumesContainer';

const TabPane = Tabs.TabPane;

class AdministrarContenidoContainer extends Component {
  
  render () {
    return (
      <PerfilWrapper>
        <Tabs className={'full-height bg-color-white table-container'}>
          <TabPane tab="Canciones" key="1">
            <AdministrarCanciones />
          </TabPane>
          <TabPane tab="Discos" key="2">
            <AdministrarDiscos />
          </TabPane>
          <TabPane tab="Albumes" key="3">
            <AdministrarAlbumes />
          </TabPane>
          <TabPane tab="Eventos" key="4">
            <AdministrarEventos />
          </TabPane>
        </Tabs>
      </PerfilWrapper>
    );
  }
}

const mapStateToProps = (state) => ({

});

export default connect(

)(AdministrarContenidoContainer);
