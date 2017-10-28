import React from 'react';
import ReactDOM from 'react-dom';
import { Provider } from 'react-redux';
import { createStore, applyMiddleware } from 'redux';
import createSagaMiddleware from 'redux-saga'

import rootReducer from './reducers';
import rootSaga from './sagas';
import Route from './routes';
import registerServiceWorker from './utils/registerServiceWorker';

import 'antd/dist/antd.css';
import './styles/index.css';
import './styles/errors.css';

const sagaMiddleware = createSagaMiddleware();
const store = createStore(rootReducer, window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__(), applyMiddleware(sagaMiddleware));

sagaMiddleware.run(rootSaga);

ReactDOM.render(
  <Provider store={store}>
    <Route />
  </Provider>, 
  document.getElementById('root')
);
registerServiceWorker();
