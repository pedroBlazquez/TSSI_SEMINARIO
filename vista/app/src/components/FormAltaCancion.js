import React, {Component} from 'react';
import {Form, Button, Input, Upload, Icon} from 'antd';

import ExtendedForm from './ExtendedForm';
import FechaNacimiento from './FechaNacimiento';
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
          {FechaValidator({form})('fechaPublicacion')
            (<FechaNacimiento className={'full-width'} placeholder='Fecha de Publicacion'/>)
          }
        </FormItem>
        <FormItem>
          {form.getFieldDecorator('archivo')
            (<Upload accept="audio">
              <Button>
                <Icon type="upload" /> Click to Upload
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

export default Form.create({})(ExtendedForm(FormAltaCancion));

