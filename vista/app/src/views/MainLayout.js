import React from 'react';

import { Layout } from 'antd';

const { Header, Footer } = Layout;

const MainLayout = ({header, footer, children}) => (
  <Layout>
    <Header>
      {header}
    </Header>
    <Layout>
        {children}
    </Layout>
    <Footer>
      {footer}
    </Footer>
  </Layout>
);

export default MainLayout;