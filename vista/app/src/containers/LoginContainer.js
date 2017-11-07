import React, {Component} from 'react';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
// Cambiar esto por el request verdadero
import {requestLogin} from '../actions/loginActions';

import LoginForm from '../components/FormLogin';
import FormWrapper from '../components/FormWrapper';

class LoginContainer extends Component {
  constructor (props) {
    super(props);
    this.state = {
      usuario: {
        value: ''
      },
      password: {
        value: ''
      }
    }
  }

  onFormChange = (changedFields) => {
    this.setState({...this.state, ...changedFields});
  }

  onSubmit = (e, {usuario, password}) => {
    const {requestLogin} = this.props;
    requestLogin(usuario, password);
  }

  render () {
    const {error} = this.props;
    const {usuario, password} = this.state;
    return (
      <FormWrapper 
        error={error}
        title={'Ingrese su mail y contraseña'}
      >
        <LoginForm
          usuario={usuario}
          password={password}
          onChange={this.onFormChange}
          onSubmit={this.onSubmit}
        />
      </FormWrapper>
    );
  }

} 

const mapStateToProps = (state) => ({
  error: state.loginReducer.error
});

const mapDispatchToProps = (dispatch) => ({
  requestLogin: bindActionCreators(requestLogin, dispatch)
});

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(LoginContainer);
