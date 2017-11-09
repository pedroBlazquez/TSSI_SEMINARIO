import React, {Component} from 'react';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';
import {Modal} from 'antd';

import {GENEROS} from '../utils/constants'

import AltaCancion from '../components/FormAltaCancion';
import AdministrarContenido from '../components/AdministrarContenido';

import {getCancionesPerfil} from '../selectors/perfil';

import {altaCancion, modCancion, bajaCancion} from '../actions/contenidoActions';

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
      },
      genero: {
        value: ''
      }
    }, editando: id});
  }

  onEliminar = (id) => {
    const {baja, user} = this.props;
    baja(id, user.idArtista);
  }

  onFormChange = (changedFields) => {
    if (this.state.editando !== null) {
      const {cancion} = this.state;
      this.setState({cancion: {...cancion,...changedFields}});
    }
  }

  onSubmit = (e, values) => {
    const {onSubmit, onUpdate, alta, modificar, user} = this.props;
    if (this.state.editando !== null) {
      const {editando} = this.state;
      const cancion = {
        ...values,
        idCancion: editando.toString(),
        genero: GENEROS.find(g => g.id === values.genero).value
      };
      modificar(cancion, user.idArtista);
      this.setState(initialState);
    } else {
      alta(values, user.idArtista);
    }
  }

  onCancel = () => {
    if (this.state.editando !== null) {
      this.setState(initialState);
    }
  }

  render () {
    const {canciones} = this.props;
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
          onEliminar: this.onEliminar,
          onEditar: this.onEditar,
          items: canciones.map(c => ({id: c.id, descripcion: c.nombre})),
          agregarButtonText: 'Agregar Cancion'
        }}
      />
    );
  }
}

const mapDispatchToProps = (dispatch) => ({
  alta: bindActionCreators(altaCancion, dispatch),
  modificar: bindActionCreators(modCancion, dispatch),
  baja: bindActionCreators(bajaCancion, dispatch)
});

const mapStateToProps = (state) => ({
  canciones: getCancionesPerfil(state),
  user: {idArtista: 3}
}); 

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(AdministrarCancionesContainer);
