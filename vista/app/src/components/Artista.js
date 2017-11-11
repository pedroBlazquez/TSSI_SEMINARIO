import React, {Component} from 'react';

import {Card, Avatar} from 'antd';

import Compartir from './Compartir';
import Like from './Like';

class Artista extends Component {

  getGeneros = function(generos) {
      let generosArray = [];
      generos.map(function(genero) {
          generosArray.push(genero.descripcion);
      });

      return generosArray;
  }

  render () {
    const {artista}= this.props,
          generos = this.getGeneros(this.props.artista.generos);
    return (
      <Card className={'margin-10p'}>
          <div className='avatarArtistaContainer'>
              <Avatar className='avatarIcon' icon={'user'}/>
          </div>
          <div className='artistaInfoContainer'>
              <div className='topBlock flex flex-space-between'>
                  <h1 className='artistaTitulo'><strong>{artista.nombreFantasia}</strong></h1>
                  <Compartir id={artista.id} tipoContenido='artista'/>
              </div>
              <div className='flex flex-space-between'>
                  <div className='columnLeft'>
                      <div><strong>Inicio:</strong> {artista.fechaInicio}</div>
                      <div><strong>Generos:</strong> {generos.join(', ')}</div>
                  </div>
                  <div className='columnRight'>
                      <Like id={artista.id} tipoContenido='artista' likes={artista.seguidores} isLiked={artista.seguido}/>
                  </div>
              </div>
          </div>
          
      </Card>
    );
  }
}

export default Artista;