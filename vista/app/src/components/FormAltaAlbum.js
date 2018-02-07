import React, {Component} from 'react';
import {Form, Button, Input, Upload, Icon} from 'antd';
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
      error: false
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

    return discos.filter(c => discosSeleccionados.findIndex(s => isEqual(s, c)) === -1)
      .map(c => ({id: c.id, descripcion: c.nombre}));
  }

  handleSubmit = (e, values) => {
    const {discosSeleccionados} = this.state;
    const {onSubmit, form} = this.props;
    const {validateFields} = form;
    e.preventDefault();
    
    validateFields((errors, values) => {
      if (!errors && discosSeleccionados.length) {
        const valuesToSend = {...values, discos: discosSeleccionados};
        onSubmit(e, valuesToSend);
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
            (<Input type={'text'} placeholder='Ingrese el nombre del Album' maxLength={100}/>)
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
      nombre: {...props.nombre}
    }
  }
})(FormAltaAlbum);
