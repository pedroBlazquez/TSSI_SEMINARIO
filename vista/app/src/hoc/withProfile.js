import React, {Component} from 'react';
import {connect} from 'react-redux';
import {USUARIO_OYENTE} from '../utils/constants';

import {getCurrentUser} from '../selectors/login';

function withProfile (Wrapp) {
  return class WithProfile extends Component {
    esArtista () {
      const {user} = this.props;
      return user.usuarioTipoId !== USUARIO_OYENTE.id;
    }

    esPerfilPropio () {
      const {user, match} = this.props;
      return user.id.toString() === match.params.profileId;
    }

    render () {
      const esArtista = this.esArtista();
      const esPerfilPropio = this.esPerfilPropio();
      return (
        <Wrapp {...this.props} esArtista={esArtista} esPerfilPropio={esPerfilPropio}/>
      )
    }
  }
}

const mapStateToProps = (state) => ({
  user: getCurrentUser(state)
})

export default (Wrapp) => connect(
  mapStateToProps
)(withProfile(Wrapp));

