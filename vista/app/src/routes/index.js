import React from 'react';
import {
  BrowserRouter,
  Route,
  Switch
} from 'react-router-dom';

import App from '../views/App';
import LoginRoute from './Login';

// TODO: Cambiar las rutas para un mejor approach
// https://css-tricks.com/react-router-4/

export default () => (
  <BrowserRouter>
    <Switch>
      <Route path="/" exact component={App}/>
      <LoginRoute/>
    </Switch>
  </BrowserRouter>
); 