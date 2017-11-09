import React, {Component} from 'react';
import {Layout} from 'antd';

const Sider = Layout.Sider;
const Content = Layout.Content;

class PerfilLayout extends Component {
  render () {
    const {children, sider} = this.props;
    return (
      <Layout>
        <Sider className={'bg-color-light-gray'}>
          {sider}
        </Sider>
        <Content className={'bg-color-light-gray'}>
          {children}
        </Content>
      </Layout>
    );
  }
}

export default PerfilLayout;