import React, {Component} from 'react';

import {Card, Avatar} from 'antd';

import Compartir from './Compartir';
import Like from './Like';
import BotonPlay from './BotonPlay';
import AgregarCancionLista from './AgregarCancionLista';

import NoImagen from '../assets/default-cover.png';

class Cancion extends Component {
  constructor(props) {
      super(props);

      var url = this.props.cancion.disco && this.props.cancion.disco.portada ?
        this.props.cancion.disco.portada :
        NoImagen;
      this.state = {
          urlImagen: url
      }
  }
 
  render () {
    const {cancion}= this.props;
    return (
      <Card className={'margin-10p'}>
          <div className='cancionImagenContainer'>
              <img src={this.state.urlImagen} className=''/>
          </div>
          <div className='cancionInfoContainer'>
              <div className='flex flex-space-between'>
                  <div className='topBlock'>
                      <BotonPlay idCancion={cancion.id} />
                      <div className='cancionTitleBlock'>
                          <h3>{cancion.artista.nombreFantasia}</h3>
                          <h1>{cancion.nombre}</h1>
                      </div>
                      <AgregarCancionLista id={cancion.id}/>
                  </div>
                  <Compartir id={cancion.id} typeContent='Cancion'/>
              </div>
              <div>
                  <div className='columnRight'>
                      <Like id={cancion.id} typeContent='Cancion' likes={cancion.likes} isLiked={cancion.liked}/>
                  </div>
              </div>
          </div>          
      </Card>
    );
  }
}

export default Cancion;
