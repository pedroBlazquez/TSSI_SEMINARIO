import React, {Component} from 'react';
import { Form, Button, Input, Select, DatePicker} from 'antd';
import es_ES from 'antd/lib/locale-provider/es_ES';

import {REQUIRED} from '../utils/validators';

import '../styles/LoginForm.css';
import FormWrapper from './FormWrapper';
import MailInput from './MailInput';
import PassInput from './PasswordInput';
import FechaNacimiento from './FechaNacimiento';
import TiposUsuario from './TiposUsuario';
import InfoTooltip from './InfoTooltip';
import InputWithIcon from './InputWithIcon';

const FormItem = Form.Item;

const InputText = ({getFieldDecorator, mapTo, ...inputProps}) => (
  getFieldDecorator(mapTo, {rules: [REQUIRED]})(
    <Input {...inputProps}/>
  )
);

class RegistroUsuarioForm extends Component {
  constructor (props) {
    super(props);
    
  }
  
  render () {
    const {form, onSubmit, error, onTipoUsuarioChange, onFechaNacimientoChange} = this.props;
    return (
      <FormWrapper
        error={error}
        title={'Complete sus datos'}
      >
        <Form onSubmit={onSubmit}>
          <FormItem>
            <InputText getFieldDecorator={form.getFieldDecorator} type={'text'} placeholder={'Ingrese su Nombre'} mapTo={'nombre'}/>
          </FormItem>
          <FormItem>
            <InputText getFieldDecorator={form.getFieldDecorator} type={'text'} placeholder={'Ingrese su Apellido'} mapTo={'apellido'}/>
          </FormItem>
          <FormItem required>
            <FechaNacimiento
              className={'full-width'}
              onChange={onFechaNacimientoChange}
            />
          </FormItem>
          <FormItem>
            <MailInput getFieldDecorator={form.getFieldDecorator} mapTo={'usuario'} />
          </FormItem>
          <FormItem>
            <PassInput getFieldDecorator={form.getFieldDecorator}/>
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
      </FormWrapper>
    )
  }
}

export default Form.create({
  onFieldsChange(props, changedFields) {
    props.onChange(changedFields);
  },
  mapPropsToFields(props) {
    return {
      usuario: {
        value: props.usuario.value
      },
      password: {
        value: props.password.value
      }
    };
  } 
})(RegistroUsuarioForm);
