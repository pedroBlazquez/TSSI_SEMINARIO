import React, {Component} from 'react';
import '../styles/LoginForm.css';
import { Form, Button } from 'antd';

import FormWrapper from './FormWrapper';
import MailInput from './MailInput';
import PassInput from './PasswordInput';

const FormItem = Form.Item;

class LoginForm extends Component {

  constructor (props) {
    super(props);

    this.state = {
      fieldErrors: {
        usuario: '',
        password: ''
      }
    }
  }

  handleSubmit = (e) => {
    const {onSubmit, form} = this.props;
    const {validateFields, getFieldError} = form;
    e.preventDefault();
    validateFields((errors, values) => {
      if (!errors) {
        onSubmit(values);
      } else {
        const usuarioError = getFieldError('usuario');
        const passwordError = getFieldError('password');
        this.setState({fieldErrors: {usuario: usuarioError, password: passwordError}});
      }
    })
  }

  render () {
    const {form, error} = this.props;
    const {fieldErrors} = this.state;

    return(
      <FormWrapper 
        error={error}
        title={'Ingrese su mail y contraseña'}
      >
        <Form layout="vertical" onSubmit={this.handleSubmit}>
          <FormItem
            validateStatus={fieldErrors.usuario ? 'error' : ''}
            help={fieldErrors.usuario || ''}
          >
            <MailInput {...form} mapTo={'usuario'} />
          </FormItem>
          <FormItem
            validateStatus={fieldErrors.password ? 'error' : ''}
            help={fieldErrors.password || ''}
          >
            <PassInput {...form} />        
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
      </FormWrapper>
    )
  }
  
} 

export default Form.create({
  onFieldsChange(props, changedFields) {
    props.onChange(changedFields);
  },
  mapPropsToFields(props) {
    return {
      usuario: {
        value: props.usuario.value
      },
      password: {
        value: props.password.value
      }
    };
  } 
})(LoginForm);
