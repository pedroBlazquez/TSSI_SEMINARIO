import React, {Component} from 'react';
import Contenido from '../components/Contenido';

import {Modal} from 'antd';

class AdministrarContenido extends Component {
  constructor (props) {
    super(props);
    this.state = {
      modalOpen: false
    }
  }

  toggleModal = () => {
    this.setState({modalOpen: !this.state.modalOpen});
  }

  closeModalAndSubmit = (e, values) => {
    const {formElementProps} = this.props;
    const {onSubmit} = formElementProps;
    this.toggleModal();
    onSubmit(e, values);
  }

  closeModalAndCancel = (e, values) => {
    const {formElementProps} = this.props;
    const {onCancel} = formElementProps;

    if (typeof onCancel === 'function') {
      onCancel(e, values);
    }
    this.toggleModal();
  }

  openModalAndEdit = (id) => {
    const {onEditar} = this.props.contenidoProps;
    if (typeof onEditar === 'function') {
      onEditar(id);
    }
    this.toggleModal();
  }

  render () {
    const {modalOpen} = this.state;
    const {modalTitle, contenidoProps, FormElement, formElementProps, mostrarContenido} = this.props;
    return (
      <div className={mostrarContenido ? 'show' : "hide"}>
        <Contenido
          {...contenidoProps}
          onAgregar={this.toggleModal}
          onEditar={this.openModalAndEdit}
          agregar
        />
        {modalOpen && 
          <Modal
            onCancel={this.closeModalAndCancel}
            title={modalTitle}
            visible={modalOpen}
            footer={[]}
          >
            <FormElement
              {...formElementProps}
              onCancel={this.closeModalAndCancel}
              onSubmit={this.closeModalAndSubmit}
            />
          </Modal>
        }
      </div>
    );
  }
}

export default AdministrarContenido;
