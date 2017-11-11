import React, {Component} from 'react';

import {Avatar} from 'antd';

/*
    Este componente recibe id y tipo de contenido,
    hay que ver como pasar el id de usuario que imagino
    que se podra sacar del arbol de estados.
*/
class Compartir extends Component {

  clickHandler = () => {
    alert(this.props.id + this.props.tipoContenido);
  }

  render () {
    return (
        <Avatar className='compartirIcon' icon={'share-alt'} onClick={this.clickHandler}/>
    );
  }
}

export default Compartir;