import React, {Component} from 'react';
import AdminsitrarContenido from '../components/AdministrarContenido';

import AdministrarDiscos from './AdministrarDiscosContainer';
import AdministrarCanciones from './AdministrarCancionesContainer';

class AdministrarContenidoContainer extends Component {

  render () {
    return (
      <AdminsitrarContenido
        discos={<AdministrarDiscos />}
        eventos={'Eventos'}
        albumes={'Albumes'}
        canciones={<AdministrarCanciones />}
      />
    );
  }
}

export default AdministrarContenidoContainer;
