import React from 'react';
import {
  BrowserRouter,
  Route
} from 'react-router-dom';

import App from '../views/App';

export default () => (
  <BrowserRouter>
    <div>
      <Route path="/" exact component={App}/>
      <Route path="/hola" component={() => <div>Saludos</div>}/>
    </div>
  </BrowserRouter>
); 