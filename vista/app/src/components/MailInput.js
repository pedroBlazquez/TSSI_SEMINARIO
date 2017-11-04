import React from 'react';
import {Input, Icon} from 'antd';
import { REQUIRED, MAIL} from '../utils/validators';

const MailInput = ({getFieldDecorator, mapTo}) => (
  getFieldDecorator(mapTo, {rules: [REQUIRED, MAIL]})(
    <Input prefix={<Icon type="mail" style={{ fontSize: 13 }} />} placeholder="email@mail.com"/>
  )
);

export default MailInput;
