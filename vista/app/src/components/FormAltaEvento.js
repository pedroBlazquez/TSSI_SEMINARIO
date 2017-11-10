import React, {Component} from 'react';
import {Form, Button, Input, Upload, Icon, InputNumber} from 'antd';

import ExtendedForm from './ExtendedForm';
import FechaNacimiento from './FechaNacimiento';
import {DatosPersonalesValidator, FechaValidator, RequiredValidator} from '../utils/validators';

const TextArea = Input.TextArea;
const FormItem = Form.Item;

class FormAltaEvento extends Component {

  render () {
    const {onSubmit, onCancel, form} = this.props;
    return (
      <Form onSubmit={onSubmit}>
        <FormItem>
          {DatosPersonalesValidator({form})('nombre')
            (<Input type={'text'} placeholder='Ingrese el nombre del evento'/>)
          }
        </FormItem>
        <FormItem>
          {DatosPersonalesValidator({form})('direccion')
            (<Input type={'text'} placeholder='Ingrese la direccion del evento'/>)
          }
        </FormItem>
        <FormItem>
            {
              DatosPersonalesValidator({form})('descripcion')
              (<TextArea placeholder={'Ingrese una breve descripcion'} rows={4}/>)
            }
          </FormItem>
        <FormItem>
          {
            FechaValidator({form})('fecha')
            (<FechaNacimiento className="full-width" placeholder={'Fecha'}/>)
          }
        </FormItem>
        <FormItem label={'Costo (Opcional): '}>
          {form.getFieldDecorator('costo')
            (<InputNumber
              initialValue={0}
              min={0}
              formatter={value => `$ ${value}`.replace(/\B(?=(\d{3})+(?!\d))/g, ',')}
              parser={value => value.replace(/\$\s?|(,*)/g, '')}
            />)
          }
        </FormItem>
        <FormItem>
          {form.getFieldDecorator('imagen')
            (<Upload accept="image">
              <Button>
                <Icon type="upload" /> 
                {'Subir imagen'}
              </Button>
            </Upload>)
          }
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
      descripcion: {...props.descripcion},
      direccion: {...props.descripcion},
      costo: {...props.costo}
    }
  }
})(ExtendedForm(FormAltaEvento));
