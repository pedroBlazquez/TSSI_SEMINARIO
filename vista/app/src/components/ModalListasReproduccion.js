import React, {Component} from 'react';
import {Link} from 'react-router-dom';
import {Modal} from 'antd';
import {connect} from 'react-redux';

import {ocultarListas, getListas} from '../actions/listasReproduccionActions';
import ListaReproduccionItem from './ListaReproduccionItem';
import { getCurrentUser } from '../selectors/login';


class ModalListasReproduccion extends Component {
  componentWillReceiveProps (nextProps) {
    if (nextProps.modalOpen) {
      this.props.getListas();
    }
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
          <Link to={`/perfil/${this.props.userId}/listas`} onClick={this.props.ocultarListas} style={{color: 'black'}}>
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
  modalOpen: state.listasReproduccion.modalOpen,
  idCancion: state.listasReproduccion.idCancion,
  listas: state.listasReproduccion.listas,
  userId: getCurrentUser(state).id
});

export default connect(
  mapStateToProps,
  {
    getListas,
    ocultarListas
  }
)(ModalListasReproduccion);
