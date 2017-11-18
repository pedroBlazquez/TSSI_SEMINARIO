import React, {Component} from 'react';

import {Card, Avatar} from 'antd';

import Compartir from './Compartir';

const CardTitle = ({artista, fechaPublicacion, id}) => (
  <div className={'flex flex-space-between'}>
    <div>
      <Avatar className='avatarIcon' icon={'user'}/>
      <div className='cardHeaderInfo'>
        <h2 className='novedadTitulo'>{artista}</h2>
        <span className='fechaPublicacion'>{fechaPublicacion}</span>
      </div>
    </div>
    <Compartir id={id} typeContent='Evento'/>
  </div>
);

class Evento extends Component {
  render () {
    const {evento}= this.props;
    return (
      <Card
        className={'margin-10p'}
        title={<CardTitle artista={evento.artista.nombreFantasia} fechaPublicacion={evento.fechaPublicacion} id={evento.id}/>}
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
                <li><strong>Fecha:</strong> {evento.fechaEvento}</li>
                <li><strong>Costo:</strong> ${evento.costo}</li>
            </ul>
        </div>
      </Card>
    );
  }
}

export default Evento;