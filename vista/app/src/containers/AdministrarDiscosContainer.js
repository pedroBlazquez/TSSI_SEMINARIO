import React, {Component} from 'react';
import Contenido from '../components/Contenido';

const MOCK = [{id: 1, descripcion: 'Disco 1'}, {id: 2, descripcion: 'Disco 1'}, {id: 3, descripcion: 'Disco 1'}]

class AdministrarDiscosContainer extends Component {
  render () {
    return (
      <Contenido
        items={MOCK}
        onEliminar={(id) => {console.log(id)}}
        onEditar={(id) => {console.log(id)}}
        agregarButtonText={'Agregar Disco'}
        agregar
        showOptions
      />
    );
  }
}

export default AdministrarDiscosContainer;
