import React, {Component} from 'react';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';

import {Avatar, Tooltip} from 'antd';

import {sendLike} from '../actions/likeAction';

/*
    Este componente recibe id y tipo de contenido,
    hay que ver como pasar el id de usuario que imagino
    que se podra sacar del arbol de estados.
*/
class Like extends Component {
    static defaultProps = {
        showLikes: true
    };

    constructor (props) {
        super(props);
        this.state = {
            liked: props.isLiked,
            likes: props.likes
        }
    }

    componentWillReceiveProps (nextProps) {
        const {liked, likes} = nextProps;
        this.setState({liked, likes});
    }

    //Envia a la api la accion de like o unlike
    clickHandler = () => {
        let {id, typeContent} = this.props;
        this.setState({liked: !this.state.liked});
        if (!this.state.liked) {
            this.state.likes++;
        } else {
            this.state.likes--;
        }
        this.props.sendLike(id, typeContent);
    }

    render () {
        const {showLikes} = this.props;
        return (
            <Tooltip title={this.state.likes + " Likes"}>
                <Avatar 
                    className='like'
                    icon={this.state.liked ? 'like' : 'like-o'} 
                    onClick={this.clickHandler}
                    size={this.props.size}
                />
            </Tooltip>
        );
    }
}

const mapDispatchToProps = (dispatch) => ({
  sendLike: bindActionCreators(sendLike, dispatch)
});

export default connect(
  null,
  mapDispatchToProps
)(Like);
