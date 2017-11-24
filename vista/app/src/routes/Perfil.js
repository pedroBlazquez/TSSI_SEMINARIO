import React from 'react';
import {connect} from 'react-redux';
import {Route, withRouter, Switch} from 'react-router-dom';

import AuthRoute from './AuthRoute';
import PerfilContainer from '../containers/PerfilContainer';
import NovedadesPerfil from '../containers/NovedadesPerfil';
import AdministrarContenido from '../containers/AdministrarContenidoContainer';
import CancionesPerfil from '../containers/CancionesPerfil';
import DiscosPerfil from '../containers/DiscosPerfil';
import AlbumesPerfil from '../containers/AlbumesPefil';
import SeguidosContainer from '../containers/SeguidosContainer';
import SeguidoresContainer from '../containers/SeguidoresContainer';

const Perfil = () => (
  <PerfilContainer>
    <Switch>
      <Route path={'/perfil/:profileId'} exact component={NovedadesPerfil}/>
      <Route path={'/perfil/:profileId/seguidores'} component={SeguidoresContainer}/>
      <Route path={'/perfil/:profileId/seguidos'} component={SeguidosContainer}/>
      <Route path={'/perfil/:profileId/listas'} component={() => (<div>{'Aca van las listas de reproduccion'}</div>)}/>
      <Route path={'/perfil/:profileId/canciones'} component={CancionesPerfil}/>
      <Route path={'/perfil/:profileId/discos'} component={DiscosPerfil}/>
      <Route path={'/perfil/:profileId/albumes'} component={AlbumesPerfil}/>
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