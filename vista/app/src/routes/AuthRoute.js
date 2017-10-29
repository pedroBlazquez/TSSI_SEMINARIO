import React from 'react';
import {Route, Redirect} from 'react-router-dom';

const PrivateRoute = ({ component: Component, authorized, redirectTo, ...rest }) => (
  <Route {...rest} render={props => (
    authorized ? (
      <Component {...props}/>
    ) : (
      <Redirect to={{
        pathname: redirectTo,
        state: { from: props.location }
      }}/>
    )
  )}/>
)

export default PrivateRoute;
