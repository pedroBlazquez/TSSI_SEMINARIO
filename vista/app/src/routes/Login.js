import React from 'react';
import {Route} from 'react-router';
import LoginLayout from '../views/LoginLayout';

import LoginForm from '../containers/LoginContainer';

const Login = () => (
  <LoginLayout>
    <LoginForm />
  </LoginLayout>
);

const LoginRoute = () => (
  <Route component={Login} path="/login"/>
); 

export default LoginRoute;