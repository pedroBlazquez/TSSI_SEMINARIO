import React, {Component} from 'react';
import {Form, Button, Input, Checkbox} from 'antd';

import FechaEvento from './FechaEvento';
import Upload from './UploadSingleFile';
import {
  DatosPersonalesValidator,
  FechaValidator,
  RequiredValidator,
  validateFile
} from '../utils/validators';
import Contenido from './Contenido';

const TextArea = Input.TextArea;
const FormItem = Form.Item;

class FormAltaLista extends Component {
  constructor (props) {
    super(props);

    this.state = {
      canciones: this.props.canciones || []
    };
  }

  handleSubmit = (e, values) => {
    const {canciones} = this.state;
    const {onSubmit, form} = this.props;
    const {validateFields} = form;
    e.preventDefault();
    
    validateFields((errors, values) => {
      if (!errors) {
        onSubmit(e, {...values, canciones});
      }
    });
  }

  render () {
    const {onCancel, form} = this.props;
    const {canciones} = this.state;
    return (
      <Form onSubmit={this.handleSubmit}>
        <FormItem>
          {RequiredValidator({form})('nombre')
            (<Input type={'text'} placeholder='Ingrese el nombre del evento' maxLength={'100'}/>)
          }
        </FormItem>
        <FormItem label={'Canciones'}>
          {canciones.length ? 
            <div>
              {canciones.map(c => <p key={c.id}>{c.nombre}</p>)}
            </div> :
            'Puede agregar canciones a esta lista desde home'
          }
        </FormItem>

        <FormItem>
          Solo yo puedo ver esta lista: {form.getFieldDecorator('privacidad', {valuePropName: 'checked', initialValue: true})(<Checkbox />)}
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
})(FormAltaLista);
