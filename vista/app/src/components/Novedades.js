import React, {Component} from 'react';
import {Card} from 'antd';

import Publicacion from './Publicacion';
import Evento from './Evento';
import Artista from './Artista';
import Cancion from './Cancion';
import Disco from './Disco';
import Album from './Album';
import Usuario from './Usuario';

import {OBJECT_TYPES} from '../utils/constants';

import '../styles/novedades.css'

export default class Novedades extends Component {
  getElements = () => {
    const {records} = this.props;
    return records.map(function(record) {
      let element;
      switch(record.object_type) {
        case OBJECT_TYPES.ARTISTA:
          element = <Artista key={record.id + 'Artista'} artista={record} />;
          break;
        case OBJECT_TYPES.PUBLICACION:
          element = <Publicacion key={record.id + 'Publicacion'} publicacion={record} />;
          break;
        case OBJECT_TYPES.EVENTO:
          element = <Evento key={record.id + 'Evento'} evento={record} />;
          break;
        case OBJECT_TYPES.DISCO:
          element = <Disco key={record.id + 'Disco'} disco={record} />;
          break;
        case OBJECT_TYPES.CANCION:
          element = <Cancion key={record.id + 'Cancion'} cancion={record} />;
          break;
        case OBJECT_TYPES.ALBUM:
          element = <Album key={record.id + 'Album'} album={record}/>;
          break;
        case OBJECT_TYPES.USUARIO:
          element = <Usuario key={record.id + 'Usuario'} usuario={record} />;
          break;
      }
      return element;
    });
  }

  render () {
    const {records, isHome, messageClickHandler} = this.props,
          globalMessage = <Card className={'margin-10p'}>{'No hay elementos para mostrar'}</Card>,
          homeMessage = <Card className={'margin-10p clickable'} onClick={messageClickHandler}>
            {'No hay elementos para mostrar, clickee para volver a ver las novedades'}
          </Card>;
    return (
      <div>
        { (!!records.length && !isHome) || records[0] !== 'noResultsFromSearch' && isHome ?
          this.getElements() :
          isHome ? homeMessage : globalMessage
        }
      </div>
    );
  }
}
