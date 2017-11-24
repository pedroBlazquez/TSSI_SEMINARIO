import React, {Component} from 'react';
import {Card} from 'antd';

import Artista from './Artista';
import Usuario from './Usuario';

import {OBJECT_TYPES} from '../utils/constants';

import '../styles/novedades.css'

export default class ListaUsuarios extends Component {
  getElements = () => {
    const {records} = this.props;
    return records.map(function(record) {
      let element;

      if (!!record.artista) {
        let artista = record.artista[0];
        delete record.artista;

        artista.usuario = record;

        element = <Artista key={record.id} artista={artista} />;
      } else {
        element = <Usuario key={record.id} usuario={record} />;
      }

      return element;
    });
  }

  render () {
    const {records} = this.props;
    return (
      <div>
        { !!records && !!records.length ?
          this.getElements() :
          <Card className={'margin-10p'}>{'No hay elementos para mostrar'}</Card>
        }
      </div>
    );
  }
}
