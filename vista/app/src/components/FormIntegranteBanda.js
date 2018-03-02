import React, {Component} from 'react';
import {Form, Button, Input} from 'antd';

import ExtendedForm from './ExtendedForm';
import FechaNacimiento from './FechaNacimiento';
import RolesIntegrante from './RolesIntegrante';
import {DatosPersonalesValidator, FechaValidator, RequiredValidator} from '../utils/validators';
import {VOCALISTA} from '../utils/constants';

const FormItem = Form.Item;

class FormIntegranteBanda extends Component {

  render () {
    const {onSubmit, onCancel, form} = this.props;
    return (
      <Form onSubmit={onSubmit}>
        <FormItem label="Nombre">
          {DatosPersonalesValidator({form})('nombre')
            (<Input type={'text'} placeholder='Ingrese el nombre del integrante' maxLength={'100'}/>)
          }
        </FormItem>
        <FormItem label="Apellido">
          {DatosPersonalesValidator({form})('apellido')
            (<Input type={'text'} placeholder='Apellido' maxLength={'100'}/>)
          }
        </FormItem>
        <FormItem label="Rol dentro de la banda">
          {RequiredValidator({form})('rol')(<RolesIntegrante />)}
        </FormItem>
        <FormItem label="Fecha de nacimiento">
          {FechaValidator({form})('fechaNacimiento')
            (<FechaNacimiento className={'full-width'} placeholder='Ingrese la fecha de nacimiento del integrante'/>)
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
  mapPropsToFields(props) {
    return {
      rol: Form.createFormField({...props.rol}),
    };
  } 
})(ExtendedForm(FormIntegranteBanda));

