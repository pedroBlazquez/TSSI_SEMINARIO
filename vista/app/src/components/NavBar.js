import React, {Component} from 'react';

import {Link} from 'react-router-dom';
import {Menu} from 'antd';

const MenuItem = Menu.Item;

class NavBar extends Component {

  render () {
    const {onSalir, user} = this.props;
    return (
      <Menu
        mode={'horizontal'}
      >
        <MenuItem key={'home'}>
          <Link to={`/`}>{'Home'}</Link>
        </MenuItem>
        <MenuItem key={user.id}>
          <Link to={`/perfil/${user.id}`}>{user.mail}</Link>
        </MenuItem>
        <MenuItem key={'Salir'}>
          <div onClick={onSalir}>{'Salir'}</div>
        </MenuItem>
      </Menu>
    )
  }
}

export default NavBar;

