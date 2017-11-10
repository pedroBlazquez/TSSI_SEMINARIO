import React, {Component} from 'react';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';

import {GENEROS} from '../utils/constants';

import AltaDisco from '../components/FormAltaDisco';
import AdministrarContenido from '../components/AdministrarContenido';

import {getDiscosPerfil, getCancionesPerfil} from '../selectors/perfil';
import {altaDisco, modDisco, bajaDisco} from '../actions/contenidoActions';

const initialState = {
  editando: null,
  disco: null
};

class AdministrarCancionesContainer extends Component {
  constructor (props) {
    super(props);
    this.state = initialState;
  }

  onEditar = (id) => {
    const {discos} = this.props;
    const disco = discos.find(c => c.id === id);

    this.setState({disco: {
      nombre: {
        value: disco.nombre
      },
      genero: {
        value: ''
      }
    }, editando: id});
  }

  onEliminar = (id) => {
    const {baja} = this.props;
    baja(id);
  }

  onFormChange = (changedFields) => {
    if (this.state.editando !== null) {
      const {disco} = this.state;
      this.setState({disco: {...disco,...changedFields}});
    }
  }

  onSubmit = (e, values) => {
    const {onSubmit, onUpdate, alta, modificar} = this.props;
    if (this.state.editando !== null) {
      const {editando} = this.state;
      const disco = {
        ...values,
        idDisco: editando.toString(),
        genero: GENEROS.find(g => g.id === values.genero).value
      };
      modificar(disco);
      this.setState(initialState);
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
    const {discos, canciones} = this.props;
    if (!canciones.length) return (<div>{'Debe crear una canción antes de poder operar con discos'}</div>);
    return (
      <AdministrarContenido 
        FormElement={AltaDisco}
        formElementProps={{
          onSubmit: this.onSubmit,
          onCancel: this.onCancel,
          onChange: this.onFormChange,
          canciones,
          ...this.state.disco
        }}
        modalTitle={this.state.editando === null ? 'Alta Disco' : 'Actualizar Disco'}
        contenidoProps={{
          onEliminar: this.onEliminar,
          onEditar: this.onEditar,
          items: discos.map(d => ({id: d.id, descripcion: d.nombre})),
          agregarButtonText: 'Agregar Disco'
        }}
      />
    );
  }
}

const mapDispatchToProps = (dispatch) => ({
  alta: bindActionCreators(altaDisco, dispatch),
  modificar: bindActionCreators(modDisco, dispatch),
  baja: bindActionCreators(bajaDisco, dispatch)
});

const mapStateToProps = (state) => ({
  discos: getDiscosPerfil(state),
  canciones: getCancionesPerfil(state)
}); 

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(AdministrarCancionesContainer);
