import React, {Component} from 'react';
import '../styles/LoginForm.css';
import { Form, Icon, Input, Button } from 'antd';

const requiredRule = {required: true, message: 'Complete este campo'};

const FormItem = Form.Item;

const UserInput = ({getFieldDecorator}) => (
  getFieldDecorator('usuario', {rules: [requiredRule, {type: 'email', message: 'Formato no valido'}]})(
    <Input prefix={<Icon type="mail" style={{ fontSize: 13 }} />} placeholder="email@mail.com"/>
  )
);

const PassInput = ({getFieldDecorator}) => (
  getFieldDecorator('password', {rules: [requiredRule]})(
    <Input prefix={<Icon type="lock" style={{ fontSize: 13 }} />} type="password" placeholder="Password"/>
  )
);

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
    const {validateFields, isFieldTouched, getFieldError} = form;
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
      <div className="form-container">
        <h2 className="title">Ingrese su e-mail y contraseña</h2>
        {error  && <div className="error-container">{'Verifique su mail y contraseña'}</div>}
        <Form layout="vertical" onSubmit={this.handleSubmit}>
          <FormItem
            validateStatus={fieldErrors.usuario ? 'error' : ''}
            help={fieldErrors.usuario || ''}
          >
            <UserInput {...form} />
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
      </div>
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