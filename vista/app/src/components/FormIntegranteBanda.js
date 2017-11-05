import React, {Component} from 'react';
import {Form, Button, Input} from 'antd';

import ExtendedForm from './ExtendedForm';
import {DatosPersonalesValidator, FechaValidator} from '../utils/validators';

const FormItem = Form.Item;

class FormIntegranteBanda extends Component {

  render () {
    const {onSubmit, onCancel, form} = this.props;
    return (
      <Form onSubmit={onSubmit}>
        <FormItem>
          {DatosPersonalesValidator({form})('integrante')
            (<Input type={'text'} placeholder='Ingrese el nombre del integrante'/>)
          }
        </FormItem>
        <FormItem>
          {DatosPersonalesValidator({form})('rol')
            (<Input type={'text'} placeholder='Ingrese el rol del integrante'/>)
          }
        </FormItem>
        <FormItem>
          {DatosPersonalesValidator({form})('fechaNacimiento')
            (<Input type={'text'} placeholder='Ingrese la fecha de nacimiento del integrante'/>)
          }
        </FormItem>
        <FormItem>
          <div className={'flex flex-space-between'}>
            <Button onClick={onCancel} className={'white-button'}>
              {'Cancelar'}
            </Button>
            <Button htmlType="submit" className={'green-button'}>
              {'Agregar integrante'}
            </Button>
          </div>
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
})(ExtendedForm(FormIntegranteBanda));

