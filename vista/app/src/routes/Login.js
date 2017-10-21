import React from 'react';
import {Route} from 'react-router';
import LoginLayout from '../views/LoginLayout';

import LoginForm from '../components/LoginForm';

const Login = () => (
  
    <LoginForm />
  
);

const LoginRoute = () => (
  <Route component={Login} path="/login"/>
); 

export default LoginRoute;