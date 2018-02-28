import React from 'react';
import {connect} from 'react-redux';
import {Route, withRouter, Switch} from 'react-router-dom';

import AuthRoute from './AuthRoute';

import MainLayout from '../views/MainLayout';
import Manual from '../views/manualusuario';
import NavBar from '../containers/UserNavBarContainer';
import LoginForm from '../containers/LoginContainer';
import RegistroUsuario from '../containers/RegistroUsuarioContainer';
import Perfil from './Perfil';
import NovedadesHome from '../containers/NovedadesHome'; 
import Reproductor from '../containers/ReproductorContainer';
import ModalListasReproduccion from '../components/ModalListasReproduccion';


// TODO: Cambiar las rutas para un mejor approach
// https://css-tricks.com/react-router-4/
const MainRoute = ({isLogged}) => (
  <MainLayout
    header={'MusicAPP'}
  >
    {isLogged && <ModalListasReproduccion />}
    {isLogged && <NavBar/>}
    <Route component={Manual} path="/help" exact/>
    <AuthRoute authorized={isLogged} redirectTo="/login" path="/" exact component={NovedadesHome}/>
    <AuthRoute authorized={isLogged} redirectTo="/login" path="/perfil/:profileId" component={Perfil}/>
    <Route component={LoginForm} path="/login" />
    <Route component={RegistroUsuario} path="/registrarse"/>
    {isLogged && 
      <div style={{position: 'fixed', bottom: 0, width: '100%', backgroundColor: 'grey', height: 60}}>
        <div style={{position: 'absolute', top: 20, left: 20}}>(C) Copyright MusicApp 2017</div>
        <Reproductor style={{margin: 'auto', marginTop: 10}} />
      </div>
    }
    <div style={{height: 60, width: '100%'}}></div>
  </MainLayout>
);

const mapStateToProps = (state) => ({
  isLogged: state.loginReducer.success
});


export default withRouter(connect(
  mapStateToProps
)(MainRoute));
