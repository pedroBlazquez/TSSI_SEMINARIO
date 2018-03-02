import React, {Component} from 'react';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';
import moment from 'moment';

import {getListasPerfil} from '../selectors/perfil';
import {crearLista, eliminarLista, modificarLista} from '../actions/listasReproduccionActions';

import MainContent from '../components/MainContent';
import AltaLista from '../components/FormAltaLista';
import AdministrarContenido from '../components/AdministrarContenido';
import ListaCanciones from '../components/ListaCanciones';
import { setCola } from '../actions/reproductorActions';

const initialState = {
  editando: null,
  lista: null
};

class AdministrarListasContainer extends Component {
  constructor (props) {
    super(props);
    this.state = initialState;
  }

  onEditar = (id) => {
    const {listas} = this.props;
    const lista = listas.find(c => c.id === id);

    this.setState({
      lista: {
        nombre: {
          value: lista.nombre
        },
        privacidad: {
          value: lista.privacidad
        },
        canciones: lista.canciones
      },
      editando: id
    });
  }

  onEliminar = (id) => {
    const {baja} = this.props;
    baja(id);
  }

  reproducir = (lista) => {
    this.props.reproducir(lista.canciones);
  } 

  onFormChange = (changedFields) => {
    if (this.state.editando !== null) {
      const {lista} = this.state;
      this.setState({lista: {...lista,...changedFields}});
    }
  }

  onSubmit = (e, values) => {
    const {onSubmit, onUpdate, alta, modificar} = this.props;
    if (this.state.editando !== null) {
      const {editando} = this.state;
      const lista = {
        ...values,
        idLista: editando.toString(),
      };
      this.setState(initialState);
      modificar(lista);
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
    const {listas} = this.props;
    return (
      <MainContent>
        <div style={{width: '100%', height: '100%', backgroundColor: 'white'}}>
          <AdministrarContenido 
            FormElement={AltaLista}
            formElementProps={{
              onSubmit: this.onSubmit,
              onCancel: this.onCancel,
              onChange: this.onFormChange,
              ...this.state.lista
            }}
            modalTitle={this.state.editando === null ? 'Alta Lista' : 'Actualizar Lista'}
            contenidoProps={{
              onEliminar: this.onEliminar,
              onEditar: this.onEditar,
              reproducir: this.reproducir,
              actions: {editable: true, reproducible: true},
              items: listas.map(l => ({id: l.id, descripcion: l.nombre, canciones: l.canciones})),
              agregarButtonText: 'Agregar Lista',
              render: ({canciones}) => {
                return (<ListaCanciones canciones={canciones}/>);
              }
            }}
          />
        </div>
      </MainContent>
    );
  }
}

const mapDispatchToProps = (dispatch) => ({
  alta: bindActionCreators(crearLista, dispatch),
  modificar: bindActionCreators(modificarLista, dispatch),
  baja: bindActionCreators(eliminarLista, dispatch),
  reproducir: bindActionCreators(setCola, dispatch)
});

const mapStateToProps = (state) => ({
  listas: getListasPerfil(state)
}); 

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(AdministrarListasContainer);
