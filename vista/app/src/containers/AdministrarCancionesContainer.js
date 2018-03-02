import React, {Component} from 'react';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';

import {GENEROS} from '../utils/constants';
import {mapContentName} from '../utils/utils';

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

    this.setState({
      cancion: {
        nombre: {
          value: cancion.nombre
        },
        genero: {
          value: cancion.genero.id
        }
      },
      editando: id,
      audio: cancion.archivo
    });
  }

  onEliminar = (id) => {
    const {baja} = this.props;
    baja(id);
  }

  onFormChange = (changedFields) => {
    if (this.state.editando !== null) {
      const {cancion} = this.state;
      this.setState({cancion: {...cancion,...changedFields}});
    }
  }

  onSubmit = (e, values) => {
    const {onSubmit, onUpdate, alta, modificar} = this.props;
    if (this.state.editando !== null) {
      const {editando} = this.state;
      const audio = values.audio || this.state.audio;
      const cancion = {
        ...values,
        audio,
        idCancion: editando.toString(),
        genero: GENEROS.find(g => g.id === values.genero).value
      };
      this.setState(initialState);
      modificar(cancion);
    } else {
      alta(values);
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
          canciones: mapContentName(canciones, this.state.editando),
          ...this.state.cancion,
          audio: this.state.audio
        }}
        modalTitle={this.state.editando === null ? 'Alta Cancion' : 'Actualizar Canción'}
        contenidoProps={{
          onEliminar: this.onEliminar,
          onEditar: this.onEditar,
          actions: {editable: true},
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
  canciones: getCancionesPerfil(state)
}); 

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(AdministrarCancionesContainer);
