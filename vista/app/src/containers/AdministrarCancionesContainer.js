import React, {Component} from 'react';
import Contenido from '../components/Contenido';
import AltaCancion from '../components/FormAltaCancion';

import {Modal} from 'antd';

const MOCK = [{id: 1, descripcion: 'Cancion 1'}, {id: 2, descripcion: 'Cancion 2'}, {id: 3, descripcion: 'Cancion 3'}]

class AdministrarCancionesContainer extends Component {
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
    return (
      <div>
        <Contenido
          items={MOCK}
          onEliminar={(id) => {console.log(id)}}
          onEditar={(id) => {console.log(id)}}
          agregarButtonText={'Agregar Cancion'}
          onAgregar={this.toggleModal}
          agregar
          showOptions
        />
        {modalOpen && 
          <Modal
            title={'Alta Cancion'}
            visible={modalOpen}
            footer={[]}
          >
            <AltaCancion
              onCancel={this.toggleModal}
              onSubmit={(e, v) => {
                debugger;
                this.toggleModal();
              }}
            />
          </Modal>
        }
      </div>
    );
  }
}

export default AdministrarCancionesContainer;
