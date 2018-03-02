import React, {Component} from 'react';
import { Form, Button, Input, Select, DatePicker} from 'antd';
import moment from 'moment';
import es_ES from 'antd/lib/locale-provider/es_ES';

import {PasswordValidator, DatosPersonalesValidator, MailValidator, FechaValidator} from '../utils/validators';

import '../styles/LoginForm.css';
import ExtendedForm from './ExtendedForm';
import MailInput from './MailInput';
import PasswordInput from './PasswordInput';
import FechaNacimiento from './FechaNacimiento';
import TiposUsuario from './TiposUsuario';
import InfoTooltip from './InfoTooltip';
import InputWithIcon from './InputWithIcon';

const FormItem = Form.Item;

class RegistroUsuarioForm extends Component {

  render () {
    const {form, onSubmit, onCancel, onTipoUsuarioChange, update} = this.props;
    return (
      <Form onSubmit={onSubmit}>
        <FormItem label="Nombre">
          {
            DatosPersonalesValidator({form})('nombre')
            (<Input type="text" placeholder="Ingrese su nombre" maxLength={'100'}/>)
          }
        </FormItem>
        <FormItem label="Apellido">
          {
            DatosPersonalesValidator({form})('apellido')
            (<Input placeholder={'Ingrese su apellido'} type="text" maxLength={'100'}/>)
          }
        </FormItem>
        <FormItem label="Fecha de nacimiento">
          {
            FechaValidator({form})('fechaNacimiento')
            (<FechaNacimiento className="full-width" placeholder={'Fecha de nacimiento'}/>)
          }
        </FormItem>
        <FormItem label="Mail">
          {MailValidator({form})('usuario')(<MailInput disabled={update} maxLength={'100'}/>)}
        </FormItem>
        <FormItem label="Password">
          {PasswordValidator({form})('password')(<PasswordInput maxLength={'100'}/>)}  
        </FormItem>
        {!update &&
          <FormItem >
            <InputWithIcon
              input={form.getFieldDecorator('tipoUsuario')(<TiposUsuario/>)}
              icon={(<InfoTooltip title={'Estos son los tipos de usuarios que tenemos para vos!'}/>)}
            />
          </FormItem>
        }
        <FormItem >
          <div className={'flex flex-space-between'}>
            {
              !update &&
              <Button onClick={onCancel} className={'white-button'}>
                {'Cancelar'}
              </Button>
            }
            <Button htmlType="submit" className={'green-button'}>
              {update ? 'Confirmar' : 'Seguir'}
            </Button>
          </div>
        </FormItem>
      </Form>
    )
  }
}

export default Form.create({
  onFieldsChange(props, changedFields) {
    props.onChange(changedFields);
  },
  mapPropsToFields(props) {
    return {
      usuario: Form.createFormField({...props.usuario}),
      password: Form.createFormField({...props.password}),
      nombre: Form.createFormField({...props.nombre}),
      apellido: Form.createFormField({...props.apellido}),
      tipoUsuario: Form.createFormField({...props.tipoUsuario}),
      fechaNacimiento: Form.createFormField({...props.fechaNacimiento})
    };
  } 
})(ExtendedForm(RegistroUsuarioForm));
