import React from 'react';
import ReactDOM from 'react-dom';
import { Provider } from 'react-redux';
import { createStore } from 'redux';

import rootReducer from './reducers';
import Route from './routes';
import registerServiceWorker from './utils/registerServiceWorker';

import 'antd/dist/antd.css';
import './styles/index.css';
import './styles/errors.css';

const store = createStore(rootReducer);

ReactDOM.render(
  <Provider store={store}>
    <Route />
  </Provider>, 
  document.getElementById('root')
);
registerServiceWorker();
