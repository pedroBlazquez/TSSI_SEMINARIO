import React, {Component} from 'react';
import {connect} from 'react-redux';
import {withRouter} from 'react-router';

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
      }
    };
  }

  onFormChange = (changedFields) => {
    this.setState({...this.state, ...changedFields});
  }

  render () {
    const {match, location, history} = this.props;
    return (
      <RegistroUsuarioForm 
        usuario={this.state.usuario}
        password={this.state.password}
        onChange={this.onFormChange}
      />
    );
  }
}

export default withRouter(connect(

)(RegistroUsuarioContainer))