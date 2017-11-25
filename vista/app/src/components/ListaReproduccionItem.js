import React, {Component} from 'react';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';


import {pushSongToList} from '../actions/listasReproduccionActions';
import Imagen from '../assets/playlist.png';

class ListaReproduccionItem extends Component {
    
    clickHandler = () => {
        let {idCancion, lista} = this.props;
        this.props.pushSongToList(idCancion.toString(), lista.id.toString());
    }

    render () {
        let {lista} = this.props;
        
        return (
            <div className='listaReproItem' onClick={this.clickHandler}>
                <img src={Imagen}></img>
                <h2>{lista.nombre}</h2>
            </div>
        );
    }
}

const mapDispatchToProps = (dispatch) => ({
    pushSongToList: bindActionCreators(pushSongToList, dispatch),
});

export default connect(
    null,
    mapDispatchToProps
)(ListaReproduccionItem);
