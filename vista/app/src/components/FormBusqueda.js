import React, {Component} from 'react';
import {Form, Button, Input, Icon, Popover, DatePicker, Select} from 'antd';

import {GenerosMusicalesDD} from './GenerosMusicales';


const FormItem = Form.Item;
const Option = Select.Option;
const InputSearch = Input.Search;

const OpcionesRango = (props) => (
  <Select {...props}>
    <Option value="Desde" >Desde</Option>
    <Option value="Hasta">Hasta</Option>
  </Select>
);

const OpcionesBusqueda = ({
  toggleOpciones,
  onArtistaChange,
  onUbicacionChange,
  onGeneroChange,
  onDesdeHastaChange,
  onFechaChange,
  artista,
  ubicacion,
  genero,
  desdeHasta,
  fecha
}) => (
  <div className="relative padding-10p">
    <label htmlFor="musico">Musico:</label>
    <Input type={'text'} name="musico" value={artista} onChange={onArtistaChange} placeholder='Nombre artista...'/>
    <label htmlFor="generos">Genero:</label>
    <GenerosMusicalesDD name="generos" style={{width: '100%'}} onChange={onGeneroChange} value={genero} allowClear/>
    <div className={'margin-10p-top'}>{'Eventos'}</div>
    <hr/>
    <label htmlFor="direccion">Ubicacion:</label>
    <Input type={'text'} name="direccion" value={ubicacion} onChange={onUbicacionChange} placeholder='Ubicación...'/>
    <div className={'margin-5p-top'}>
      <label htmlFor="rango">Rango:</label>
      <OpcionesRango name="rango" style={{width: 120}} onChange={onDesdeHastaChange} value={desdeHasta} className={'margin-5p-left'}/>
      <DatePicker value={fecha} placeholder="Seleccione fecha" onChange={onFechaChange} className={'margin-5p-left'}/>
    </div>
  </div>
);

const parametrosIniciales = {
  search: '',
  artista: '',
  ubicacion: '',
  genero: '',
  desdeHasta: '',
  fecha: null
};

class FormBusqueda extends Component {

  constructor (props) {
    super(props);
    this.state = {
      showOptions: false,
      parametros: {...parametrosIniciales}
    }
  }

  componentWillUnmount () {
    this.onLimpiarBusqueda();
  }

  toggleOpciones = () => {
    this.onParametroChange(parametrosIniciales);
    this.setState({showOptions: !this.state.showOptions});
  }

  onParametroChange (value) {
    this.setState({parametros: {...this.state.parametros, ...value}});
  }

  onSearchChange = (e) => {
    this.onParametroChange({search: e.target.value});
  }

  onArtistaChange = (e) => {
    this.onParametroChange({artista: e.target.value});
  }

  onUbicacionChange = (e) => {
    this.onParametroChange({ubicacion: e.target.value});
  }

  onGeneroChange = (genero) => {
    this.onParametroChange({genero});
  }

  onDesdeHastaChange = (desdeHasta) => {
    this.onParametroChange({desdeHasta})
  }

  onFechaChange = (fecha) => {
    this.onParametroChange({fecha});
  }

  onSearch = (e) => {
    e.preventDefault();
    const {onSearch} = this.props;
    onSearch(this.state.parametros);
  }

  onLimpiarBusqueda = () => {
    const {limpiarBusqueda} = this.props;
    this.onParametroChange(parametrosIniciales);
    limpiarBusqueda();
  }

  render () {
    const {parametros, showOptions} = this.state;
    const {search, ...advancedOptions} = parametros;
    return (
      <Form onSubmit={this.onSearch} layout={'inline'}>
        <FormItem>
          <Input
            maxLength={'200'}
            type={'text'}
            value={search}
            onChange={this.onSearchChange}
            style={{width: 360}}
            placeholder='Buscar...'
          />
        </FormItem>
        <FormItem>
          <Button htmlType="submit">Buscar</Button>
        </FormItem>
        <FormItem>
          <Button className={'margin-5p'} onClick={this.onLimpiarBusqueda}>
            {'Limpiar búsqueda'}
          </Button>
          <span className={'margin-5p-left'} style={{cursor: 'pointer'}} onClick={this.toggleOpciones}>
            Avanzado <Icon type={showOptions ? 'caret-up' : 'caret-down'} style={{ fontSize: 13 }} />
          </span>
        </FormItem>
        <FormItem>
            {showOptions ? 
              <OpcionesBusqueda 
                toggleOpciones={this.toggleOpciones}
                onArtistaChange={this.onArtistaChange}
                onUbicacionChange={this.onUbicacionChange}
                onDesdeHastaChange={this.onDesdeHastaChange}
                onFechaChange={this.onFechaChange}
                onGeneroChange={this.onGeneroChange}
                {...advancedOptions}
              /> :
              null
            }
        </FormItem>
      </Form>
    );
  }
}

export default Form.create({})(FormBusqueda);
