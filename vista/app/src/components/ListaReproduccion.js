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
              <Button onClick={this.reproducirLista}>{'Reproducir Lista'}</Button>
        </div>
        <Divider />
        <div className='listaCanciones'>
            {   
                !!lista.canciones &&
                lista.canciones.map((cancion, i) => {
                return (
                    <div key={i} className='flex flex-space-between discoCancion'>
                        <span style={{height: '100%', verticalAlign: 'middle'}}>{cancion.nombre} - {cancion.artista.nombreFantasia}</span>
                        <BotonPlay size={'small'} cancion={{...cancion, artista: cancion.artista}}/>
                    </div>
                );
                })
            }
        </div>
      </Card>
    );
  }
}

export default connect(null, {
  reproducir: setCola
})(ListaReproduccion);
