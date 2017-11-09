import React, {Component} from 'react';
import Contenido from '../components/Contenido';

import {Modal} from 'antd';

class AdministrarDiscosContainer extends Component {
  constructor (props) {
    super(props);
    this.state = {
      modalOpen: false
    }
  }

  toggleModal = () => {
    this.setState({modalOpen: !this.state.modalOpen});
  }


  render () {
    const {modalOpen} = this.state;
    const {onEliminar, onEditar, items} = this.props;

    return (
      <div>
        <Contenido
          items={items}
          onEliminar={onEliminar}
          onEditar={(id) => {console.log(id)}}
          agregarButtonText={'Agregar Disco'}
          onAgregar={this.toggleModal}
          agregar
          showOptions
        />
        {modalOpen && 
          <Modal
            title={'Alta Disco'}
            visible={modalOpen}
            footer={[]}
          >
            <div onClick={this.toggleModal}>{'Administrar discos'}</div>
          </Modal>
        }
      </div>
    );
  }
}

export default AdministrarDiscosContainer;
