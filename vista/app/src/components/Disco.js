import React, {Component} from 'react';
import {connect} from 'react-redux';

import {Card, Avatar, Button} from 'antd';
import {Link} from 'react-router-dom';

import {formatFecha} from '../utils/utils';

import Compartir from './Compartir';
import Like from './Like';
import BotonPlay from './BotonPlay';

import NoImagen from '../assets/default-release.png';
import { setCola } from '../actions/reproductorActions';
import ListaCanciones from './ListaCanciones';
import MediaButtons from './MediaButtons';
import { OBJECT_TYPES } from '../utils/constants';

class Disco extends Component {
  
  reproducirDisco = () => {
    const {disco, reproducir} = this.props;
    reproducir(disco.canciones.map(c => ({...c, artista: disco.artista})));
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
                        <h5>{formatFecha(disco.fechaPublicacion)}</h5>
                    </div>
                </div>
                <div>
                    <div className='columnLeft'>
                        <Button onClick={this.reproducirDisco}>Reproducir disco</Button>
                    </div>
                    <div className='columnRight'>
                        <MediaButtons content={disco} typeContent={OBJECT_TYPES.DISCO} like share />
                    </div>
                </div>
            </div>
          </div>
          <ListaCanciones canciones={disco.canciones.map(c => ({...c, artista: disco.artista}))}/>
      </Card>
    );
  }
}


export default connect(null, {
    reproducir: setCola
})(Disco);
