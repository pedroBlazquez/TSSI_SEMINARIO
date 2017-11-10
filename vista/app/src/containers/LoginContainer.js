import React, {Component} from 'react';
import {bindActionCreators} from 'redux';
import {withRouter} from 'react-router-dom';
import {connect} from 'react-redux';
// Cambiar esto por el request verdadero
import {requestLogin, checkToken} from '../actions/loginActions';

import LoginForm from '../components/FormLogin';
import FormWrapper from '../components/FormWrapper';

class LoginContainer extends Component {

  componentWillMount () {
    this.props.checkForToken();
  }

  onSubmit = (e, {usuario, password}) => {
    const {requestLogin} = this.props;
    requestLogin(usuario, password);
  }

  componentWillReceiveProps (nextProps) {
    if (nextProps.isLogged) {
      this.props.history.push("/");
    }
  }

  render () {
    const {error} = this.props;
    return (
      <FormWrapper 
        error={error}
        title={'Ingrese su mail y contraseña'}
      >
        <LoginForm
          onChange={this.onFormChange}
          onSubmit={this.onSubmit}
        />
      </FormWrapper>
    );
  }

} 

const mapStateToProps = (state) => ({
  error: state.loginReducer.error,
  isLogged: state.loginReducer.success
});

const mapDispatchToProps = (dispatch) => ({
  requestLogin: bindActionCreators(requestLogin, dispatch),
  checkForToken: bindActionCreators(checkToken, dispatch)
});

export default withRouter(connect(
  mapStateToProps,
  mapDispatchToProps
)(LoginContainer));

