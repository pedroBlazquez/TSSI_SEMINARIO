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
import { buildFileList } from '../utils/utils';

const TextArea = Input.TextArea;
const FormItem = Form.Item;

class FormAltaEvento extends Component {
  constructor (props) {
    super(props);
    this.state = {
      portada: props.imagen || '',
      errorPortada: false
    };
  }

  handleSubmit = (e, values) => {
    const { portada} = this.state;
    const {onSubmit, form, onFormValidationFail} = this.props;
    const {validateFields} = form;
    e.preventDefault();
    
    validateFields((errors, values) => {
      if (!errors) {
        const valuesToSend = {...values, imagen: portada};
        onSubmit(e, valuesToSend);
      }
      if (!portada) {
        this.setState({errorPortada: true});
      }
    });
  }


  render () {
    const {onCancel, form, eventos} = this.props;
    return (
      <Form onSubmit={this.handleSubmit}>
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
        <span>Le recomendamos que la imagen sea de 550px de ancho por 200px de alto</span>
        <FormItem
          validateStatus={this.state.errorPortada ? 'error' : ''}
          help={this.state.errorPortada ? 'Cargue un archivo' : ''}
        >
          {form.getFieldDecorator('imagen',{rules: [{validator: validateFile(this.state.portada)}]})
            (<Upload 
              accept="image/*"
              name={'file'}
              preloadedFile={this.state.portada}
              listType={'picture'}
              action={'http://localhost:8080/archivo/subirEventoFoto'}
              onRemove={() => {
                this.setState({portada: ''});
              }}
              onChange={(info) => {
                const fileList = info.fileList;
                if (fileList.length && fileList[0].response) {
                  this.setState({portada: fileList[0].response});
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
      nombre: Form.createFormField({...props.nombre}),
      descripcion: Form.createFormField({...props.descripcion}),
      direccion: Form.createFormField({...props.direccion}),
      fecha: Form.createFormField({...props.fecha}),
      costo: Form.createFormField({...props.costo})
    }
  }
})(FormAltaEvento);
