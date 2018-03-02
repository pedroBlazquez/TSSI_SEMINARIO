import React, {Component} from 'react';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';

import {GENEROS} from '../utils/constants';
import {mapContentName} from '../utils/utils';

import AltaAlbum from '../components/FormAltaAlbum';
import AdministrarContenido from '../components/AdministrarContenido';

import {getDiscosPerfil, getAlbumesPerfil} from '../selectors/perfil';
import {altaAlbum, bajaAlbum, modAlbum} from '../actions/contenidoActions';

const initialState = {
  editando: null,
  album: null,
  portada: ''
};

class AdministrarCancionesContainer extends Component {
  constructor (props) {
    super(props);
    this.state = initialState;
  }

  onEditar = (id) => {
    const {albumes} = this.props;
    const album = albumes.find(c => c.id === id);

    this.setState({
      album: {
        nombre: {
          value: album.nombre
        },
        discosSeleccionados: album.discos
      },
      editando: id,
      portada: album.portada
    });
  }

  onEliminar = (id) => {
    const {baja} = this.props;
    baja(id);
  }

  onFormChange = (changedFields) => {
    if (this.state.editando !== null) {
      const {album} = this.state;
      this.setState({album: {...album,...changedFields}});
    }
  }

  onSubmit = (e, values) => {
    const {onSubmit, onUpdate, alta, modificar} = this.props;
    if (this.state.editando !== null) {
      const {editando, portada} = this.state;
      const album = {
        ...values,
        idAlbum: editando.toString(),
        discos: values.discos.map(d => d.id)
      };
      this.setState(initialState);
      modificar(album);
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
    const {albumes, discos} = this.props;
    if (!discos.length) return (<div>{'Debe crear una canción antes de poder operar con discos'}</div>);
    return (
      <AdministrarContenido 
        FormElement={AltaAlbum}
        formElementProps={{
          onSubmit: this.onSubmit,
          onCancel: this.onCancel,
          onChange: this.onFormChange,
          albumes: mapContentName(albumes, this.state.editando),
          discos,
          portada: this.state.portada,
          ...this.state.album
        }}
        modalTitle={this.state.editando === null ? 'Alta Album' : 'Actualizar Album'}
        contenidoProps={{
          onEliminar: this.onEliminar,
          onEditar: this.onEditar,
          actions: {editable: true},
          items: albumes.map(d => ({id: d.id, descripcion: d.nombre})),
          agregarButtonText: 'Agregar Album'
        }}
      />
    );
  }
}

const mapDispatchToProps = (dispatch) => ({
  alta: bindActionCreators(altaAlbum, dispatch),
  modificar: bindActionCreators(modAlbum, dispatch),
  baja: bindActionCreators(bajaAlbum, dispatch)
});

const mapStateToProps = (state) => ({
  discos: getDiscosPerfil(state),
  albumes: getAlbumesPerfil(state)
}); 

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(AdministrarCancionesContainer);
