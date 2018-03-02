import React, {Component} from 'react';

import {Card, Avatar} from 'antd';
import {Link} from 'react-router-dom';

import Compartir from './Compartir';
import Like from './Like';
import BotonPlay from './BotonPlay';
import AgregarCancionLista from './AgregarCancionLista';

import NoImagen from '../assets/default-cover.png';

class Cancion extends Component {
  constructor(props) {
      super(props);

      var url = this.props.cancion.discos.length && this.props.cancion.discos[0].portada ?
        this.props.cancion.discos[0].portada :
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
                      <BotonPlay cancion={cancion} />
                      <div className='cancionTitleBlock'>
                        <Link to={`/perfil/${cancion.artista.usuario.id}`} style={{color: 'black'}}>
                          <h3>{cancion.artista.nombreFantasia}</h3>
                        </Link>
                          <h1>{cancion.nombre}</h1>
                      </div>
                      <AgregarCancionLista cancion={cancion}/>
                  </div>
                  <Compartir id={cancion.id} typeContent='Cancion' shared={cancion.compartido}/>
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
