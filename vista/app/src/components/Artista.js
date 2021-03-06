import React, {Component} from 'react';

import {Link} from 'react-router-dom';
import {Card, Avatar} from 'antd';

import {formatFecha} from '../utils/utils';

import Compartir from './Compartir';
import Like from './Like';
import AvatarUsuario from './AvatarUsuario';

class Artista extends Component {

  getGeneros = function(generos) {
      return generos.map(genero => genero.descripcion);
  }

  render () {
    const {artista}= this.props;
    const generos = this.getGeneros(artista.generos);
    return (
      <Card className={'margin-10p'}>
          <div className='avatarArtistaContainer'>
              <AvatarUsuario usuario={artista.usuario} />
          </div>
          <div className='artistaInfoContainer'>
              <div className='topBlock flex flex-space-between'>
                <h1 className='artistaTitulo'>
                    <Link to={`/perfil/${artista.usuario.id}`} style={{color: 'black'}}>
                        <strong>{artista.nombreFantasia}</strong>
                    </Link>
                </h1>
                  <Compartir id={artista.id} typeContent='Artista' shared={artista.compartido}/>
              </div>
              <div className='flex flex-space-between'>
                  <div className='columnLeft'>
                      <div><strong>Inicio:</strong> {formatFecha(artista.fechaInicio)}</div>
                      <div><strong>Generos:</strong> {generos.join(', ')}</div>
                  </div>
              </div>
          </div>
          
      </Card>
    );
  }
}

export default Artista;
