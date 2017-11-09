import React, {Component} from 'react';
import {withRouter} from 'react-router-dom';

import withProfile from '../hoc/withProfile';
import Novedades from '../components/Novedades';

class NovedadesPerfil extends Component {

  render () {
    const {esPerfilPropio} = this.props;
    return (
      <Novedades conPublicacion={esPerfilPropio} />
    );
  }
}

export default withRouter(withProfile(NovedadesPerfil));
