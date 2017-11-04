import React, {Component} from 'react';
import { Form, Button, Input, Select, DatePicker} from 'antd';
import es_ES from 'antd/lib/locale-provider/es_ES';

import {REQUIRED} from '../utils/validators';

import '../styles/LoginForm.css';
import FormWrapper from './FormWrapper';
import MailInput from './MailInput';
import PassInput from './PasswordInput';

const FormItem = Form.Item;
const Option = Select.Option;

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
        <Form >
          <FormItem>
            <InputText {...form} type={'text'} placeholder={'Ingrese su Nombre'} mapTo={'nombre'}/>
          </FormItem>
          <FormItem>
            <InputText {...form} type={'text'} placeholder={'Ingrese su Apellido'} mapTo={'apellido'}/>
          </FormItem>
          <FormItem>
            <DatePicker
              className={'full-width'}
              placeholder={'Fecha de nacimiento'}
              onChange={onFechaNacimientoChange}
            />
          </FormItem>
          <FormItem>
            <MailInput {...form} mapTo={'usuario'} />
          </FormItem>
          <FormItem>
            <PassInput {...form}/>
          </FormItem>
          <FormItem>
              <Select defaultValue={'oyente'} className={'full-width'} onChange={onTipoUsuarioChange}>
                <Option value={'oyente'}>Oyente</Option>
                <Option value={'artista'}>Artista</Option>
              </Select>
          </FormItem>
          <FormItem >
            <Button className={'green-button'}>
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
