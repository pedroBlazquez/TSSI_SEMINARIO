import React, {Component} from 'react';

import {Card, Avatar} from 'antd';
import {Link} from 'react-router-dom';

import Compartir from './Compartir';
import Like from './Like';
import BotonPlay from './BotonPlay';

import Imagen from '../assets/playlist.png';
import '../styles/profile.css';

class ListaReproduccion extends Component {

  render () {
    const {lista}= this.props;
    return (
      <Card className={'margin-10p playlist'}>
        <div className={'flex flex-space-between'}>
            <h2>{lista.nombre}</h2>
            <img src={Imagen} style={{width: 20, height: 20}}/>
        </div>
        <div className='listaCanciones'>
            {   
                !!lista.canciones &&
                lista.canciones.map((cancion, i) => {
                return (
                    <div key={i} className='flex flex-space-between discoCancion'>
                        <span style={{height: '100%', verticalAlign: 'middle'}}>{cancion.nombre} - {cancion.artista.nombreFantasia}</span>
                        <BotonPlay cancion={{...cancion, artista: cancion.artista}}/>
                    </div>
                );
                })
            }
        </div>
      </Card>
    );
  }
}

export default ListaReproduccion;
