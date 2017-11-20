import React, {Component} from 'react';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';

import {Card} from 'antd';
import Publicacion from './Publicacion';
import FormNuevaPublicacion from './FormNuevaPublicacion';
import Evento from './Evento';
import Artista from './Artista';
import Cancion from './Cancion';
import Disco from './Disco';

import {getNovedades} from '../actions/novedadesAction';

import '../styles/novedades.css'

class Novedades extends Component {

  componentWillMount() {
    this.props.getNovedades();
  }

  getElements = () => {
    const {records} = this.props;
    return records.map(function(record) {
      let element;
      switch(record.object_type) {
        case 'Artista':
          element = <Artista key={record.id} artista={record} />;
          break;
        case 'Publicacion':
          element = <Publicacion key={record.id} publicacion={record} />;
          break;
        case 'Evento':
          element = <Evento key={record.id} evento={record} />;
          break;
        case 'Disco':
          element = <Disco key={record.id} disco={record} />;
          break;
        case 'Cancion':
          element = <Cancion key={record.id} cancion={record} />;
          break;
      }
      return element;
    });
  }

  render () {
    const {conPublicacion, records} = this.props;

    return (
      <div className={'full-height'} style={{maxWidth: 600, width: '80%', margin: '0 auto', paddingTop: 10}}>
        { conPublicacion &&
          <Card className={'margin-10p'} title={'Publicá un mensaje!'}>
            <FormNuevaPublicacion />
          </Card>
        }
        { records.length && this.getElements() }
      </div>
    );
  }
}

const mapStateToProps = (state) => ({
  records: state.novedadesReducer.records
});

const mapDispatchToProps = (dispatch) => ({
  getNovedades: bindActionCreators(getNovedades, dispatch)
});

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Novedades);
