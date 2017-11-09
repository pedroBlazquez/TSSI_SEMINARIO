import React, {Component} from 'react';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';
import {withRouter} from 'react-router-dom';

import PerfilLayout from '../views/PerfilLayout';
import PerfilSideBar from '../components/PerfilSideBar';
import withProfile from '../hoc/withProfile';

import {cargarPerfil, restorePerfil} from '../actions/perfilActions'

const SideBar = withRouter(withProfile(PerfilSideBar));

class PerfilContainer extends Component {
  constructor (props) {
    super(props);
    
  }

  componentWillMount () {
    const {cargarPerfil, match} = this.props;
    cargarPerfil(match.params.profileId);
  }

  componentWillUnMount () {
    this.props.restorePerfil();
  }

  render () {
    const {children} = this.props;
    return (
      <PerfilLayout
        sider={<SideBar />}
      >
        {children}
      </PerfilLayout>
    );
  }
}

const mapDispatchToProps = (dispatch) => ({
  cargarPerfil: bindActionCreators(cargarPerfil, dispatch),
  restorePerfil: bindActionCreators(restorePerfil, dispatch)
});

export default withRouter(connect(
  null,
  mapDispatchToProps
)(PerfilContainer));
