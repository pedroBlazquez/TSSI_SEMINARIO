import React, {Component} from 'react';
import { AutoComplete, Icon } from 'antd';

import Contenido from './Contenido';

class ContenidoConBusqueda extends Component {
  constructor (props) {
    super(props);
    this.state = {
      filtro: '',
      dataSource: []
    };
  }

  componentWillReceiveProps (nextProps) {
    if (nextProps.items !== this.props.items) {
      const dataSource = nextProps.items.map(i => i.descripcion);
      this.setState({dataSource})
    }
  }

  componentWillMount () {
    const {items} = this.props;
    const dataSource = items.map(i => i.descripcion);
    this.setState({dataSource})
  }
  
  onSearch = (value) => {
    this.setState({filtro: value});
  }

  onSelect = (value) => {
    this.props.onSelect(value);
  }

  onChange = (value) => {
    this.setState({filtro: value});
  }

  render () {
    const { seleccion, onRemover} = this.props
    return (
      <div>
        {seleccion.length ? 
          seleccion.map((s, i) => 
            <div key={`${s.id + i}`}>
              {s.descripcion}
              <span className={'margin-10p'}>
                <Icon type={'delete'} onClick={() => {onRemover(s.id)}}className={'icon-delete'} />
              </span>
            </div>
          ) : 
          'No hay items seleccionados'
        }
        <AutoComplete
          dataSource={this.state.dataSource}
          onSearch={this.onSearch}
          onSelect={this.onSelect}
          placeholder={'buscar'}
        />
      </div>
    );
  }
}

export default ContenidoConBusqueda;
