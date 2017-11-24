import React, {Component} from 'react';
import {withRouter} from 'react-router-dom';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';

import {Card} from 'antd';
import FormNuevaPublicacion from '../components/FormNuevaPublicacion';
import {nuevaPublicacion} from '../actions/publicacionActions';

class NuevaPublicacion extends Component {
  constructor (props) {
    super(props);
    this.state = {
      publicacion: {}
    };
  }

  handleSubmit = (e, values) => {
    const {publicar} = this.props;
    publicar(values.publicacion);
    this.setState({publicacion: {}});
  }
  
  onChange = (changedFields) => {
    this.setState({...changedFields});
  }

  render () {
    return (
      <Card className={'margin-10p'} title={'Publicá un mensaje!'}>
        <FormNuevaPublicacion publicacion={{...this.state.publicacion}} onSubmit={this.handleSubmit} />
      </Card> 
    );
  }
}


const mapDispatchToProps = (dispatch) => ({
  publicar: bindActionCreators(nuevaPublicacion, dispatch)
});

export default connect(
  null,
  mapDispatchToProps
)(NuevaPublicacion);
