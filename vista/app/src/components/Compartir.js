import React, {Component} from 'react';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import withProfile from '../hoc/withProfile';
import {withRouter} from 'react-router-dom';

import {Avatar} from 'antd';

import {sendCompartir, deleteCompartirFromTree} from '../actions/compartirAction';

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
    let {id, typeContent, esPerfilPropio} = this.props;
    this.setState({
      wasShared: !this.state.wasShared
    });
    this.props.sendCompartir(id, typeContent);
    if (this.state.wasShared && window.location.pathname.match("perfil") && esPerfilPropio) {
      this.props.deleteCompartirFromTree(id, typeContent);
    }
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
  sendCompartir: bindActionCreators(sendCompartir, dispatch),
  deleteCompartirFromTree: bindActionCreators(deleteCompartirFromTree, dispatch)
});

export default withRouter(withProfile(connect(
  null,
  mapDispatchToProps
)(Compartir)));
/*export default connect(
  null,
  mapDispatchToProps
)(Compartir);*/
