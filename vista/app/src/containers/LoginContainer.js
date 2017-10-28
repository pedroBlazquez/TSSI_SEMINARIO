import React, {Component} from 'react';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import LoginForm from '../components/LoginForm';
// Cambiar esto por el request verdadero
import {errorLogin} from '../actions/loginActions';

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

  onSubmit = ({usuario, password}) => {
    const {requestLogin} = this.props;
    // requestLogin(usuario, password);
    requestLogin('Usuario no existente o password incorrecta');
  }

  render () {
    const {error} = this.props;
    const {usuario, password} = this.state;
    return (
      <LoginForm
        usuario={usuario}
        password={password}
        onChange={this.onFormChange}
        onSubmit={this.onSubmit}
        error={error}
      />
    );
  }

} 

const mapStateToProps = (state) => ({
  error: state.loginReducer.error
});

const mapDispatchToProps = (dispatch) => ({
  requestLogin: bindActionCreators(errorLogin, dispatch)
});

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(LoginContainer);
