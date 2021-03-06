import React, {Component} from 'react';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';

import {Avatar} from 'antd';

import Imagen from '../assets/add-song.png';
import {mostrarListas} from '../actions/listasReproduccionActions';

/*
    Este componente recibe el id de la cancion.
    Deberia abrir un modal con las listas de reproduccion existentes
    y el usuario seleccionar una de ellas.
*/
class AgregarCancionLista extends Component {

  clickHandler = () => {
      let {cancion} = this.props;
      this.props.mostrarListas(cancion);
  }

  render () {
    const {style} = this.props;
    return (
        <img src={Imagen} style={style} className='addSong' onClick={this.clickHandler}></img>
    );
  }
}

const mapDispatchToProps = (dispatch) => ({
    mostrarListas: bindActionCreators(mostrarListas, dispatch)
});

export default connect(
    null,
    mapDispatchToProps
)(AgregarCancionLista)
