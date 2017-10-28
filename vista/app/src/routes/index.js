import React from 'react';
import {connect} from 'react-redux';
import {
  BrowserRouter,
  Switch
} from 'react-router-dom';

import LoginRoute from './Login';
import Home from './Home';

// TODO: Cambiar las rutas para un mejor approach
// https://css-tricks.com/react-router-4/

const MainRoute = ({isLogged}) => (
  <BrowserRouter>
  <div>
    <Home authorized={isLogged} />  
    <LoginRoute />
  </div>
  </BrowserRouter>
);

const mapStateToProps = (state) => ({
  isLogged: state.loginReducer.success
}); 

export default  connect(
  mapStateToProps
)(MainRoute);
