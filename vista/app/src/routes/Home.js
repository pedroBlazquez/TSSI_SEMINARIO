import React from 'react';

import AuthRoute from './AuthRoute';
import MainLayout from '../views/MainLayout';

const HomeView = () => (
  <MainLayout>
    <div>{'Welcome'}</div>
  </MainLayout>
);

const Home = ({authorized}) => (
  <AuthRoute authorized={authorized} redirectTo="/login"  path="/" exact component={HomeView}/>
);

export default Home;
