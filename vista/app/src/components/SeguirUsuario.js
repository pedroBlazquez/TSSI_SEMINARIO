import React, {Component} from 'react';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import {Icon} from 'antd';

import {getSiguiendoPerfil} from '../selectors/perfil';
import {seguirUsuario} from '../actions/likeAction';

class Seguirusuario extends Component {

  constructor (props) {
    super(props);
    this.state = {
      siguiendo: props.siguiendo
    }
  }

  //Envia a la api la accion de like o unlike
  clickHandler = () => {
    let {id, typeContent} = this.props;
    this.setState({siguiendo: !this.state.siguiendo});
    this.props.seguir(id, typeContent);
  }

  render () {
      return (
        <Icon 
          type={this.state.siguiendo ? 'heart' : 'heart-o'}
          onClick={this.clickHandler}
          style={{fontSize: 18, color: 'black'}}
        />
      );
  }
}

const mapStateToProps = (state) => ({
  siguiendo: getSiguiendoPerfil(state)
});

const mapDispatchToProps = (dispatch) => ({
  seguir: bindActionCreators(seguirUsuario, dispatch)
});

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Seguirusuario);
