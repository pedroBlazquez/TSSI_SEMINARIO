import React, {Component} from 'react';
import {connect} from 'react-redux';
import {Modal} from 'antd';

import AltaCancion from '../components/FormAltaCancion';
import AdministrarContenido from '../components/AdministrarContenido';

const initialState = {
  editando: null,
  cancion: null
};

class AdministrarCancionesContainer extends Component {
  constructor (props) {
    super(props);
    this.state = initialState;
  }

  onEditar = (id) => {
    const {canciones} = this.props;
    const cancion = canciones.find(c => c.id === id);

    this.setState({cancion: {
      nombre: {
        value: cancion.nombre
      }
    }, editando: id});
  }

  onFormChange = (changedFields) => {
    if (this.state.editando !== null) {
      this.setState({cancion: {...changedFields}});
    }
  }

  onSubmit = (e, values) => {
    const {onSubmit, onUpdate} = this.props;
    if (this.state.editando !== null) {
      console.log('Se estaba editando');
      this.setState(initialState);
    } else {
      console.log('un alta nueva');
    }
  }

  onCancel = () => {
    if (this.state.editando !== null) {
      this.setState(initialState);
    }
  }

  render () {
    const {onEliminar, items, canciones} = this.props;
    return (
      <AdministrarContenido 
        FormElement={AltaCancion}
        formElementProps={{
          onSubmit: this.onSubmit,
          onCancel: this.onCancel,
          onChange: this.onFormChange,
          ...this.state.cancion
        }}
        modalTitle={this.state.editando === null ? 'Alta Cancion' : 'Actualizar Canción'}
        contenidoProps={{
          onEliminar,
          onEditar: this.onEditar,
          items,
          agregarButtonText: 'Agregar Cancion'
        }}
      />
    );
  }
}

const mapStateToProps = (state) => ({
  items: [{id:1 , descripcion: '1'}, {id:2 , descripcion: '1'}, {id:3 , descripcion: '1'}],
  canciones: [{id:1 , nombre: '1'}, {id:2 , nombre: '2'}, {id:3 , nombre: '1'}]
}); 

export default connect(
  mapStateToProps
)(AdministrarCancionesContainer);
