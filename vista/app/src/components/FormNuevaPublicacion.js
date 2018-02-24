import React, {Component} from 'react';
import { Form, Button, Input } from 'antd';

import {RequiredValidator} from '../utils/validators';

import ExtendedFrom from './ExtendedForm';

const FormItem = Form.Item;
const TextArea = Input.TextArea;

class NuevaPublicacion extends Component {
  render () {
    const {onSubmit, form} = this.props;
    return (
      <Form onSubmit={onSubmit}>
        <FormItem>
          {RequiredValidator({form})('publicacion')(<TextArea rows={4} maxLength={'250'}/>)}
        </FormItem>
        <FormItem>
          <Button htmlType="submit" className={'green-button'}>{'Publicar'}</Button>
        </FormItem>
      </Form>
    );
  }
}

export default Form.create({
  onFieldsChange(props, changedFields) {
    if (typeof props.onChange === 'function') {
      props.onChange(changedFields);
    }
  },
  mapPropsToFields(props) {
    return {
      publicacion: {...props.publicacion},
    };
  }
})(ExtendedFrom(NuevaPublicacion));