import React, {Component} from 'react';

import {Card, Avatar} from 'antd';

import Compartir from './Compartir';
import Like from './Like';
import BotonPlay from './BotonPlay';

import NoImagen from '../assets/default-release.png';

import {OBJECT_TYPES} from '../utils/constants';

class Album extends Component {

  constructor(props) {
      super(props);

      var url = this.props.album.img ?
        this.props.album.portada :
        NoImagen;
      this.state = {
          urlImagen: url
      }
  }

  render () {
    const {album}= this.props;
    return (
      <Card className={'margin-10p'}>
          <div className='discoTopBlock'>
            <div className='cancionImagenContainer'>
              <img src={this.state.urlImagen} />
            </div>
            <div className='discoInfoContainer'>
                <div className='flex flex-space-between'>
                    <div className='topBlock'>
                        <h1>{album.nombre}</h1>
                        <h3>{album.artista.nombreFantasia}</h3>
                        <h5>{album.fechaPublicacion}</h5>
                    </div>
                    <Compartir id={album.id} typeContent={OBJECT_TYPES.ALBUM}/>
                </div>
                <div>
                    <div className='columnRight'>
                        <Like id={album.id} typeContent={OBJECT_TYPES.ALBUM} likes={album.likes} isLiked={album.liked}/>
                    </div>
                </div>
            </div>
          </div>
          <div className='listaCanciones'>
              {   
                  !!album.discos &&
                  album.discos.map((disco, i) => {
                    return (
                        <div key={disco.id} className='flex flex-space-between discoCancion'>
                            <span>{disco.nombre}</span>
                        </div>
                    );
                  })
              }
          </div>
      </Card>
    );
  }
}

export default Album;