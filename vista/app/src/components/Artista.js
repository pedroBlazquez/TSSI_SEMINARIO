import React, {Component} from 'react';

import {Link} from 'react-router-dom';
import {Card, Avatar} from 'antd';

import Compartir from './Compartir';
import Like from './Like';

class Artista extends Component {

  getGeneros = function(generos) {
      return generos.map(genero => genero.descripcion);
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
                <h1 className='artistaTitulo'>
                    <Link to={`/perfil/${artista.usuario.id}`}>
                        <strong>{artista.nombreFantasia}</strong>
                    </Link>
                </h1>
                  <Compartir id={artista.id} typeContent='Artista'/>
              </div>
              <div className='flex flex-space-between'>
                  <div className='columnLeft'>
                      <div><strong>Inicio:</strong> {artista.fechaInicio}</div>
                      <div><strong>Generos:</strong> {generos.join(', ')}</div>
                  </div>
                  <div className='columnRight'>
                      <Like id={artista.id} typeContent='Artista' likes={artista.seguidores} isLiked={artista.seguido}/>
                  </div>
              </div>
          </div>
          
      </Card>
    );
  }
}

export default Artista;
