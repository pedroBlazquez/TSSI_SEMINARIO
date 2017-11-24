import React from 'react';
import {connect} from 'react-redux';
import {Route, withRouter, Switch} from 'react-router-dom';

import AuthRoute from './AuthRoute';

import MainLayout from '../views/MainLayout';
import NavBar from '../containers/UserNavBarContainer';
import LoginForm from '../containers/LoginContainer';
import RegistroUsuario from '../containers/RegistroUsuarioContainer';
import Perfil from './Perfil';
import NovedadesHome from '../containers/NovedadesHome'; 
import Reproductor from '../containers/ReproductorContainer';


// TODO: Cambiar las rutas para un mejor approach
// https://css-tricks.com/react-router-4/
const MainRoute = ({isLogged}) => (
  <MainLayout
    header={'MusicAPP'}
  >
    {isLogged && <NavBar/>}
    <AuthRoute authorized={isLogged} redirectTo="/login" path="/" exact component={NovedadesHome}/>
    <AuthRoute authorized={isLogged} redirectTo="/login" path="/perfil/:profileId" component={Perfil}/>
    <Route component={LoginForm} path="/login" />
    <Route component={RegistroUsuario} path="/registrarse"/>
    {isLogged && 
      <div style={{position: 'fixed', bottom: 0, width: '100%', 'background-color': 'grey', height: 60}}>
        <div style={{position: 'absolute', top: 20, left: 20}}>(C) Copyright MusicApp 2017</div>
        <Reproductor style={{margin: 'auto', 'margin-top': 10}} />
      </div>
    }
  </MainLayout>
);

const mapStateToProps = (state) => ({
  isLogged: state.loginReducer.success
});


export default withRouter(connect(
  mapStateToProps
)(MainRoute));
