import React, {Component} from 'react';
import '../styles/profile.css';
import {withRouter, NavLink} from 'react-router-dom';

import {Button} from 'antd';

import NoImage from '../assets/no-image-profile.png';
import VerticalMenu from './VerticalMenu';

const getProfileOptions = (profileId) => [
  {to: `/perfil/${profileId}`, value: 'Perfil', exact: true},
  {to: `/perfil/${profileId}/seguidores`, value: 'Seguidores'},
  {to: `/perfil/${profileId}/seguidos`, value: 'Seguidos'},
  {to: `/perfil/${profileId}/listas`, value: 'Listas de Reproducción'}
];

const getArtistaOptions = (profileId) => [
  {to: `/perfil/${profileId}/albumes`, value: 'Albumes'},
  {to: `/perfil/${profileId}/discos`, value: 'Discos'},
  {to: `/perfil/${profileId}/canciones`, value: 'Canciones'},
  {to: `/perfil/${profileId}/informacion`, value: 'Informacion'},
];

const getOptions = (profileId, esArtista) => {
  const baseOptions = getProfileOptions(profileId);
  if (esArtista) {
    return baseOptions.concat(getArtistaOptions(profileId));
  }
  return baseOptions;
}

class PerfilSideBar extends Component {
  render () {
    const {match, esArtista, esPerfilPropio, user} = this.props;
    return (
      <div className={'side-bar-container margin-10p relative'}>
        <img className={'img-circle'} src={NoImage}/>
        <h3 className={'profile-title'}>{'Fulano Perez'}</h3>
        <VerticalMenu options={getOptions(match.params.profileId, esArtista)} />
        <div className={'margin-5p'}>
          {esPerfilPropio && 
            <Button className={'green-button full-width margin-5p-bottom'}>
              {'Editar Perfil'}
            </Button>
          }
          {esArtista && esPerfilPropio && 
            <Button className={'green-button full-width'}>
              <NavLink to={`/perfil/${match.params.profileId}/administrar`}>{'Administrar contenido'}</NavLink>
            </Button>
          }
        </div>
        <div className={'absolute'} style={{bottom: 5}}>
          {esPerfilPropio && 
            <Button className={'green-button full-width'}>{'Eliminar Cuenta'}</Button>
          }
        </div>
      </div>
    );
  }
}

export default PerfilSideBar;
