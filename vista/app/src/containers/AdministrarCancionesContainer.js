import React, {Component} from 'react';
import {connect} from 'react-redux';
import {Modal} from 'antd';

import Contenido from '../components/Contenido';
import AltaCancion from '../components/FormAltaCancion';
import AdministrarContenido from '../components/AdministrarContenido';

class AdministrarCancionesContainer extends Component {

  render () {
    const {onEliminar, onEditar, items} = this.props;

    return (
      <AdministrarContenido 
        FormElement={AltaCancion}
        formElementProps={{
          onSubmit: (e, v) => {console.log(v)}
        }}
        modalTitle={'Alta Cancion'}
        contenidoProps={{
          onEliminar,
          onEditar,
          items,
          agregarButtonText: 'Agregar Cancion'
        }}
      />
    );
  }
}

const mapStateToProps = (state) => ({
  items: [{id:1 , descripcion: '1'}, {id:2 , descripcion: '1'}, {id:3 , descripcion: '1'}]
}); 

export default connect(
  mapStateToProps
)(AdministrarCancionesContainer);
