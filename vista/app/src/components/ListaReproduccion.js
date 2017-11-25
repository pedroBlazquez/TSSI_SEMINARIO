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
          <div className='titleBlock'>
            <div className='cancionImagenContainer'>
              <img src={Imagen}/>
            </div>
            <div className='discoInfoContainer'>
                <div className='flex flex-space-between'>
                    <div>
                        <h1>{lista.nombre}</h1>
                    </div>
                </div>
            </div>
          </div>
          <div className='listaCanciones'>
              {   
                  !!lista.canciones &&
                  lista.canciones.map((cancion, i) => {
                    return (
                        <div key={i} className='flex flex-space-between discoCancion'>
                            <span>{cancion.nombre}</span>
                            <BotonPlay cancion={{...cancion, artista: cancion.artista.nombreFantasia}}/>
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
