import React, {Component} from 'react';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';

import {Avatar} from 'antd';

import {reproducir} from '../actions/reproductorActions';
import {getCurrentSong} from '../selectors/reproductor';

/*
    Este componente recibe el id de la cancion
*/
class BotonPlay extends Component {

    clickHandler = () => {
        const {cancion, reproducir} = this.props;
        this.props.reproducir(cancion)
    }

    render () {
        const {reproduciendo} = this.props;
        return (
            <Avatar 
                className='reproduccionIcon'
                icon={reproduciendo ? 'pause-circle-o' : 'play-circle-o'}
                onClick={this.clickHandler}
            />
        );
    }
}

const mapStateToProps = (state, ownProps) => ({
    reproduciendo: getCurrentSong(state).id === ownProps.cancion.id,
    ...ownProps
})

const mapDispatchToProps = (dispatch) => ({
    reproducir: bindActionCreators(reproducir, dispatch)
});

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(BotonPlay);