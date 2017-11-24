import React, {Component} from 'react';
import moment from 'moment';

import {Card, Avatar} from 'antd';

const CardTitle = ({user, fechaPublicacion}) => (
  <div className={'flex flex-space-between'}>
    <div>
      <Avatar className='avatarIcon' icon={'user'}/>
      <div className='cardHeaderInfo'>
        <h2 className='novedadTitulo'>{user}</h2>
        <span className='fechaPublicacion'>{fechaPublicacion}</span>
      </div>
    </div>
  </div>
);

class Publicacion extends Component {
  render () {
    const {publicacion} = this.props,
          usuario = publicacion.artista ?
            publicacion.artista.nombreFantasia :
            publicacion.usuario.nombre + ' ' + publicacion.usuario.apellido;
    return (
      <Card
        className={'margin-10p'}
        title={<CardTitle user={usuario} fechaPublicacion={moment(publicacion.fechaPublicacion).format('YYYY-MM-DD')}/>}
      >
      {publicacion.texto}
      </Card>
    );
  }
}

export default Publicacion;
