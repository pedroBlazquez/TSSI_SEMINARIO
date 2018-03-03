import React, {Component} from 'react';
import {connect} from 'react-redux';

import {Card, Button, Divider} from 'antd';
import {Link} from 'react-router-dom';

import Compartir from './Compartir';
import Like from './Like';
import BotonPlay from './BotonPlay';

import {setCola} from '../actions/reproductorActions';

import Imagen from '../assets/playlist.png';
import '../styles/profile.css';
import ListaCanciones from './ListaCanciones';

class ListaReproduccion extends Component {
  reproducirLista = () => {
    const {lista, reproducir} = this.props;
    reproducir(lista.canciones);
  }

  render () {
    const {lista}= this.props;
    return (
      <Card className={'margin-10p playlist'}>
        <div className={'flex flex-space-between'}>
            <div className={'flex'}>
              <img src={Imagen} className={'margin-5p-right'} style={{width: 20, height: 20}}/>
              <h2>{lista.nombre}</h2>
            </div>
              {lista.canciones && lista.canciones.length && 
                <Button onClick={this.reproducirLista}>{'Reproducir Lista'}</Button>
              }
        </div>
        <Divider />
        <ListaCanciones 
          canciones={lista.canciones}
        />
      </Card>
    );
  }
}

export default connect(null, {
  reproducir: setCola
})(ListaReproduccion);
