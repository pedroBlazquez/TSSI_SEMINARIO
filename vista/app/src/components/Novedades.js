import React, {Component} from 'react';

import {Card} from 'antd';
import Publicacion from './Publicacion';
import FormNuevaPublicacion from './FormNuevaPublicacion';

class Novedades extends Component {

  render () {
    const {conPublicacion} = this.props;
    return (
      <div className={'full-height'} style={{maxWidth: 600, width: '80%', margin: '0 auto', paddingTop: 10}}>
        { conPublicacion &&
          <Card className={'margin-10p'} title={'Publicá un mensaje!'}>
            <FormNuevaPublicacion />
          </Card>
        }
        <Publicacion user={{nombreFantasia: 'Los Ramones'}}>
          {'Un Contenido Random'}
        </Publicacion>
      </div>
    );
  }
}

export default Novedades;
