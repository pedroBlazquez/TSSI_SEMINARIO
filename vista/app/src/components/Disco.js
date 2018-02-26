import React, {Component} from 'react';
import {connect} from 'react-redux';

import {Card, Avatar, Button} from 'antd';
import {Link} from 'react-router-dom';

import Compartir from './Compartir';
import Like from './Like';
import BotonPlay from './BotonPlay';

import NoImagen from '../assets/default-release.png';
import { setCola } from '../actions/reproductorActions';
import ListaCanciones from './ListaCanciones';

class Disco extends Component {
  
  reproducirDisco = () => {
    const {disco, reproducir} = this.props;
    reproducir(disco.canciones);
  }

  render () {
    const {disco}= this.props;
    return (
      <Card className={'margin-10p'}>
          <div className='discoTopBlock'>
            <div className='cancionImagenContainer'>
              <img src={disco.portada || NoImagen} className=''/>
            </div>
            <div className='discoInfoContainer'>
                <div className='flex flex-space-between'>
                    <div className='topBlock'>
                        <h1>{disco.nombre}</h1>
                        <Link to={`/perfil/${disco.artista.usuario.id}`} style={{color: 'black'}}>
                            <h3>{disco.artista.nombreFantasia}</h3>
                        </Link>
                        <h5>{disco.fechaPublicacion}</h5>
                    </div>
                    <Compartir id={disco.id} typeContent='Disco' shared={disco.compartido}/>
                </div>
                <div>
                    <div className='columnLeft'>
                        <Button onClick={this.reproducirDisco}>Reproducir disco</Button>
                    </div>
                    <div className='columnRight'>
                        <Like id={disco.id} typeContent='Disco' likes={disco.likes} isLiked={disco.liked}/>
                    </div>
                </div>
            </div>
          </div>
          <ListaCanciones canciones={disco.canciones}/>
      </Card>
    );
  }
}


export default connect(null, {
    reproducir: setCola
})(Disco);
