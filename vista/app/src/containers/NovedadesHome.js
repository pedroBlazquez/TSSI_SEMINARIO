import React, {Component} from 'react';
import {withRouter} from 'react-router-dom';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';

import {getNovedades} from '../actions/novedadesAction';

import {getRecordsInicio} from '../selectors/inicio';

import {Card} from 'antd';
import Busqueda from '../containers/Busqueda';
import MainContent from '../components/MainContent';
import withProfile from '../hoc/withProfile';
import Novedades from '../components/Novedades';
import Reproductor from '../components/Reproductor';

class NovedadesHome extends Component {
  componentWillMount() {
    this.props.getNovedades();
  }

  render () {
    const {records} = this.props;
    return (
      <MainContent>
        <Card className={'margin-10p'}>
          <Busqueda />
        </Card>
        <Novedades records={records} />
      </MainContent>
    );
  }
}

const mapStateToProps = (state) => ({
  records: getRecordsInicio(state)
});

const mapDispatchToProps = (dispatch) => ({
  getNovedades: bindActionCreators(getNovedades, dispatch)
});

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(NovedadesHome);
