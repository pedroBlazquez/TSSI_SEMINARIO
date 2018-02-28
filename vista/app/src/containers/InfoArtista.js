import React, {Component} from 'react';
import withProfile from '../hoc/withProfile';
import {withRouter} from 'react-router-dom';
import {connect} from 'react-redux';

import { Card } from 'antd';

class InfoArtista extends Component {
  
  render() {
    return (
      <Card title="Información del artista" className={'margin-10p'}>
        {this.props.artista[0].descripcion}
      </Card>
    );
  }
}

const mapStateToProps = (state) => ({
    artista: state.perfilReducer.usuario.artista
});

export default withRouter(withProfile(connect(
    mapStateToProps
)(InfoArtista)
));
