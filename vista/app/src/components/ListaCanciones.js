import React, {Component} from 'react';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';

import Compartir from './Compartir';
import Like from './Like';
import BotonPlay from './BotonPlay';
import Agregar from './AgregarCancionLista';

import {List, Divider} from 'antd';
import { OBJECT_TYPES } from '../utils/constants';

const ListItem = List.Item;

class ListaCanciones extends Component {
    static defaultProps = {
      play: true,
      share: true,
      like: true,
      agregar: true
    }
    constructor (props) {
      super(props);
      this.state = {
        mostrarCanciones: false
      }
    }

    toggleMostrarCanciones = () => {
      this.setState({mostrarCanciones: !this.state.mostrarCanciones});
    }

    render () {
      const {canciones, play, share, like, agregar, title} = this.props;
      if (!this.props.canciones.length) return <p>{'No hay canciones para mostrar'}</p>;
      if (!this.state.mostrarCanciones) {
        return (<p onClick={this.toggleMostrarCanciones} >{'Mostrar canciones'}</p>); 
      }
      return(
        <div>
          <p onClick={this.toggleMostrarCanciones}>{'Ocultar canciones'}</p>
          <Divider />
          <List
            size={'small'}
            dataSource={this.props.canciones}
            renderItem={(cancion) => 
              <ListItem>
                <div className={'flex flex-space-between'} style={{width: '100%'}}>
                  <span style={{maxWidth: 230, overflow: 'hidden', textOverflow: 'ellipsis', whiteSpace: 'nowrap'}}>{cancion.nombre}</span>
                  <div className={'flex flex-space-around'} style={{width: '30%'}}>
                    {play && 
                      <BotonPlay
                        size={'small'}
                        cancion={{...cancion, artista: cancion.artista}}
                      />
                    }
                    {agregar &&
                      <Agregar 
                        cancion={cancion}
                        style={{width: 20, height: 20}}
                      />
                    }
                    {share && 
                      <Compartir
                        id={cancion.id}
                        shared={cancion.compartido}
                        typeContent={OBJECT_TYPES.CANCION}
                        size={'small'}
                      />
                    }
                    {like &&
                      <Like
                        id={cancion.id}
                        isLiked={cancion.liked}
                        typeContent={OBJECT_TYPES.CANCION}
                        showLikes={false}
                        size={'small'}
                      />
                    }
                  </div>
                </div>
              </ListItem>
            }
          />
        </div>
      );
    }
}

export default ListaCanciones;
