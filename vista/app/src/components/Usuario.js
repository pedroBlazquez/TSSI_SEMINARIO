import React, {Component} from 'react';

import {Card, Avatar} from 'antd';

import Compartir from './Compartir';
import Like from './Like';

class Usuario extends Component {

  getGeneros = function(generos) {
      return generos.map(genero => genero.descripcion);
  }

  render () {
    const {usuario}= this.props;

    return (
      <Card className={'margin-10p'}>
          <div className='avatarArtistaContainer'>
              <Avatar className='avatarIcon' icon={'user'}/>
          </div>
          <div className='artistaInfoContainer'>
              <div className='topBlock flex flex-space-between'>
                  <h1 className='artistaTitulo'><strong>{usuario.nombre + ' ' + usuario.apellido}</strong></h1>
                  <Compartir id={usuario.id} typeContent='Artista'/>
              </div>
          </div>
          
      </Card>
    );
  }
}

export default Usuario;
