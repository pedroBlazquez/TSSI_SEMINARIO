import React, {Component} from 'react';

import {Card, Avatar} from 'antd';

const AvatarUsuario = ({usuario}) => (
  usuario.imagen ?
  <Avatar className='avatarIcon' src={usuario.imagen}/> : 
  <Avatar className='avatarIcon' icon={'user'}/>
);

export default AvatarUsuario;
