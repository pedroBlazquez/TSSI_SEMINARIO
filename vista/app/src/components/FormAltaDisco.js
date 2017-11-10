import React, {Component} from 'react';
import {Form, Button, Input, Upload, Icon} from 'antd';
import {isEqual} from 'lodash';

import ContenidoConBusqueda from './ContenidoConBusqueda';
import {GenerosMusicalesDD} from './GenerosMusicales';
import {DatosPersonalesValidator, FechaValidator, RequiredValidator} from '../utils/validators';

const FormItem = Form.Item;

class FormAltaDisco extends Component {
  constructor (props) {
    super(props);

    this.state = {
      cancionesSeleccionadas: this.props.cancionesSeleccionadas || [],
      error: false
    };
  }

  handleCancionSelection = (id) => {
    const {cancionesSeleccionadas} = this.state;
    const {canciones} = this.props;
    const seleccion = canciones.find(c => c.nombre === id);
    let newCanciones = cancionesSeleccionadas.concat(seleccion);

    this.setState({cancionesSeleccionadas: newCanciones, error: false});
  }

  removerCancion = (id) => {
    const {cancionesSeleccionadas} = this.state;
    let newCanciones = cancionesSeleccionadas.filter(c => c.id !== id);
    this.setState({cancionesSeleccionadas: newCanciones});
  }

  filtrarCancionesYaIncluidas = () => {
    const {canciones} = this.props;
    const {cancionesSeleccionadas} = this.state;

    return canciones.filter(c => cancionesSeleccionadas.findIndex(s => isEqual(s, c)) === -1)
      .map(c => ({id: c.id, descripcion: c.nombre}));
  }

  handleSubmit = (e, values) => {
    const {cancionesSeleccionadas} = this.state;
    const {onSubmit, form} = this.props;
    const {validateFields} = form;
    e.preventDefault();
    
    validateFields((errors, values) => {
      if (!errors && cancionesSeleccionadas.length) {
        const valuesToSend = {...values, canciones: cancionesSeleccionadas};
        onSubmit(e, valuesToSend);
      }
      if (!cancionesSeleccionadas.length) {
        // Seteamos el flag de error para las canciones
        this.setState({error: true});
      }
    });
  }

  render () {
    const {onCancel, form} = this.props;
    return (
      <Form onSubmit={this.handleSubmit}>
        <FormItem>
          {DatosPersonalesValidator({form})('nombre')
            (<Input type={'text'} placeholder='Ingrese el nombre del disco'/>)
          }
        </FormItem>
        <FormItem>
          {RequiredValidator({form})('genero')
            (<GenerosMusicalesDD />)
          }
        </FormItem>
        <FormItem 
          label={'Canciones'}
          validateStatus={this.state.error ? 'error' : ''}
          help={this.state.error ? 'Seleccione al menos una cancion' : ''}  
        >
          <ContenidoConBusqueda 
            items={this.filtrarCancionesYaIncluidas()}
            seleccion={this.state.cancionesSeleccionadas.map(c => ({id: c.id, descripcion: c.nombre}))}
            onSelect={this.handleCancionSelection}
            onRemover={this.removerCancion}
          />
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
      nombre: {...props.nombre},
      genero: {...props.genero}
    }
  }
})(FormAltaDisco);

