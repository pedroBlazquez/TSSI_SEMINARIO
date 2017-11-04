import React, {Component} from 'react';
import {connect} from 'react-redux';
import {withRouter} from 'react-router';
import moment from 'moment';

import {TIPOS_USUARIO, USUARIO_ARTISTA, USUARIO_OYENTE, USUARIO_BANDA} from '../utils/constants';

import RegistroUsuarioForm from '../components/RegistroUsuarioForm';
import FormWrapper from '../components/FormWrapper';
import DatosArtistaForm from '../components/DatosArtistaForm';

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
  }
};

class RegistroUsuarioContainer extends Component {
  constructor (props) {
    super(props);

    this.state = {
      usuarioFields : initialUsuarioFields,
      artistaFields: initialUsuarioFields,
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
            onChange={this.onFormChange}
            {...this.state.usuarioFields}
          />
        );
      case USUARIO_ARTISTA:
      case USUARIO_BANDA:
        return (
          <DatosArtistaForm
            onSubmit={this.onSubmit}
            onChange={this.onFormChange}
            onCancel={this.onCancel}
            esBanda={formType === USUARIO_BANDA}
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
        this.setState({artistaFields: {...this.state.artistaFields, ...changedFields}});
        break;
    }
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