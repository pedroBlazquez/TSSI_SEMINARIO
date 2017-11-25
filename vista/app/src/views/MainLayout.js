import React from 'react';

import { Layout } from 'antd';

const { Header } = Layout;

const MainLayout = ({header, children}) => (
  <Layout >
    <Header className={'bg-color-light-gray'}>
      {header}
    </Header>
    <Layout className={'main-container'}>
        {children}
    </Layout>
  </Layout>
);

export default MainLayout;