import React, {Component} from 'react';
import withProfile from '../hoc/withProfile';
import {withRouter} from 'react-router-dom';
import {connect} from 'react-redux';
import {formatFecha} from '../utils/utils';

import { Card } from 'antd';

class InfoArtista extends Component {
  
  render() {
    const {artista} = this.props;
    return (
      <div>
        <Card title="Información general" className={'margin-10p'}>
          Fecha de inicio: {formatFecha(artista[0].fechaInicio)}
          <br/>
          Generos Musicales:
          <ul>
            {artista[0].generos.map((g, index) => <li key={index}>  {g.descripcion}  </li>)}
          </ul>
        </Card>
        <Card title="Historia" className={'margin-10p'}>
          {artista[0].descripcion}
        </Card>
        <Card title="Integrantes" className={'margin-10p'}>
          <ul>
            {artista[0].integrantes.map((i, index) => <li key={index}>{i.nombre} {i.apellido} - {i.rol.descripcion}</li>)}
          </ul>
        </Card>
      </div>
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
