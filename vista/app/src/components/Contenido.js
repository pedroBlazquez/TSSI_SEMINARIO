import React, {Component} from 'react';
import cn from 'classnames';
import {Button, Icon} from 'antd';

import '../styles/Contenido.css';

const Item = ({
  descripcion,
  id,
  onEliminar, 
  onEditar,
  showOptions,
  selectable,
  selected,
  onSelect
}) => (
  <div 
    className={cn('item flex flex-space-between flex-align-baseline', {selectable, selected})} 
    onClick={() => {selectable && onSelect(id)}}
  >
    <span className={'descripcion'}>{descripcion}</span>
    {showOptions &&
      <div>
        <Icon type="edit" className={'icon-edit'} onClick={() => {onEditar(id)}}/>
        <Icon type="delete" className={'icon-delete'} onClick={() => {onEliminar(id)}}/>
      </div>
    }
  </div>
);

class Contenido extends Component {

  render () {
    const {agregarButtonText, onAgregar, agregar, items, ...itemProps} = this.props;
    return (
      <div className={'contenido-container'}>
        {agregar && <Button onClick={onAgregar} className={'margin-10p'}>{agregarButtonText}</Button>}
        <div className={'items-container'}>
          {items.map(i => 
            <Item
              key={i.id}
              id={i.id}
              descripcion={i.descripcion}
              {...itemProps}
            />
          )}
        </div>
      </div>
    );
  }
}

export default Contenido;