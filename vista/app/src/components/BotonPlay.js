import React, {Component} from 'react';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';

import {Avatar} from 'antd';

import {reproducir, pausar} from '../actions/reproductorActions';
import {getCurrentSong, estaReproduciendo} from '../selectors/reproductor';

/*
    Este componente recibe el id de la cancion
*/
class BotonPlay extends Component {

    clickHandler = () => {
        const {cancion, reproducir, pausar, reproduciendo} = this.props;
        if (reproduciendo) {
            pausar(cancion);
        } else {
            reproducir(cancion)
        }
    }

    render () {
        const {reproduciendo, ...other} = this.props;
        return (
            <Avatar 
                {...other}
                className='reproduccionIcon'
                icon={reproduciendo ? 'pause-circle-o' : 'play-circle-o'}
                onClick={this.clickHandler}
            />
        );
    }
}

const mapStateToProps = (state, ownProps) => ({
    reproduciendo: getCurrentSong(state).id === ownProps.cancion.id && estaReproduciendo(state),
    ...ownProps
})

export default connect(
    mapStateToProps,
    {
        reproducir,
        pausar
    }
)(BotonPlay);
