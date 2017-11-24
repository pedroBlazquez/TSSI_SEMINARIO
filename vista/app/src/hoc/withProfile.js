import React, {Component} from 'react';
import {connect} from 'react-redux';
import {USUARIO_OYENTE} from '../utils/constants';

import {getCurrentUser} from '../selectors/login';
import {getUsuarioPerfil} from '../selectors/perfil';

function withProfile (Wrapp) {
  return class WithProfile extends Component {
    esArtista () {
      const {user} = this.props;
      const {usuarioTipo} = user;
      return !!usuarioTipo && usuarioTipo.id !== USUARIO_OYENTE.id;
    }

    esPerfilPropio () {
      const {currentUser, user} = this.props;
      return currentUser.id === user.id;
    }

    render () {
      const esArtista = this.esArtista();
      const esPerfilPropio = this.esPerfilPropio();
      const {profileId} = this.props.match.params;
      return (
        <Wrapp 
          {...this.props}
          esArtista={esArtista}
          esPerfilPropio={esPerfilPropio}
          profileId={profileId}
        />
      )
    }
  }
}

const mapStateToProps = (state) => ({
  currentUser: getCurrentUser(state),
  user: getUsuarioPerfil(state)
})

export default (Wrapp) => connect(
  mapStateToProps
)(withProfile(Wrapp));

