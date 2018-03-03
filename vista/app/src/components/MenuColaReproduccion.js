import React, {Component} from 'react';
import {Icon, Popover, List} from 'antd';
import {connect} from 'react-redux';

import {removerDeCola, reproducirDeCola} from '../actions/reproductorActions';

const ListItem = List.Item;

class MenuColaReproduccion extends Component {

  constructor (props) {
    super(props);

    this.state = {
      visible: false
    }
  }

  togglePopOver = () => {
    this.setState({visible: !this.state.visible});
  }

  removerCancion = (indice) => {
    const {removerDeCola} = this.props;
    return () => {
      removerDeCola(indice);
    }
  }

  reproducirDeCola = (indice) => {
    const {reproducirDeCola} = this.props;
    return () => {
      reproducirDeCola(indice);
    }
  }

  render () {
    const {visible} = this.state;
    const {canciones, playing} = this.props;
    return (
      <Popover
        visible={visible}
        onVisibleChange={this.togglePopOver}
        content={(
          !canciones.length ? 
            <p>{'No hay canciones en cola cola'}</p> :
            <List
              size={'small'}
              dataSource={canciones}
              renderItem={(cancion, index) => 
                <ListItem>
                  <div className={'flex'}>
                    <span style={{
                        width: 120,
                        overflow: 'hidden',
                        textOverflow: 'ellipsis',
                        whiteSpace: 'nowrap',
                        fontSize: 10,
                        marginRight: 10,
                        cursor: 'pointer'
                      }}
                      onClick={this.reproducirDeCola(index)}
                    >
                      {cancion.nombre}
                    </span>
                    <Icon 
                      type="close-circle-o"
                      className={'icon-base'} 
                      onClick={this.removerCancion(index)}
                    />
                  </div>
                </ListItem>
              }
            />
        )}
      >
        <Icon type="bars" className={'icon-base'} />
      </Popover>
    )
  }
}

export default connect(
  null,
  {
    removerDeCola,
    reproducirDeCola
  }
)(MenuColaReproduccion);
