import React, {Component} from 'react';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';
import moment from 'moment';

import AltaEvento from '../components/FormAltaEvento';
import AdministrarContenido from '../components/AdministrarContenido';
import {mapContentName} from '../utils/utils'; 

import {getEventosPerfil} from '../selectors/perfil';

import {altaEvento, modEvento, bajaEvento} from '../actions/contenidoActions';

const initialState = {
  editando: null,
  evento: null,
  imagen: ''
};

class AdministrarEventosContainer extends Component {
  constructor (props) {
    super(props);
    this.state = initialState;
  }

  onEditar = (id) => {
    const {eventos} = this.props;
    const evento = eventos.find(c => c.id === id);

    this.setState({
      evento: {
        nombre: {
          value: evento.nombre
        },
        descripcion: {
          value: evento.descripcion
        },
        direccion: {
          value: evento.direccion
        },
        fecha : {
          value: moment(evento.fechaEvento)
        },
        costo : {
          value: evento.costo
        }
      },
      editando: id,
      imagen: evento.imagen
    });
  }

  onEliminar = (id) => {
    const {baja} = this.props;
    baja(id);
  }

  onFormChange = (changedFields) => {
    if (this.state.editando !== null) {
      const {evento} = this.state;
      this.setState({evento: {...evento,...changedFields}});
    }
  }

  onSubmit = (e, values) => {
    const {onSubmit, onUpdate, alta, modificar} = this.props;
    if (this.state.editando !== null) {
      const {editando} = this.state;
      const evento = {
        ...values,
        imagen: values.imagen || this.state.imagen,
        idEvento: editando.toString(),
      };
      this.setState(initialState);
      modificar(evento);
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
    const {eventos} = this.props;
    return (
      <AdministrarContenido 
        FormElement={AltaEvento}
        formElementProps={{
          onSubmit: this.onSubmit,
          onCancel: this.onCancel,
          onChange: this.onFormChange,
          imagen: this.state.imagen,
          eventos: mapContentName(eventos, this.state.editando),
          ...this.state.evento
        }}
        modalTitle={this.state.editando === null ? 'Alta Evento' : 'Actualizar Evento'}
        contenidoProps={{
          onEliminar: this.onEliminar,
          onEditar: this.onEditar,
          actions: {editable: true},
          items: eventos.map(c => ({id: c.id, descripcion: c.nombre})),
          agregarButtonText: 'Agregar Evento'
        }}
      />
    );
  }
}

const mapDispatchToProps = (dispatch) => ({
  alta: bindActionCreators(altaEvento, dispatch),
  modificar: bindActionCreators(modEvento, dispatch),
  baja: bindActionCreators(bajaEvento, dispatch)
});

const mapStateToProps = (state) => ({
  eventos: getEventosPerfil(state)
}); 

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(AdministrarEventosContainer);
