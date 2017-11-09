import React, {Component} from 'react';
import { Form, Button, Input } from 'antd';

import {DatosPersonalesValidator} from '../utils/validators';

import ExtendedFrom from './ExtendedForm';

const FormItem = Form.Item;
const TextArea = Input.TextArea;

class NuevaPublicacion extends Component {
  render () {
    const {onSubmit, form} = this.props;
    return (
      <Form onSubmit={onSubmit}>
        <FormItem>
          {DatosPersonalesValidator({form})('publicacion')(<TextArea rows={4}/>)}
        </FormItem>
        <FormItem>
          <Button htmlType="submit" className={'green-button'}>{'Publicar'}</Button>
        </FormItem>
      </Form>
    );
  }
}

export default Form.create()(ExtendedFrom(NuevaPublicacion));