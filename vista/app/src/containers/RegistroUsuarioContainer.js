import React, {Component} from 'react';
import {connect} from 'react-redux';
import {withRouter} from 'react-router';

import {TIPOS_USUARIO, USUARIO_MUSICO, USUARIO_OYENTE} from '../utils/constants';

import RegistroUsuarioForm from '../components/RegistroUsuarioForm';
import FormWrapper from '../components/FormWrapper';

class RegistroUsuarioContainer extends Component {
  constructor (props) {
    super(props);

    this.state = {
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
        value: null
      },
      tipoUsuario: USUARIO_OYENTE
    };
  }

  onFormChange = (changedFields) => {
    this.setState({...this.state, ...changedFields});
  }

  onSubmit = (values) => {
    console.log(values);
  }

  render () {
    const {error} = this.props;
    return (
      <FormWrapper
        error={error}
        title={'Complete sus datos'}
      >
        <RegistroUsuarioForm
          onSubmit={this.onSubmit}
          usuario={this.state.usuario}
          password={this.state.password}
          fechaNacimiento={this.state.fechaNacimiento}
          nombre={this.state.nombre}
          apellido={this.state.apellido}
          onChange={this.onFormChange}
        />
      </FormWrapper>
    );
  }
}

export default connect(

)(RegistroUsuarioContainer)