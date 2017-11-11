import React, {Component} from 'react';

import {Avatar} from 'antd';

import Imagen from '../assets/add-song.png';

/*
    Este componente recibe el id de la cancion.
    Deberia abrir un modal con las listas de reproduccion existentes
    y el usuario seleccionar una de ellas.
*/
class AgregarCancionLista extends Component {

  clickHandler = () => {
      alert(this.props.id);
  }

  render () {
    return (
        <img src={Imagen} className='addSong' onClick={this.clickHandler}></img>
    );
  }
}

export default AgregarCancionLista;