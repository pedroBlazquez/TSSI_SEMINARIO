import React from 'react';
import {connect} from 'react-redux';
import {withRouter} from 'react-router-dom';
import {bindActionCreators} from 'redux';

import NavBar from '../components/NavBar';

import {getCurrentUser} from '../selectors/login';
import {logOut} from '../actions/loginActions';

const mapStateToProps = (state) => ({
  user: getCurrentUser(state)
});

const mapDispatchToProps = (dispatch) => ({
  onSalir: bindActionCreators(logOut, dispatch)
})

export default withRouter(connect(
  mapStateToProps,
  mapDispatchToProps
)(NavBar));
