import React from 'react';
import {connect} from 'react-redux';
import {withRouter} from 'react-router-dom';

import NavBar from '../components/NavBar';

import {getCurrentUser} from '../selectors/login';

const mapStateToProps = (state) => ({
  user: getCurrentUser(state)
});

export default withRouter(connect(
  mapStateToProps
)(NavBar));
