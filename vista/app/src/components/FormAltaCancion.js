import React, {Component} from 'react';
import {Form, Button, Input, Icon} from 'antd';
import moment from 'moment';

import Upload from './UploadSingleFile';
import FechaNacimiento from './FechaNacimiento';
import {GenerosMusicalesDD} from './GenerosMusicales';
import {FechaValidator, RequiredValidator, validateFile, NombreContenidoValidator} from '../utils/validators';

const FormItem = Form.Item;

class FormAltaCancion extends Component {

  constructor (props) {
    super(props);

    this.state = {
      audio: props.audio || '',
      errorAudio: ''
    }
  }

  handleSubmit = (e, values) => {
    const {cancionesSeleccionadas, audio, errorAudio} = this.state;
    const {onSubmit, form, onFormValidationFail} = this.props;
    const {validateFields} = form;
    e.preventDefault();
    
    validateFields((errors, values) => {
      if (!errors && audio && !errorAudio) {
        const valuesToSend = {...values, canciones: cancionesSeleccionadas, audio};
        onSubmit(e, valuesToSend);
      }
      if (!audio) {
        this.setState({errorAudio: 'Cargue un archivo por favor'});
      }
    });
  }


  render () {
    const {onCancel, form, canciones} = this.props;
    return (
      <Form onSubmit={this.handleSubmit}>
        <FormItem>
          {NombreContenidoValidator(canciones)
            ('Ya existe una cancion con ese nombre')
            ({form})
            ('nombre')
            (<Input type={'text'} placeholder='Ingrese el nombre de la cancion' maxLength={'100'}/>)
          }
        </FormItem>
        <FormItem>
          {RequiredValidator({form})('genero')(<GenerosMusicalesDD />)}
        </FormItem>
        <FormItem
          validateStatus={this.state.errorAudio ? 'error' : ''}
          help={this.state.errorAudio}
        >
          <Upload 
            accept="audio/*"
            name={'file'}
            beforeUpload={(file, fileList) => {
              if(file && !file.type.includes('audio')) {
                this.setState({errorAudio: 'El archivo no es un audio'});
              } else {
                this.setState({errorAudio: ''});
              }
            }}
            action={'http://localhost:8080/archivo/subirCancion'}
            preloadedFile={this.state.audio}
            onRemove={() => {
              this.setState({audio: ''});
            }}
            onChange={(info) => {
              const fileList = info.fileList;
              if (fileList.length && fileList[0].response) {
                this.setState({audio: fileList[0].response});
              }
            }}
          >
            <Button>
              <Icon type="upload" /> 
              {'Subir Canción'}
            </Button>
          </Upload>
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
      genero: Form.createFormField({...props.genero})
    }
  }
})(FormAltaCancion);

