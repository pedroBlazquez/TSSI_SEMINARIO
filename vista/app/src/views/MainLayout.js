import React from 'react';

import { Layout } from 'antd';

const { Header, Footer } = Layout;

const MainLayout = ({header, footer, children}) => (
  <Layout >
    <Header className={'bg-color-light-gray'}>
      {header}
    </Header>
    <Layout className={'main-container'}>
        {children}
    </Layout>
    <Footer className={'bg-color-light-gray'}>
      {footer}
    </Footer>
  </Layout>
);

export default MainLayout;