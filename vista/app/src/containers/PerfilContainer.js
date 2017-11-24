import React, {Component} from 'react';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';
import {withRouter} from 'react-router-dom';

import {Spin} from 'antd';

import PerfilLayout from '../views/PerfilLayout';
import PerfilSideBar from '../components/PerfilSideBar';
import withProfile from '../hoc/withProfile';

import {cargarPerfil, restorePerfil} from '../actions/perfilActions'
import {getLoadingPerfil} from '../selectors/perfil';

const SideBar = withRouter(withProfile(PerfilSideBar));

class PerfilContainer extends Component {
  constructor (props) {
    super(props);
    
  }

  componentWillMount () {
    const {cargarPerfil, match} = this.props;
    cargarPerfil(match.params.profileId);
  }

  componentWillReceiveProps (nextProps) {
    const {cargarPerfil, match} = this.props;
    if (match.params.profileId !== nextProps.match.params.profileId) {
      cargarPerfil(nextProps.match.params.profileId);
    }
  }

  componentWillUnMount () {
    this.props.restorePerfil();
  }

  render () {
    const {children, loading} = this.props;
    if (loading) return (<Spin />);
    return (
      <PerfilLayout
        sider={<SideBar />}
      >
        {children}
      </PerfilLayout>
    );
  }
}

const mapStateToProps = (state) => ({
  loading: getLoadingPerfil(state)
});

const mapDispatchToProps = (dispatch) => ({
  cargarPerfil: bindActionCreators(cargarPerfil, dispatch),
  restorePerfil: bindActionCreators(restorePerfil, dispatch)
});

export default withRouter(connect(
  mapStateToProps,
  mapDispatchToProps
)(PerfilContainer));
