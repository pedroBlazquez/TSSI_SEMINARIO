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
          renderItem={item => {
            const actionItems = [];
            if (actions.reproducible) {
              actionItems.push(<Button onClick={(e) => {reproducir(item)}} size={'small'}>Reproducir</Button>);
            }

            if (actions.editable) {
              actionItems.push(<Icon type="edit" className={'icon-edit'} onClick={(e) => {onEditar(item.id);}}/>);
              actionItems.push(<Icon type="delete" className={'icon-delete'} onClick={(e) => {onEliminar(item.id);}}/>);
            }
            return (
              <List.Item actions={actionItems}>
                <List.Item.Meta title={<span>{item.descripcion}</span>}/>
                <div>
                  {render && render(item)}
                </div>
              </List.Item>
            );
          }}
        />
      </div>
    );
  }
}

export default ContenidoBis;