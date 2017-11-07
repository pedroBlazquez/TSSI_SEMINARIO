import React, {Component} from 'react';
import {isEmpty} from 'lodash';
import {Modal} from 'antd';

import {VOCALISTA} from '../utils/constants';
import FormIntegrante from './FormIntegranteBanda';

class ModalAltaIntegrante extends Component {

  constructor(props) {
    super(props);

    this.handleOk = this.handleOk.bind(this);
  }

  handleOk (e, integrante) {
    const {agregarIntegrante, cerrarModal} = this.props;
    agregarIntegrante(integrante);
    cerrarModal();
  }


  render () {
    const {visible, cerrarModal} = this.props;
    return(
      <Modal
        title={'Ingrese los datos del Integrante'}
        visible={visible}
        footer={[]}
      >
        <FormIntegrante onCancel={cerrarModal} onSubmit={this.handleOk}/>
      </Modal>
    );
  }
}

export default ModalAltaIntegrante;
