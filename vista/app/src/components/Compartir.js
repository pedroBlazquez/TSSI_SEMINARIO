import React, {Component} from 'react';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';

import {Avatar} from 'antd';

import {sendCompartir} from '../actions/compartirAction';

/*
    Este componente recibe id y tipo de contenido,
    hay que ver como pasar el id de usuario que imagino
    que se podra sacar del arbol de estados.
*/
class Compartir extends Component {

  constructor (props) {
    super(props);
    this.state = {
      wasShared: props.shared
    }
  }

  clickHandler = () => {
    let {id, typeContent} = this.props;
    this.setState({
      wasShared: !this.state.wasShared
    });
    this.props.sendCompartir(id, typeContent);
  }

  render () {
    let shared = 'compartirIcon compartido',
        notShared = 'compartirIcon';
    return (
        <Avatar className={this.state.wasShared ? shared : notShared} icon={'share-alt'} onClick={this.clickHandler}/>
    );
  }
}

const mapDispatchToProps = (dispatch) => ({
  sendCompartir: bindActionCreators(sendCompartir, dispatch)
});

export default connect(
  null,
  mapDispatchToProps
)(Compartir);
