import React, {Component} from 'react';
import '../styles/LoginForm.css';
import { Form, Button } from 'antd';

import {PasswordValidator, MailValidator} from '../utils/validators';

import ExtendedFrom from './ExtendedForm';
import FormWrapper from './FormWrapper';
import MailInput from './MailInput';
import PassInput from './PasswordInput';

const FormItem = Form.Item;

class LoginForm extends Component {
  render () {
    const {form, onSubmit} = this.props;
    return(
      <Form layout="vertical" onSubmit={onSubmit}>
        <FormItem>
          {MailValidator({form})('usuario')(<MailInput />)}
        </FormItem>
        <FormItem>
          {PasswordValidator({form})('password')(<PassInput/>)}   
        </FormItem>
        <FormItem>
          <Button
            type="primary"
            htmlType="submit"
            size="large"
            className="login-button ingresar"
          >
            {'Ingresar'}
          </Button>
        </FormItem>
        <FormItem>
          <Button
            className="login-button registrarse"
          >
            <a href="/registrarse">{'Registrarse'}</a>
          </Button>
        </FormItem>
      </Form>
    )
  }
  
} 

export default Form.create({
  onFieldsChange(props, changedFields) {
    props.onChange(changedFields);
  },
  mapPropsToFields (props) {
    return ({
      usuario: {...props.usuario},
      password: {...props.password}
    });
  }
})(ExtendedFrom(LoginForm));
