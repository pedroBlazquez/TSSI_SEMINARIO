import React, {Component} from 'react';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';
import {Tabs} from 'antd';
import moment from 'moment';
import {isEqual} from 'lodash';

import '../styles/AdministrarContenido.css';

import {USUARIO_OYENTE, USUARIO_BANDA} from '../utils/constants';
import {updateUser} from '../actions/registerActions';
import {updateProfileTree} from '../actions/perfilActions';

import PerfilWrapper from '../components/PerfilContentWrapper';
import DatosArtista from '../components/FormDatosArtista';
import DatosUsuario from '../components/FormRegistroUsuario';
import FormFotoPerfil from '../components/FormFotoPerfil';
import {getUsuarioPerfil} from '../selectors/perfil';

import {ROLES_INTEGRANTES} from '../utils/constants';

const TabPane = Tabs.TabPane;

class AdministrarPerfil extends Component {

  constructor (props) {
    super(props);
    this.esArtista = props.usuario.usuarioTipo.id !== USUARIO_OYENTE.id;
    this.esBanda = props.usuario.usuarioTipo.id === USUARIO_BANDA.id;

    let initialState = {
      usuarioFields: {
        usuario: {
          value: props.usuario.mail
        },
        password: {
          value: props.usuario.password
        },
        nombre: {
          value: props.usuario.nombre
        },
        apellido: {
          value: props.usuario.apellido
        },
        fechaNacimiento: {
          value: moment(props.usuario.fechaNacimiento)
        },
        tipoUsuario: {
          value: props.usuario.usuarioTipo.id
        }
      }
    }

    if (this.esArtista) {
      const {artista} = props.usuario;
      initialState.artistaFields = {
        generos: {
          value: artista[0].generos.map(g => g.id)
        },
        fechaInicio: {
          value: moment(artista[0].fechaInicio)
        },
        descripcion: {
          value: artista[0].descripcion
        },
        nombreFantasia: {
          value: artista[0].nombreFantasia
        },
        integrantes: artista[0].integrantes || []
      }
    }

    this.state = {...initialState};
  }

  onDatosUsuarioChange = (changedFields) => {
    this.setState({usuarioFields: {...this.state.usuarioFields, ...changedFields}});
  }

  onDatosArtistaChange = (changedFields) => {
    const artistaFields = this.state.artistaFields;
    this.setState({artistaFields: {...artistaFields, ...changedFields}});
  }

  onSubmit = (e, values) => {
    const {actualizarUsuario, actualizarUsuarioStore} = this.props,
          {nombre, apellido} = this.state.usuarioFields,
          {descripcion, nombreFantasia, integrantes} = this.state.artistaFields || {};
    let data = {'nombre': nombre.value, 'apellido': apellido.value}

    actualizarUsuario(this.state);

    if (descripcion && nombreFantasia) {
      data.nombreFantasia = nombreFantasia.value;
      data.descripcion = descripcion.value;
      data.integrantes = integrantes;
    }

    actualizarUsuarioStore(data);
  }

  agregarIntegrante = (integrante) => {
    const {artistaFields} = this.state;
    const integrantes = [...artistaFields.integrantes];
    const integranteNuevo = {...integrante, key: integrantes.length};
    const nuevoIntegranteRol = ROLES_INTEGRANTES.find(r => r.id === integranteNuevo.rol);
    integranteNuevo.rol = {id: nuevoIntegranteRol.id, descripcion: nuevoIntegranteRol.value};
    integrantes.push(integranteNuevo);
    this.setState({artistaFields: Object.assign({}, artistaFields, {integrantes})});
  }

  removerIntegrante = (integrante) => {
    const {artistaFields} = this.state;
    const integrantes = [...artistaFields.integrantes].filter(i => !isEqual(i, integrante));
    this.setState({artistaFields: {...artistaFields, integrantes}});
  }
  
  render () {
    return (
      <PerfilWrapper>
        <Tabs className={'full-height bg-color-white table-container perfilAdministrarTabs'}>
          <TabPane tab="Usuario" key="1">
            <div className={'autoalign'} style={{width: '50%'}}>
              <DatosUsuario
                onSubmit={this.onSubmit}
                onChange={this.onDatosUsuarioChange}
                {...this.state.usuarioFields} 
                update
              />
            </div>
          </TabPane>
          <TabPane tab="Cambiar Foto de Perfil" key="3">
              <div className="formContenedor">
                <div className="formFotoPerfil">
                  <FormFotoPerfil />
                </div>
                {this.esArtista &&
                  <div className="mensajeArtista">
                    Recorda que esta cuenta representa a una Banda o Artista, tené en cuenta eso a la
                    hora de elegir una foto de perfil :)
                  </div>
                }
              </div>
          </TabPane>
          {this.esArtista &&
            <TabPane tab="Artista / Banda" key="2">
              <div className={'autoalign'} style={{width: '50%', maxHeight: 440, paddingRight: 10}}>
                <DatosArtista
                  onSubmit={this.onSubmit}
                  onChange={this.onDatosArtistaChange}
                  agregarIntegrante={this.agregarIntegrante}
                  removerIntegrante={this.removerIntegrante}
                  esBanda={this.esBanda}
                  {...this.state.artistaFields}
                  update
                />
              </div>
            </TabPane>
          }
        </Tabs>
      </PerfilWrapper>
    );
  }
}

const mapStateToProps = (state) => ({
  usuario: getUsuarioPerfil(state)
});

const mapDispatchToProps = (dispatch) => ({
  actualizarUsuario: bindActionCreators(updateUser, dispatch),
  actualizarUsuarioStore: bindActionCreators(updateProfileTree, dispatch)
});

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(AdministrarPerfil);
