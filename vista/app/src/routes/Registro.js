import React from 'react';
import {Route} from 'react-router-dom';
import LoginLayout from '../views/LoginLayout';

import RegistroUsuarioContainer from '../containers/RegistroUsuarioContainer';

const RegistroUsuario = () => (
  <LoginLayout>
    <RegistroUsuarioContainer />
  </LoginLayout>
);

const RegistroRoute = ({isLoggedIn}) => (
  <Route component={RegistroUsuario} path="/registrarse"/>
); 

export default RegistroRoute;
