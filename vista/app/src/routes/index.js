import React from 'react';
import {connect} from 'react-redux';
import {BrowserRouter} from 'react-router-dom';

import LoginRoute from './Login';
import Home from './Home';
import Registro from './Registro';

// TODO: Cambiar las rutas para un mejor approach
// https://css-tricks.com/react-router-4/

const MainRoute = ({isLogged}) => (
  <BrowserRouter>
    <div>
      <Home authorized={isLogged} />  
      <LoginRoute isLoggedIn={isLogged} />
      <Registro />
    </div>
  </BrowserRouter>
);

const mapStateToProps = (state) => ({
  isLogged: state.loginReducer.success
}); 

export default  connect(
  mapStateToProps
)(MainRoute);
