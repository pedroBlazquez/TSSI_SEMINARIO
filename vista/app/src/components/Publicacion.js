import React, {Component} from 'react';
import moment from 'moment';
import {Link} from 'react-router-dom';

import {Card, Avatar} from 'antd';

const CardTitle = ({nombreUsuario, usuario, fechaPublicacion}) => (
  <div className={'flex flex-space-between'}>
    <div>
      <Avatar className='avatarIcon' icon={'user'}/>
      <div className='cardHeaderInfo'>
        <Link to={`/perfil/${usuario.id}`} style={{color: 'black'}}>
          <h2 className='novedadTitulo'>{nombreUsuario}</h2>
        </Link>
        <span className='fechaPublicacion'>{fechaPublicacion}</span>
      </div>
    </div>
  </div>
);

class Publicacion extends Component {
  render () {
    const {publicacion} = this.props;
    const usuario = publicacion.artista ? publicacion.artista.usuario : publicacion.usuario;
    const nombreUsuario = publicacion.artista ? publicacion.artista.nombreFantasia :
      usuario.nombre + ' ' + usuario.apellido;
    return (
      <Card
        className={'margin-10p'}
        title={
          <CardTitle
            nombreUsuario={nombreUsuario}
            usuario={usuario}
            fechaPublicacion={moment(publicacion.fechaPublicacion).format('YYYY-MM-DD')}
          />
        }
      >
      {publicacion.texto}
      </Card>
    );
  }
}

export default Publicacion;
