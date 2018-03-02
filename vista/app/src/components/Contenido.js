import React, {Component} from 'react';
import cn from 'classnames';
import {Button, Icon, List} from 'antd';

import '../styles/Contenido.css';

const ListItem = List.item;

class ContenidoBis extends Component {
  render () {
    const {
      agregarButtonText,
      onAgregar,
      agregar,
      items,
      onEditar,
      onEliminar,
      render,
      actions,
      reproducir,
      ...itemProps
    } = this.props;
    return (
      <div className={'contenido-container'}>
        {agregar && <Button onClick={onAgregar} className={'margin-10p'}>{agregarButtonText}</Button>}
        <List
          bordered
          itemLayout="vertical"
          dataSource={items}
          renderItem={item => (
            <List.Item 
              actions={[
                actions.reproducible && <Button onClick={(e) => {reproducir(item)}} size={'small'}>Reproducir</Button>,
                actions.editable && <Icon type="edit" className={'icon-edit'} onClick={(e) => {onEditar(item.id);}}/>,
                actions.editable && <Icon type="delete" className={'icon-delete'} onClick={(e) => {onEliminar(item.id);}}/>
              ].filter(a => typeof a !== 'boolean')}>
              <List.Item.Meta title={<span>{item.descripcion}</span>}/>
              <div>
                {render && render(item)}
              </div>
            </List.Item>
          )}
        />
      </div>
    );
  }
}

export default ContenidoBis;