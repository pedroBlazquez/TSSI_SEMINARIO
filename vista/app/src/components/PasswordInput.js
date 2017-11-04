import React, {Component} from 'react';
import {Input, Icon} from 'antd';

class PasswordInput extends Component {
  render () {
    return (
      <Input
        prefix={<Icon type="lock" style={{ fontSize: 13 }} />}
        type="password"
        placeholder="Password"
        {...this.props}
      />
    );
  }
}

export default PasswordInput;