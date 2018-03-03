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

  constructor (props) {
    super(props);
    this.state = {
      estados: [
        {key: "1", estado: true},
        {key: "2", estado: false},
        {key: "3", estado: false},
        {key: "4", estado: false},
      ]
    };
  }

  onChangeHandler = (currentTab) => {
    let {estados} = this.state;
    estados.map((e) => e.estado = e.key === currentTab);
    this.setState({estados});
  }

  render () {
    const {estados} = this.state;
    return (
      <PerfilWrapper>
        <Tabs className={'full-height bg-color-white table-container administrarTabs'} onChange={this.onChangeHandler}>
          <TabPane tab="Canciones" key="1">
            <AdministrarCanciones mostrar={estados[0].estado}/>
          </TabPane>
          <TabPane tab="Discos" key="2">
            <AdministrarDiscos mostrar={estados[1].estado}/>
          </TabPane>
          <TabPane tab="Albumes" key="3">
            <AdministrarAlbumes mostrar={estados[2].estado}/>
          </TabPane>
          <TabPane tab="Eventos" key="4">
            <AdministrarEventos mostrar={estados[3].estado}/>
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
