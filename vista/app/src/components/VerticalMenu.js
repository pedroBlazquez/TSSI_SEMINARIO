import React, {Component} from 'react';
import '../styles/VerticalMenu.css';
import cn from 'classnames';
import {NavLink} from 'react-router-dom';

class VerticalMenu extends Component {

  render () {
    const {options} = this.props;
    return (
      <div className={'flex flex-col vertical-menu-container'}>
        {options.map(o => 
          <NavLink
            key={o.value}
            className={cn('option')}
            exact={o.exact}
            to={o.to}
            activeClassName={'active-option'}
          >
            {o.value}
          </NavLink>
        )}
      </div>
    );
  }
}

export default VerticalMenu;
