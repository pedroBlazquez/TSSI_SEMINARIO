import React, {Component} from 'react';
import {connect} from 'react-redux';

import Novedades from '../components/Novedades';

import {getDiscosPerfil} from '../selectors/perfil';

export class DiscosPerfil extends Component {
  render () {
    const {discos} = this.props;
    return (
      <div className={'main-content'}>
        {!!discos && <Novedades records={discos}/>}
      </div>
    );
  }
}

const mapStateToProps = (state) => ({
  discos: getDiscosPerfil(state)
});

export default connect(
  mapStateToProps
)(DiscosPerfil)