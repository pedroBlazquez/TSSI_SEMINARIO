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
        <FormItem>
          {
            DatosPersonalesValidator({form})('nombre')
            (<Input type="text" placeholder="Ingrese su nombre" />)
          }
        </FormItem>
        <FormItem>
          {
            DatosPersonalesValidator({form})('apellido')
            (<Input placeholder={'Ingrese su apellido'} type="text"/>)
          }
        </FormItem>
        <FormItem>
          {
            FechaValidator({form})('fechaNacimiento')
            (<FechaNacimiento className="full-width" placeholder={'Fecha de nacimiento'}/>)
          }
        </FormItem>
        <FormItem>
          {MailValidator({form})('usuario')(<MailInput disabled={update}/>)}
        </FormItem>
        <FormItem>
          {PasswordValidator({form})('password')(<PasswordInput />)}  
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
      usuario: {...props.usuario},
      password: {...props.password},
      nombre: {...props.nombre},
      apellido: {...props.apellido},
      tipoUsuario: {...props.tipoUsuario},
      fechaNacimiento: {...props.fechaNacimiento}
    };
  } 
})(ExtendedForm(RegistroUsuarioForm));
