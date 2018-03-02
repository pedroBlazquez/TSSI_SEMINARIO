import React, {Component} from 'react';
import {Form, Button, Input, Icon} from 'antd';
import Upload from './UploadSingleFile';
import {isEqual} from 'lodash';

import ContenidoConBusqueda from './ContenidoConBusqueda';
import {GenerosMusicalesDD} from './GenerosMusicales';
import {FechaValidator, RequiredValidator, NombreContenidoValidator} from '../utils/validators';

const FormItem = Form.Item;

class FormAltaAlbum extends Component {
  constructor (props) {
    super(props);

    this.state = {
      discosSeleccionados: this.props.discosSeleccionados || [],
      portada: props.portada || '',
      error: false,
      errorPortada: false
    };
  }

  handleDiscoSelection = (id) => {
    const {discosSeleccionados} = this.state;
    const {discos} = this.props;
    const seleccion = discos.find(c => c.nombre === id);
    let newDiscos = discosSeleccionados.concat(seleccion);

    this.setState({discosSeleccionados: newDiscos, error: false});
  }

  removerDisco = (id) => {
    const {discosSeleccionados} = this.state;
    let newDiscos = discosSeleccionados.filter(c => c.id !== id);
    this.setState({discosSeleccionados: newDiscos});
  }

  filtrarDiscosYaIncluidos = () => {
    const {discos} = this.props;
    const {discosSeleccionados} = this.state;

    return discos.filter(d => discosSeleccionados.findIndex(s => d.id === s.id) === -1)
      .map(d => ({id: d.id, descripcion: d.nombre}));
  }

  handleSubmit = (e, values) => {
    const {discosSeleccionados, portada} = this.state;
    const {onSubmit, form} = this.props;
    const {validateFields} = form;
    e.preventDefault();
    
    validateFields((errors, values) => {
      if (!errors && discosSeleccionados.length && portada) {
        const valuesToSend = {
          ...values,
          discos: discosSeleccionados,
          portada
        };
        onSubmit(e, valuesToSend);
      }
      if (!portada) {
        this.setState({errorPortada: true});
      }
      if (!discosSeleccionados.length) {
        // Seteamos el flag de error para los discos
        this.setState({error: true});
      }
    });
  }

  render () {
    const {onCancel, form, albumes} = this.props;
    return (
      <Form onSubmit={this.handleSubmit}>
        <FormItem>
          {NombreContenidoValidator(albumes)
            ('Ya existe un album con este nombre')
            ({form})
            ('nombre')
            (<Input type={'text'} placeholder='Ingrese el nombre del Album' maxLength={'100'}/>)
          }
        </FormItem>
        <FormItem 
          label={'Discos'}
          validateStatus={this.state.error ? 'error' : ''}
          help={this.state.error ? 'Seleccione al menos un disco' : ''}  
        >
          <ContenidoConBusqueda 
            items={this.filtrarDiscosYaIncluidos()}
            seleccion={this.state.discosSeleccionados.map(c => ({id: c.id, descripcion: c.nombre}))}
            onSelect={this.handleDiscoSelection}
            onRemover={this.removerDisco}
          />
        </FormItem>
        <span>Le recomendamos que la imagen sea de 600px por 600px</span>
        <FormItem
          validateStatus={this.state.errorPortada ? 'error' : ''}
          help={this.state.errorPortada ? 'Cargue un archivo' : ''}
        >
          <Upload
            accept="image/*"
            multiple = {false}
            preloadedFile={this.state.portada}
            name={'file'}
            listType={'picture'}
            action={'http://localhost:8080/archivo/subirAlbumPortada'}
            onRemove={() => {
              this.setState({portada: ''});
            }}
            onChange={(data) => {
              if (data.file.status === 'done') {
                this.setState({portada: data.file.response})
              }
            }}
          >
            <Button>
              <Icon type="upload" /> 
              {'Subir imagen'}
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
      nombre: Form.createFormField({...props.nombre})
    }
  }
})(FormAltaAlbum);
