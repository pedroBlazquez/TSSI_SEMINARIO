import React from 'react';
import { Row, Col } from 'antd';

import '../styles/LoginLayout.css';

const LoginLayout = ({children}) => (
  <Row className={'login-layout'} type="flex" justify="center" align="middle">
    <Col span="8">{children}</Col>
  </Row>
);

export default LoginLayout;