import React from 'react';
import '../styles/LoginForm.css';
import { Form, Icon, Input, Button } from 'antd';

const FormItem = Form.Item;

const LoginForm = ({error}) => (
  <div className="form-container">
    <h2 className="title">Ingrese su e-mail y contraseña</h2>
    <div className="error-container">{'Verifique su mail y contraseña'}</div>
    <Form layout="vertical" onSubmit={this.handleSubmit}>
      <FormItem>
        <Input prefix={<Icon type="mail" style={{ fontSize: 13 }} />} placeholder="email@mail.com" />
      </FormItem>
      <FormItem>
          <Input prefix={<Icon type="lock" style={{ fontSize: 13 }} />} type="password" placeholder="Password" />
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
          type="default"
          htmlType="button"
          className="login-button registrarse"
        >
          {'Registrarse'}
        </Button>
      </FormItem>
    </Form>
  </div>
);

export default LoginForm;