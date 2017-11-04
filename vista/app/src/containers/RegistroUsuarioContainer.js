import React, {Component} from 'react';
import {connect} from 'react-redux';
import {withRouter} from 'react-router';

import {TIPOS_USUARIO, USUARIO_MUSICO, USUARIO_OYENTE} from '../utils/constants';

import RegistroUsuarioForm from '../components/RegistroUsuarioForm';

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
    const {match, location, history} = this.props;
    return (
      <RegistroUsuarioForm
        onSubmit={this.onSubmit}
        usuario={this.state.usuario}
        password={this.state.password}
        onChange={this.onFormChange}
      />
    );
  }
}

export default connect(

)(RegistroUsuarioContainer)