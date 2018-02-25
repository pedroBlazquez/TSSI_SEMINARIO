import React, {Component} from 'react';
import '../styles/profile.css';
import {withRouter, NavLink} from 'react-router-dom';

import {Button, Modal} from 'antd';

import NoImage from '../assets/no-image-profile.png';
import VerticalMenu from './VerticalMenu';
import PerfilWrapper from './PerfilContentWrapper';
import SeguirUsuario from './SeguirUsuario';

const confirm = Modal.confirm;

const getProfileOptions = (profileId) => [
  {to: `/perfil/${profileId}`, value: 'Perfil', exact: true},
  {to: `/perfil/${profileId}/seguidores`, value: 'Seguidores'},
  {to: `/perfil/${profileId}/seguidos`, value: 'Seguidos'},
  {to: `/perfil/${profileId}/listas`, value: 'Listas de Reproducción'}
];

const getArtistaOptions = (profileId) => [
  {to: `/perfil/${profileId}/canciones`, value: 'Canciones'},
  {to: `/perfil/${profileId}/discos`, value: 'Discos'},
  {to: `/perfil/${profileId}/albumes`, value: 'Albumes'},
  {to: `/perfil/${profileId}/eventos`, value: 'Eventos'},
  {to: `/perfil/${profileId}/informacion`, value: 'Informacion'}
];

const getOptions = (profileId, esArtista) => {
  const baseOptions = getProfileOptions(profileId);
  if (esArtista) {
    return baseOptions.concat(getArtistaOptions(profileId));
  }
  return baseOptions;
}

class PerfilSideBar extends Component {
  mostrarModalBajaUsuario = () => {
    const {bajaUsuario} = this.props;
    confirm({
      title: 'Baja de usuario',
      content: 'Si confirma, la acción no podrá deshacerse',
      onOk() {
        bajaUsuario();
      },
      onCancel() {},
    });
  }
  
  render () {
    const {esArtista, esPerfilPropio, user, profileId} = this.props;
    const userName = esArtista ? user.artista[0].nombreFantasia : `${user.nombre}, ${user.apellido}`;
    return (
      <PerfilWrapper>
        <div className={'side-bar-container relative'}>
          <div className={'absolute'} style={{top: 5, left: 5}}>
            {!esPerfilPropio && <SeguirUsuario id={user.id}/>}
          </div>
          <img className={'img-circle'} src={user.imagen || NoImage}/>
          <h3 className={'profile-title'}>{userName}</h3>
          <VerticalMenu options={getOptions(profileId, esArtista)} />
          <div className={'margin-5p'}>
            {esPerfilPropio && 
              <Button className={'green-button full-width margin-5p-bottom'}>
                <NavLink to={`/perfil/${profileId}/editar`}>{'Editar Perfil'}</NavLink>
              </Button>
            }
            {esArtista && esPerfilPropio && 
              <Button className={'green-button full-width'}>
                <NavLink to={`/perfil/${profileId}/administrar`}>{'Administrar contenido'}</NavLink>
              </Button>
            }
          </div>
          <div className={'absolute'} style={{bottom: 5}}>
            {esPerfilPropio && 
              <Button className={'green-button full-width'} onClick={this.mostrarModalBajaUsuario}>
                {'Eliminar Cuenta'}
              </Button>
            }
          </div>
        </div>
      </PerfilWrapper>
    );
  }
}

export default PerfilSideBar;
