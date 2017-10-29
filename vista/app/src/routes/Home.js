import React from 'react';

import AuthRoute from './AuthRoute';
import App from '../views/App';

const Home = ({authorized}) => (
  <AuthRoute authorized={authorized} redirectTo="/login"  path="/" exact component={App}/>
);

export default Home;
