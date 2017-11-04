import React, {Component} from 'react';
import { Form, Button, Input, Select, DatePicker} from 'antd';
import es_ES from 'antd/lib/locale-provider/es_ES';

import { DatosPersonalesValidator } from '../utils/validators';

import '../styles/LoginForm.css';
import ExtendedForm from './ExtendedForm';
import InfoTooltip from './InfoTooltip';
import InputWithIcon from './InputWithIcon';
import FechaNacimiento from './FechaNacimiento';
import GenerosMusicales from './GenerosMusicales';

const FormItem = Form.Item;
const TextArea = Input.TextArea;

class DatosArtistaForm extends Component {

  render () {
    const {form, onSubmit, esBanda, onCancel} = this.props;
    return (
      <Form onSubmit={onSubmit}>
        <FormItem 
          label="Nombre de fantasía"
          error={form.getFieldError('nombreFantasia')}
          validateStatus={form.getFieldError('nombreFantasia') ? 'error': ''}
        >
          <InputWithIcon
            input={DatosPersonalesValidator({form})('nombreFantasia')(<Input type="text" />)}
            icon={(<InfoTooltip title={'Las personas podrán encontrarte a través de tu nombre de fantasía!'}/>)}
          />
        </FormItem>
        <FormItem>
          {
            DatosPersonalesValidator({form})('fechaInicio')
            (<FechaNacimiento className="full-width"/>)
          }
        </FormItem>
        <FormItem label="Seleccione los generos">
          {form.getFieldDecorator('generos')(<GenerosMusicales />)}
        </FormItem>
        <FormItem label={"Descripción"}>
          {
            DatosPersonalesValidator({form})('descripcion')
            (<TextArea rows={4}/>)
          }
        </FormItem>
        {esBanda && 
          <FormItem>
            {'Aca van los datos de los integrantes'}
          </FormItem>
        }
        <FormItem >
          <div className='flex flex-space-between'>
            <Button onClick={onCancel} className={'white-button'}>
              {'Atrás'}
            </Button>
            <Button htmlType="submit" className={'green-button'}>
              {'Confirmar'}
            </Button>
          </div>
        </FormItem>
      </Form>
    )
  }
}

export default Form.create({
  onFieldsChange(props, changedFields) {
    props.onChange(changedFields);
  },
  mapPropsToFields(props) {
    return {
      nombreFantasia: {...props.nombreFantasia},
      fechaInicio: {...props.fechaInicio},
      generos: {...props.generos},
      descripcion: {...props.descripcion}
    };
  } 
})(ExtendedForm(DatosArtistaForm));
