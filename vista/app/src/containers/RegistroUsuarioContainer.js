import React, {Component} from 'react';
import {connect} from 'react-redux';
import {withRouter} from 'react-router';
import moment from 'moment';

import {TIPOS_USUARIO, USUARIO_ARTISTA, USUARIO_OYENTE, USUARIO_BANDA} from '../utils/constants';

import RegistroUsuarioForm from '../components/FormRegistroUsuario';
import FormWrapper from '../components/FormWrapper';
import DatosArtistaForm from '../components/FormDatosArtista';

const initialUsuarioFields = {
  usuario: {
    value: ''
  },
  password: {
    value: ''
  },
  nombre: {
    value: ''
  },
  apellido: {
    value: ''
  },
  fechaNacimiento: {
    value: moment()
  },
  tipoUsuario: {
    value: USUARIO_OYENTE
  }
};

const initialArtistaFields = {
  generos: {
    value: []
  },
  fechaInicio: {
    value: moment()
  },
  descripcion: {
    value: ''
  },
  nombreFantasia: {
    value: ''
  },
  integrantes: []
};

class RegistroUsuarioContainer extends Component {
  constructor (props) {
    super(props);

    this.state = {
      usuarioFields : initialUsuarioFields,
      artistaFields: initialArtistaFields,
      formType: USUARIO_OYENTE
    };
  }

  renderForm = () => {
    const {formType, usuarioFields} = this.state;
    switch (formType) {
      case USUARIO_OYENTE :
        return (
          <RegistroUsuarioForm
            onSubmit={this.onSubmit}
            onChange={this.onDatosUsuarioChange}
            {...this.state.usuarioFields}
          />
        );
      case USUARIO_ARTISTA:
      case USUARIO_BANDA:
        return (
          <DatosArtistaForm
            onSubmit={this.onSubmit}
            onChange={this.onDatosArtistaChange}
            onCancel={this.onCancel}
            esBanda={formType === USUARIO_BANDA}
            agregarIntegrante={this.agregarIntegrante}
            {...this.state.artistaFields}
          />
        );
    }
  }

  onFormChange = (changedFields) => {
    switch (this.state.formType) {
      case USUARIO_OYENTE:
        this.setState({usuarioFields: {...this.state.usuarioFields, ...changedFields}});
        break;
      case USUARIO_ARTISTA:
      case USUARIO_BANDA:
        const artistaFields = this.state.artistaFields;
        this.setState({artistaFields: {...artistaFields, ...changedFields}});
        break;
    }
  }

  onDatosUsuarioChange = (changedFields) => {
    this.setState({usuarioFields: {...this.state.usuarioFields, ...changedFields}});
  }

  onDatosArtistaChange = (changedFields) => {
    const artistaFields = this.state.artistaFields;
    this.setState({artistaFields: {...artistaFields, ...changedFields}});
  }

  onSubmit = (e, values) => {
    if (values.tipoUsuario !== USUARIO_OYENTE && this.state.formType === USUARIO_OYENTE) {
      this.setState({formType: values.tipoUsuario});
    } else {

    }
  }

  onCancel = () => {
    this.setState({formType: USUARIO_OYENTE, artistaFields: initialArtistaFields});
  }

  agregarIntegrante = (e, integrante) => {
    const state = this.state;
    const artistaFields = state.artistaFields;
    const integrantes = [...artistaFields.integrantes];
    const integranteNuevo = {...integrante, key: integrantes.length};
    integrantes.push(integranteNuevo);
    this.setState({artistaFields: Object.assign({}, artistaFields, {integrantes})});
  }

  render () {
    const {error} = this.props;
    return (
      <FormWrapper
        error={error}
        title={'Complete sus datos'}
      >
        {this.renderForm()}
      </FormWrapper>
    );
  }
}

export default connect(

)(RegistroUsuarioContainer)