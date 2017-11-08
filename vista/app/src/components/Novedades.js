import React, {Component} from 'react';

import Contenido from './Contenido';

class Novedades extends Component {

  render () {
    return (
      <div className={'full-height'}>
        <Contenido user={{nombreFantasia: 'Los Ramones'}}>
          {'Un Contenido Random'}
        </Contenido>
      </div>
    );
  }
}

export default Novedades;
