import React, {Component} from 'react';
import {Input, Icon} from 'antd';

class MailInput extends Component {
  render () {
    return (
      <Input
        prefix={<Icon type="mail" style={{ fontSize: 13 }} />}
        placeholder="email@mail.com"
        {...this.props}
      />
    );
  }
}

export default MailInput;
