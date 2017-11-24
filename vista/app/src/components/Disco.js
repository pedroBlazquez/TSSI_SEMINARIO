import React, {Component} from 'react';

import {Card, Avatar} from 'antd';

import Compartir from './Compartir';
import Like from './Like';
import BotonPlay from './BotonPlay';

import NoImagen from '../assets/default-release.png';

class Disco extends Component {

  constructor(props) {
      super(props);

      var url = this.props.disco.img ?
        this.props.disco.portada :
        NoImagen;
      this.state = {
          urlImagen: url
      }
  }

  render () {
    const {disco}= this.props;
    return (
      <Card className={'margin-10p'}>
          <div className='discoTopBlock'>
            <div className='cancionImagenContainer'>
              <img src={this.state.urlImagen} className=''/>
            </div>
            <div className='discoInfoContainer'>
                <div className='flex flex-space-between'>
                    <div className='topBlock'>
                        <h1>{disco.nombre}</h1>
                        <h3>{disco.artista.nombreFantasia}</h3>
                        <h5>{disco.fechaPublicacion}</h5>
                    </div>
                    <Compartir id={disco.id} typeContent='Disco'/>
                </div>
                <div>
                    <div className='columnRight'>
                        <Like id={disco.id} typeContent='Disco' likes={disco.likes} isLiked={disco.liked}/>
                    </div>
                </div>
            </div>
          </div>
          <div className='listaCanciones'>
              {   
                  !!disco.canciones &&
                  disco.canciones.map((cancion, i) => {
                    return (
                        <div key={i} className='flex flex-space-between discoCancion'>
                            <span>{cancion.nombre}</span>
                            <BotonPlay cancion={{...cancion, artista: disco.artista}}/>
                        </div>
                    );
                  })
              }
          </div>
      </Card>
    );
  }
}

export default Disco;
