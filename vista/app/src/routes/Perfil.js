import React from 'react';
import {connect} from 'react-redux';
import {Route, withRouter, Switch} from 'react-router-dom';

import AuthRoute from './AuthRoute';
import PerfilContainer from '../containers/PerfilContainer';
import NovedadesPerfil from '../containers/NovedadesPerfil';
import AdministrarContenido from '../containers/AdministrarContenidoContainer';

const Perfil = () => (
  <PerfilContainer>
    <Switch>
      <Route path={'/perfil/:profileId'} exact component={NovedadesPerfil}/>
      <Route path={'/perfil/:profileId/seguidores'} component={() => (<div>{'Aca van los seguidores'}</div>)}/>
      <Route path={'/perfil/:profileId/seguidos'} component={() => (<div>{'Aca van los seguidos'}</div>)}/>
      <Route path={'/perfil/:profileId/listas'} component={() => (<div>{'Aca van las listas de reproduccion'}</div>)}/>
      <AuthRoute
        path={'/perfil/:profileId/administrar'}
        exact
        component={AdministrarContenido}
        redirectTo={'/unauthorized'}
        authorized={true}
      />
    </Switch>
  </PerfilContainer>
);

export default withRouter(Perfil);