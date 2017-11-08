import React, {Component} from 'react';

import {Tabs} from 'antd';
import PerfilWrapper from './PerfilContentWrapper';

const TabPane = Tabs.TabPane;

class AdminsitrarContenido extends Component {
  render () {
    const {canciones, discos, albumes, eventos} = this.props;
    return (
      <PerfilWrapper>
        <Tabs className={'full-height bg-color-white'}>
          <TabPane tab="Canciones" key="1">
            {canciones}
          </TabPane>
          <TabPane tab="Discos" key="2">
            {discos}
          </TabPane>
          <TabPane tab="Albumes" key="3">
            {albumes}
          </TabPane>
          <TabPane tab="Eventos" key="4">
            {eventos}
          </TabPane>
        </Tabs>
      </PerfilWrapper>
    );
  }
}

export default AdminsitrarContenido;
