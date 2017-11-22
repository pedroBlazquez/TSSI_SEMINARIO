import React, {Component} from 'react';

import {Avatar} from 'antd';

/*
    Este componente recibe el id de la cancion
*/
class BotonPlay extends Component {

    constructor (props) {
        super(props);
        this.state = {
            reproduciendo: false
        }
    }

    //Envia a la api la request para obtener la cancion
    clickHandler = () => {
        this.setState({reproduciendo: !this.state.reproduciendo});
        
    }

    render () {
        return (
            <Avatar 
                className='reproduccionIcon'
                icon={this.state.reproduciendo ? 'pause-circle-o' : 'play-circle-o'}
                onClick={this.clickHandler}
            />
        );
    }
}

export default BotonPlay;