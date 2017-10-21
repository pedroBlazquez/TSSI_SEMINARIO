import React from 'react';
import {
  BrowserRouter,
  Route,
  Switch
} from 'react-router-dom';

import App from '../views/App';
import LoginRoute from './Login';

export default () => (
  <BrowserRouter>
    <Switch>
      <Route path="/" exact component={App}/>
      <LoginRoute/>
    </Switch>
  </BrowserRouter>
); 