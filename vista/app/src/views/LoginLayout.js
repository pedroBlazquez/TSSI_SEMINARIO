import React from 'react';
import { Row, Col } from 'antd';

import '../styles/LoginLayout.css';

const LoginLayout = ({children}) => (
  <Row className="login-layout" type="flex" justify="space-around" align="middle">
    <Col span="8"></Col>
    <Col span="8">{children}</Col>
    <Col span="8"></Col>
  </Row>
);

export default LoginLayout;