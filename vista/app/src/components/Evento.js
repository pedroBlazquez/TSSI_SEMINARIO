import React, {Component} from 'react';

import {formatFecha} from '../utils/utils';

import {Link} from 'react-router-dom';
import {Card, Avatar} from 'antd';

import Compartir from './Compartir';

const CardTitle = ({artista, fechaPublicacion, id, compartido}) => (
  <div className={'flex flex-space-between'}>
    <div>
      <Avatar className='avatarIcon' icon={'user'}/>
      <div className='cardHeaderInfo'>
        <Link to={`/perfil/${artista.usuario.id}`} style={{color: 'black'}}>
          <h2 className='novedadTitulo'>{artista.nombreFantasia}</h2>
        </Link>
        <span className='fechaPublicacion'>{formatFecha(fechaPublicacion)}</span>
      </div>
    </div>
    <Compartir id={id} typeContent='Evento' shared={compartido}/>
  </div>
);

class Evento extends Component {
  render () {
    const {evento}= this.props;
    return (
      <Card
        className={'margin-10p'}
        title={
          <CardTitle
            artista={evento.artista}
            fechaPublicacion={evento.fechaPublicacion}
            id={evento.id}
            compartido={evento.compartido}/>
          }
      >
        { !!evento.imagen &&
          <div className='eventoImagenMarco'>
            <img src={evento.imagen} className='eventoImagen'/>
          </div>
        }
        
        <div className='eventoDescripcion'>
            <ul className='columnLeft'>
                <li><strong>Evento:</strong> {evento.nombre}</li>
                <li><strong>Descripcion:</strong> {evento.descripcion}</li>
            </ul>
            <ul className='columnRight'>
                <li><strong>Fecha:</strong> {formatFecha(evento.fechaEvento)}</li>
                <li><strong>Costo:</strong> ${evento.costo}</li>
            </ul>
        </div>
      </Card>
    );
  }
}

export default Evento;
