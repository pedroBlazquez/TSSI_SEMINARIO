import React, {Component} from 'react';
import {Form, Button, Input, Icon, InputNumber} from 'antd';

import ExtendedForm from './ExtendedForm';
import FechaEvento from './FechaEvento';
import Upload from './UploadSingleFile';
import {
  FechaValidator,
  RequiredValidator,
  validateFile,
  NombreContenidoValidator
} from '../utils/validators';

const TextArea = Input.TextArea;
const FormItem = Form.Item;

class FormAltaEvento extends Component {
  constructor (props) {
    super(props);
    this.state = {
      imagen: props.imagen || ''
    };
  }

  render () {
    const {onSubmit, onCancel, form, eventos} = this.props;
    return (
      <Form onSubmit={onSubmit}>
        <FormItem>
          {NombreContenidoValidator(eventos)
            ('Ya existe un evento con ese nombre')
            ({form})
            ('nombre')
            (<Input type={'text'} placeholder='Ingrese el nombre del evento' maxLength={'100'}/>)
          }
        </FormItem>
        <FormItem>
          {RequiredValidator({form})('direccion')
            (<Input type={'text'} placeholder='Ingrese la direccion del evento' maxLength={'100'}/>)
          }
        </FormItem>
        <FormItem>
            {
              RequiredValidator({form})('descripcion')
              (<TextArea placeholder={'Ingrese una breve descripcion'} rows={4} maxLength={'250'}/>)
            }
          </FormItem>
        <FormItem>
          {
            FechaValidator({form})('fecha')
            (<FechaEvento className="full-width" placeholder={'Fecha'}/>)
          }
        </FormItem>
        <FormItem label={'Costo (Opcional): '}>
          {form.getFieldDecorator('costo', {initialValue: 0})
            (<InputNumber
              min={0}
              formatter={value => `$ ${value}`.replace(/\B(?=(\d{3})+(?!\d))/g, ',')}
              parser={value => value.replace(/\$\s?|(,*)/g, '')}
            />)
          }
        </FormItem>
        <FormItem>
          {form.getFieldDecorator('imagen',{rules: [{validator: validateFile(this.state.imagen)}]})
            (<Upload 
              accept="image"
              name={'file'}
              action={'http://localhost:8080/archivo/subirEventoFoto'}
              onRemove={() => {
                this.setState({imagen: ''});
              }}
              onChange={(info) => {
                const fileList = info.fileList;
                if (fileList.length) {
                  this.setState({imagen: fileList[0].response});
                }
              }}
            >
              <Button>
                <Icon type="upload" /> 
                {'Subir Imagen'}
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
      fecha: {...props.fecha},
      costo: {...props.costo}
    }
  }
})(ExtendedForm(FormAltaEvento));
