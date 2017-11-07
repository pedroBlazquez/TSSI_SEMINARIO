import React, {Component} from 'react';

import {withRouter, Link} from 'react-router-dom';
import {Menu} from 'antd';

const MenuItem = Menu.Item;

class NavBar extends Component {

  render () {
    const {user, match, location} = this.props;
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
          <Link to={'/login'}>{'Salir'}</Link>
        </MenuItem>
      </Menu>
    )
  }
}

export default withRouter(NavBar);

