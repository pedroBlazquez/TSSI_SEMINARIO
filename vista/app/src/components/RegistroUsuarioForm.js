import React, {Component} from 'react';
import { Form, Button, Input, Select, DatePicker} from 'antd';
import es_ES from 'antd/lib/locale-provider/es_ES';

import {PasswordValidator, DatosPersonalesValidator, MailValidator} from '../utils/validators';

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
    const {form, onSubmit, onTipoUsuarioChange} = this.props;
    return (
      <Form onSubmit={onSubmit}>
        <FormItem>
          {
            DatosPersonalesValidator({form})('nombre')
            (<Input type="text" palceholder="Ingrese su nombre" />)
          }
        </FormItem>
        <FormItem>
          {
            DatosPersonalesValidator({form})('apellido')
            (<Input type="text" palceholder="Ingrese su apellido" />)
          }
        </FormItem>
        <FormItem required>
          {
            DatosPersonalesValidator({form})('fechaNacimiento')
            (<FechaNacimiento className="full-width" />)
          }
        </FormItem>
        <FormItem>
          {MailValidator({form})('usuario')(<MailInput />)}
        </FormItem>
        <FormItem>
          {PasswordValidator({form})('password')(<PasswordInput />)}  
        </FormItem>
        <FormItem >
          <InputWithIcon
            input={(<TiposUsuario onChange={onTipoUsuarioChange}/>)}
            icon={(<InfoTooltip title={'Estos son los tipos de usuarios que tenemos para vos!'}/>)}
          />
        </FormItem>
        <FormItem >
          <Button htmlType="submit" className={'green-button'}>
            {'Seguir'}
          </Button>
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
      usuario: {...props.usuario},
      password: {...props.password},
      fechaNacimiento: {...props.fechaNacimiento}
    };
  } 
})(ExtendedForm(RegistroUsuarioForm));
