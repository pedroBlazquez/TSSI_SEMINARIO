import React, {Component} from 'react';

import {Card, Avatar} from 'antd';
import {Link} from 'react-router-dom';

import Compartir from './Compartir';
import Like from './Like';
import BotonPlay from './BotonPlay';
import AgregarCancionLista from './AgregarCancionLista';

import NoImagen from '../assets/default-cover.png';
import MediaButtons from './MediaButtons';
import { OBJECT_TYPES } from '../utils/constants';

class Cancion extends Component {
  render () {
    const {cancion}= this.props;
    const imgUrl = cancion.discos.length && this.props.cancion.discos[0].portada || NoImagen;
    return (
      <Card className={'margin-10p'}>
          <div className='cancionImagenContainer'>
              <img src={imgUrl} className=''/>
          </div>
          <div className='cancionInfoContainer'>
            <div className='flex flex-space-between'>
              <div className='topBlock'>
                <div className='cancionTitleBlock'>
                  <Link to={`/perfil/${cancion.artista.usuario.id}`} style={{color: 'black'}}>
                    <h3>{cancion.artista.nombreFantasia}</h3>
                  </Link>
                    <h2>{cancion.nombre}</h2>
                </div>
              </div>
            </div>
            <MediaButtons 
              content={cancion}
              typeContent={OBJECT_TYPES.CANCION}
              share
              like
              play
              agregar
            />
          </div>          
      </Card>
    );
  }
}

export default Cancion;
