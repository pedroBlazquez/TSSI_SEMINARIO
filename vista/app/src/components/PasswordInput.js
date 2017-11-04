import React from 'react';
import {Input, Icon} from 'antd';
import {REQUIRED} from '../utils/validators';

const PasswordInput = ({getFieldDecorator}) => (
  getFieldDecorator('password', {rules: [REQUIRED]})(
    <Input prefix={<Icon type="lock" style={{ fontSize: 13 }} />} type="password" placeholder="Password"/>
  )
);

export default PasswordInput;
