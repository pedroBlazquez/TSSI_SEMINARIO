import React from 'react';
import {connect} from 'react-redux';
import {Route, withRouter, Switch} from 'react-router-dom';

import AuthRoute from './AuthRoute';
import PerfilContainer from '../containers/PerfilContainer';

const Perfil = () => (
  <PerfilContainer>
    <Switch>
      <Route path={'/perfil/:profileId'} exact component={() => (<div>{'Aca va la vista de perfil'}</div>)}/>
      <Route path={'/perfil/:profileId/seguidores'} component={() => (<div>{'Aca van los seguidores'}</div>)}/>
      <Route path={'/perfil/:profileId/seguidos'} component={() => (<div>{'Aca van los seguidos'}</div>)}/>
      <Route path={'/perfil/:profileId/listas'} component={() => (<div>{'Aca van las listas de reproduccion'}</div>)}/>
      <AuthRoute
        path={'/perfil/:profileId/administrar'}
        exact
        component={() => (<div>{'Panel de administracion'}</div>)}
        redirectTo={'/unauthorized'}
        authorized={true}
      />
    </Switch>
  </PerfilContainer>
);

export default withRouter(Perfil);