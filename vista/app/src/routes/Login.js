import React from 'react';
import AuthRoute from './AuthRoute';
import LoginLayout from '../views/LoginLayout';

import LoginForm from '../containers/LoginContainer';

const Login = () => (
  <LoginLayout>
    <LoginForm />
  </LoginLayout>
);

const LoginRoute = ({isLoggedIn}) => (
  <AuthRoute component={Login} authorized={!isLoggedIn} redirectTo="/" exact path="/login"/>
); 

export default LoginRoute;