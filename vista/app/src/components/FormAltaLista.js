import React, {Component} from 'react';
import {Form, Button, Input, Checkbox} from 'antd';

import ExtendedForm from './ExtendedForm';
import FechaEvento from './FechaEvento';
import Upload from './UploadSingleFile';
import {
  DatosPersonalesValidator,
  FechaValidator,
  RequiredValidator,
  validateFile
} from '../utils/validators';

const TextArea = Input.TextArea;
const FormItem = Form.Item;

class FormAltaLista extends Component {

  render () {
    const {onSubmit, onCancel, form} = this.props;
    return (
      <Form onSubmit={onSubmit}>
        <FormItem>
          {DatosPersonalesValidator({form})('nombre')
            (<Input type={'text'} placeholder='Ingrese el nombre del evento' maxLength={100}/>)
          }
        </FormItem>
        <FormItem label={'Permitir a otros usuarios ver esta lista'}>
          {form.getFieldDecorator('privacidad')(<Checkbox />)}
        </FormItem>
        <FormItem>
          <div className={'flex flex-space-between'}>
            <Button onClick={onCancel} className={'white-button'}>
              {'Cancelar'}
            </Button>
            <Button htmlType="submit" className={'green-button'}>
              {'Confirmar'}
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
  mapPropsToFields (props) {
    return {
      nombre: {...props.nombre},
      privacidad: {...props.privacidad},
    }
  }
})(ExtendedForm(FormAltaLista));
