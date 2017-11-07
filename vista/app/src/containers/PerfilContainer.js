import React, {Component} from 'react';
import {connect} from 'react-redux';
import {Route, withRouter, Switch} from 'react-router-dom';

import PerfilLayout from '../views/PerfilLayout';
import PerfilSideBar from '../components/PerfilSideBar';


class PerfilContainer extends Component {
  constructor (props) {
    super(props);
    
  }

  render () {
    const {children} = this.props;
    return (
      <PerfilLayout
        sider={<PerfilSideBar />}
      >
        {children}
      </PerfilLayout>
    );
  }
}

export default PerfilContainer;
