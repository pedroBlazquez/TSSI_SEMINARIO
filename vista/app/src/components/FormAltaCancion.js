import React, {Component} from 'react';
import {Form, Button, Input, Upload, Icon} from 'antd';

import ExtendedForm from './ExtendedForm';
import FechaNacimiento from './FechaNacimiento';
import {GenerosMusicalesDD} from './GenerosMusicales';
import {DatosPersonalesValidator, FechaValidator, RequiredValidator} from '../utils/validators';

const FormItem = Form.Item;

class FormAltaCancion extends Component {

  render () {
    const {onSubmit, onCancel, form} = this.props;
    return (
      <Form onSubmit={onSubmit}>
        <FormItem>
          {DatosPersonalesValidator({form})('nombre')
            (<Input type={'text'} placeholder='Ingrese el nombre de la cancion'/>)
          }
        </FormItem>
        <FormItem>
          {RequiredValidator({form})('genero')
            (<GenerosMusicalesDD type={'text'} />)
          }
        </FormItem>
        <FormItem>
          {form.getFieldDecorator('audio')
            (<Upload accept="audio">
              <Button>
                <Icon type="upload" /> 
                {'Subir Canción'}
              </Button>
            </Upload>)
          }
        </FormItem>
        <FormItem>
          {form.getFieldDecorator('imagen')
            (<Upload accept="audio">
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
      nombre: {...props.nombre}
    }
  }
})(ExtendedForm(FormAltaCancion));

