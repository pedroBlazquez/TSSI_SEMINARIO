import React, {Component} from 'react';
import {connect} from 'react-redux';

import Novedades from '../components/Novedades';

import {getCancionesPerfil} from '../selectors/perfil';

export class CancionesPerfil extends Component {
  render () {
    const {canciones} = this.props;
    return (
      <div className={'main-content'}>
        {!!canciones && <Novedades records={canciones}/>}
      </div>
    );
  }
}

const mapStateToProps = (state) => ({
  canciones: getCancionesPerfil(state)
});

export default connect(
  mapStateToProps
)(CancionesPerfil)