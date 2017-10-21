import React from 'react';
import ReactDOM from 'react-dom';
import 'antd/dist/antd.css';
import './styles/index.css';
import App from './views/App';
import registerServiceWorker from './utils/registerServiceWorker';

ReactDOM.render(<App />, document.getElementById('root'));
registerServiceWorker();
