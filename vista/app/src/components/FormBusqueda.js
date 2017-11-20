import React, {Component} from 'react';
import {Form, Button, Input} from 'antd';

import ExtendedForm from './ExtendedForm';

const FormItem = Form.Item;

class FormBusqueda extends Component {

  render () {
    const {onSubmit, onCancel} = this.props;
    return (
      <Form onSubmit={onSubmit} inline>
        <FormItem>
          <Input type={'text'} placeholder='Buscar...'/>
        </FormItem>
        <FormItem>
          <Button>Buscar</Button>
        </FormItem>
      </Form>
    );
  }
}

export default Form.create({})(ExtendedForm(FormBusqueda));
