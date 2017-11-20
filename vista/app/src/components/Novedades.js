import React, {Component} from 'react';

import {Card} from 'antd';
import Publicacion from './Publicacion';
import FormNuevaPublicacion from './FormNuevaPublicacion';
import Evento from './Evento';
import Artista from './Artista';
import Cancion from './Cancion';
import Disco from './Disco';
import Album from './Album';

import {OBJECT_TYPES} from '../utils/constants';

import '../styles/novedades.css'

export default class Novedades extends Component {
  getElements = () => {
    const {records} = this.props;
    return records.map(function(record) {
      let element;
      switch(record.object_type) {
        case OBJECT_TYPES.ARTISTA:
          element = <Artista key={record.id} artista={record} />;
          break;
        case OBJECT_TYPES.PUBLICACION:
          element = <Publicacion key={record.id} publicacion={record} />;
          break;
        case OBJECT_TYPES.EVENTO:
          element = <Evento key={record.id} evento={record} />;
          break;
        case OBJECT_TYPES.DISCO:
          element = <Disco key={record.id} disco={record} />;
          break;
        case OBJECT_TYPES.CANCION:
          element = <Cancion key={record.id} cancion={record} />;
          break;
        case OBJECT_TYPES.ALBUM:
          element = <Album key={record.id} album={record}/>;
          break;
      }
      return element;
    });
  }

  render () {
    const {conPublicacion, records, conBusqueda} = this.props;

    return (
      <div className={'main-content'}>
        { conBusqueda && <div>{'Aca va la busqueda'}</div>}
        { conPublicacion &&
          <Card className={'margin-10p'} title={'Publicá un mensaje!'}>
            <FormNuevaPublicacion />
          </Card>
        }
        { !!records.length ?
          this.getElements() :
          <Card className={'margin-10p'}>{'No hay elementos para mostrar'}</Card>
        }

      </div>
    );
  }
}
