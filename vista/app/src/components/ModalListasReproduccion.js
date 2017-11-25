import React, {Component} from 'react';
import {Link} from 'react-router-dom';
import {Modal} from 'antd';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';

import {ocultarListas, getListas} from '../actions/listasReproduccionActions';
import ListaReproduccionItem from './ListaReproduccionItem';


class ModalListasReproduccion extends Component {

  componentWillMount() {
      this.props.getListas();
  }

  render () {
    const {modalOpen, cerrarModal, listas} = this.props;

    return(
      <Modal
        title={'Seleccione la lista a la que desea agregar la cancion'}
        visible={modalOpen}
        footer={[]}
        onCancel={this.props.ocultarListas}
      >
        {
          !listas &&
          <Link to={`/perfil/${this.props.userId}/listas`} style={{color: 'black'}}>
            <h2>No tiene ninguna lista de reproduccion. Haga click aqui para crear una</h2>
          </Link>
        }
        {  
          !!listas &&
          listas.map((lista, index) => {
            return <ListaReproduccionItem key={index + lista.id} lista={lista} idCancion={this.props.idCancion}/>
          })
        }
      </Modal>
    );
  }
}

const mapStateToProps = (state) => ({
  modalOpen: state.perfilReducer.listasReproduccion.modalOpen,
  idCancion: state.perfilReducer.listasReproduccion.idCancion,
  listas: state.perfilReducer.listasReproduccion.listas,
  userId: state.perfilReducer.usuario.id
});

const mapDispatchToProps = (dispatch) => ({
    ocultarListas: bindActionCreators(ocultarListas, dispatch),
    getListas: bindActionCreators(getListas, dispatch)
});

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ModalListasReproduccion);
