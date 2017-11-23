import React, {Component} from 'react';
import {withRouter} from 'react-router-dom';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';

import {getNovedades} from '../actions/novedadesAction';

import {Card} from 'antd';
import MainContent from '../components/MainContent';
import FormNuevaPublicacion from '../components/FormNuevaPublicacion';
import withProfile from '../hoc/withProfile';
import Novedades from '../components/Novedades';

class NovedadesPerfil extends Component {
  componentWillMount() {
    this.props.getUltimasPublicaciones();
  }

  render () {
    const {esPerfilPropio, records} = this.props;
    return (
      <MainContent>
       {esPerfilPropio ?
          <Card className={'margin-10p'} title={'Publicá un mensaje!'}>
            <FormNuevaPublicacion />
          </Card> : 
          null
        }
        <Novedades conPublicacion={esPerfilPropio} records={records} />
      </MainContent>
    );
  }
}

const mapStateToProps = (state) => ({
  records: state.novedadesReducer.records
});

const mapDispatchToProps = (dispatch) => ({
  getUltimasPublicaciones: bindActionCreators(getNovedades, dispatch)
});

export default withRouter(withProfile(connect(
  mapStateToProps,
  mapDispatchToProps
)(NovedadesPerfil)
));
