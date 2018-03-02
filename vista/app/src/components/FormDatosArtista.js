import React, {Component} from 'react';
import { Form, Button, Input, Select, DatePicker} from 'antd';
import es_ES from 'antd/lib/locale-provider/es_ES';

import { DatosPersonalesValidator, RequiredValidator, FechaValidator } from '../utils/validators';

import '../styles/LoginForm.css';
import ExtendedForm from './ExtendedForm';
import InfoTooltip from './InfoTooltip';
import InputWithIcon from './InputWithIcon';
import FechaNacimiento from './FechaNacimiento';
import GenerosMusicales from './GenerosMusicales';
import ListaIntegrantes from './ListaIntegrantes';
import ModalAltaIntegrante from './ModalAltaIntegrante';

const FormItem = Form.Item;
const TextArea = Input.TextArea;

class DatosArtistaForm extends Component {

  constructor (props) {
    super(props);
    this.state = {
      modalIntegrantes: false,
      errorIntegrantes: false
    };

    this.toggleModalIntegrantes = this.toggleModalIntegrantes.bind(this);
  }

  handleSubmit = (e, values) => {
    const {onSubmit, form, esBanda, integrantes} = this.props;
    const {validateFields} = form;
    e.preventDefault();
    
    validateFields((errors, values) => {
      const fieldIntegrantes = esBanda ? !!integrantes.length : true;
      if (!errors && fieldIntegrantes) {
        onSubmit(e, values);
      }
      if (!integrantes.length) {
        // Seteamos el flag de error para las canciones
        this.setState({error: true});
      }
    });
  }

  toggleModalIntegrantes () {
    this.setState({modalIntegrantes: !this.state.modalIntegrantes});
  }

  render () {
    const {
      form,
      esBanda,
      onCancel,
      integrantes,
      agregarIntegrante,
      removerIntegrante,
      update
    } = this.props;
    return (
      <div>
        <Form onSubmit={this.handleSubmit}>
          <FormItem 
            error={form.getFieldError('nombreFantasia')}
            validateStatus={form.getFieldError('nombreFantasia') ? 'error': ''}
            help={form.getFieldError('nombreFantasia') ? form.getFieldError('nombreFantasia')[0] : ''}
          >
            <InputWithIcon
              input={DatosPersonalesValidator({form})('nombreFantasia')(<Input type="text" placeholder="Ingrese nombre de fantasía" maxLength={'100'} />)}
              icon={(<InfoTooltip title={'Las personas podrán encontrarte a través de tu nombre de fantasía!'}/>)}
            />
          </FormItem>
          <FormItem>
            {
              FechaValidator({form})('fechaInicio')
              (<FechaNacimiento className="full-width"/>)
            }
          </FormItem>
          <FormItem label="Seleccione los generos">
            {RequiredValidator({form})('generos')(<GenerosMusicales />)}
          </FormItem>
          <FormItem label={"Descripción"}>
            {
              RequiredValidator({form})('descripcion')
              (<TextArea rows={4}/>)
            }
          </FormItem>
          {esBanda && 
            <FormItem
              validateStatus={this.state.error && !integrantes.length ? 'error' : ''}
              help={this.state.error && !integrantes.length ? 'Ingrese al menos un integrante' : ''} 
            >
              <Button className={'green-button margin-5p'} onClick={this.toggleModalIntegrantes}>
                {'Agregar nuevo integrante'}
              </Button>
              <div>
                <ListaIntegrantes
                  integrantes={integrantes}
                  onDelete={removerIntegrante}
                />
              </div>
            </FormItem>
          }
          <FormItem >
            <div className='flex flex-space-between'>
              {!update &&
                <Button onClick={onCancel} className={'white-button'}>
                  {'Atrás'}
                </Button>
              }
              <Button htmlType="submit" className={'green-button'}>
                {'Confirmar'}
              </Button>
            </div>
          </FormItem>
        </Form>
        {this.state.modalIntegrantes &&
          <ModalAltaIntegrante
            visible={this.state.modalIntegrantes}
            cerrarModal={this.toggleModalIntegrantes}
            agregarIntegrante={agregarIntegrante}
          />
        }
      </div>
    )
  }
}

export default Form.create({
  onFieldsChange(props, changedFields) {
    props.onChange(changedFields);
  },
  mapPropsToFields(props) {
    return {
      nombreFantasia: Form.createFormField({...props.nombreFantasia}),
      fechaInicio: Form.createFormField({...props.fechaInicio}),
      generos: Form.createFormField({...props.generos}),
      descripcion: Form.createFormField({...props.descripcion})
    };
  } 
})(DatosArtistaForm);
