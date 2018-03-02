import React, {Component} from 'react';
import {Link} from 'react-router-dom';
import {Modal, Divider} from 'antd';
import {connect} from 'react-redux';

import {ocultarListas, getListas} from '../actions/listasReproduccionActions';
import ListaReproduccionItem, {ListaReproduccionItem as AgregarAColaDeReproduccion} from './ListaReproduccionItem';
import { getCurrentUser } from '../selectors/login';
import { getCancionAgregarALista } from '../selectors/listasReproduccion';
import { agregarCola } from '../actions/reproductorActions';


class ModalListasReproduccion extends Component {
  componentWillReceiveProps (nextProps) {
    if (nextProps.modalOpen) {
      this.props.getListas();
    }
  }

  render () {
    const {modalOpen, cerrarModal, listas, cancion, agregarCola} = this.props;

    return(
      <Modal
        title={'Seleccione la lista a la que desea agregar la cancion'}
        visible={modalOpen}
        footer={[]}
        onCancel={this.props.ocultarListas}
      >
        <AgregarAColaDeReproduccion 
          lista={{nombre: 'Agregar a cola de reproduccion'}}
          pushSongToList={agregarCola}
          cancion={cancion}
        />
        <Divider />
        {
          !listas &&
          <Link to={`/perfil/${this.props.userId}/listas`} onClick={this.props.ocultarListas} style={{color: 'black'}}>
            <h2>No tiene ninguna lista de reproduccion. Haga click aqui para crear una</h2>
          </Link>
        }
        {  
          !!listas &&
          listas.map((lista, index) => {
            return <ListaReproduccionItem key={index + lista.id} lista={lista} cancion={cancion}/>
          })
        }
      </Modal>
    );
  }
}

const mapStateToProps = (state) => ({
  modalOpen: state.listasReproduccion.modalOpen,
  cancion: getCancionAgregarALista(state),
  listas: state.listasReproduccion.listas,
  userId: getCurrentUser(state).id
});

export default connect(
  mapStateToProps,
  {
    getListas,
    ocultarListas,
    agregarCola
  }
)(ModalListasReproduccion);
