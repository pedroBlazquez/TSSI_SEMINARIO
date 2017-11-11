import React, {Component} from 'react';

import {Card} from 'antd';
import Publicacion from './Publicacion';
import FormNuevaPublicacion from './FormNuevaPublicacion';
import Evento from './Evento';
import Artista from './Artista';
import Cancion from './Cancion';
import Disco from './Disco';

import {mockedServiceResponse} from '../mockedData';

import '../styles/novedades.css'

class Novedades extends Component {

  getElements = function() {
    return mockedServiceResponse.map(function(record) {
      let element;
      switch(record.object_type) {
        case 'Artista':
          element = <Artista artista={record}/>;
          break;
        case 'Publicacion':
          element = <Publicacion publicacion={record} />;
          break;
        case 'Evento':
          element = <Evento evento={record} />;
          break;
        case 'Disco':
          element = <Disco disco={record}/>;
          break;
        case 'Cancion':
          element = <Cancion cancion={record}/>;
          break;
      }
      return element;
    });
  }

  render () {
    const {conPublicacion} = this.props,
          elements = this.getElements();


    return (
      <div className={'full-height'} style={{maxWidth: 600, width: '80%', margin: '0 auto', paddingTop: 10}}>
        { conPublicacion &&
          <Card className={'margin-10p'} title={'Publicá un mensaje!'}>
            <FormNuevaPublicacion />
          </Card>
        }
        { elements }
      </div>
    );
  }
}

export default Novedades;
