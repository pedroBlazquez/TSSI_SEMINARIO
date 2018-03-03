import React, {Component} from 'react';

import {Link} from 'react-router-dom';
import {Card, Avatar} from 'antd';

import Compartir from './Compartir';
import Like from './Like';
import AvatarUsuario from './AvatarUsuario';

class Usuario extends Component {

  getGeneros = function(generos) {
      return generos.map(genero => genero.descripcion);
  }

  render () {
    const {usuario}= this.props;

    return (
      <Card className={'margin-10p'}>
          <div className='avatarArtistaContainer'>
            <AvatarUsuario usuario={usuario}/>
          </div>
          <div className='artistaInfoContainer'>
              <div className='topBlock flex flex-space-between'>
                <Link to={`/perfil/${usuario.id}`} style={{color: 'black'}}>
                    <h1 className='artistaTitulo'>
                        <strong>{usuario.nombre + ' ' + usuario.apellido}</strong>
                    </h1>
                </Link>
              </div>
          </div>
          
      </Card>
    );
  }
}

export default Usuario;
