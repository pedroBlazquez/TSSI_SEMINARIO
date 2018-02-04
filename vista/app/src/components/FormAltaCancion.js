import React, {Component} from 'react';
import {Form, Button, Input, Icon} from 'antd';
import moment from 'moment';

import Upload from './UploadSingleFile';
import ExtendedForm from './ExtendedForm';
import FechaNacimiento from './FechaNacimiento';
import {GenerosMusicalesDD} from './GenerosMusicales';
import {FechaValidator, RequiredValidator, validateFile, NombreContenidoValidator} from '../utils/validators';

const FormItem = Form.Item;

class FormAltaCancion extends Component {

  constructor (props) {
    super(props);

    this.state = {
      audio: props.audio || ''
    }
  }

  render () {
    const {onSubmit, onCancel, form, canciones} = this.props;
    return (
      <Form onSubmit={onSubmit}>
        <FormItem>
          {NombreContenidoValidator(canciones)
            ('Ya existe una cancion con ese nombre')
            ({form})
            ('nombre')
            (<Input type={'text'} placeholder='Ingrese el nombre de la cancion' maxLength={100}/>)
          }
        </FormItem>
        <FormItem>
          {RequiredValidator({form})('genero')
            (<GenerosMusicalesDD />)
          }
        </FormItem>
        <FormItem>
          {form.getFieldDecorator('audio', {rules: [{validator: validateFile(this.state.audio)}]})
            (<Upload 
              accept="audio"
              name={'file'}
              action={'http://localhost:8080/archivo/subirCancion'}
              onRemove={() => {
                this.setState({audio: ''});
              }}
              onChange={(info) => {
                const fileList = info.fileList;
                if (fileList.length) {
                  this.setState({audio: fileList[0].response});
                }
              }}
            >
              <Button>
                <Icon type="upload" /> 
                {'Subir Canción'}
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
      genero: {...props.genero}
    }
  }
})(ExtendedForm(FormAltaCancion));

