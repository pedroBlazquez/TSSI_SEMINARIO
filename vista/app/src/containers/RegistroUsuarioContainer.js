import React, {Component} from 'react';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';
import {withRouter} from 'react-router';
import moment from 'moment';

import {TIPOS_USUARIO, USUARIO_ARTISTA, USUARIO_OYENTE, USUARIO_BANDA} from '../utils/constants';

import RegistroUsuarioForm from '../components/FormRegistroUsuario';
import FormWrapper from '../components/FormWrapper';
import DatosArtistaForm from '../components/FormDatosArtista';

// Actions
import {requestRegister} from '../actions/registerActions';

const initialUsuarioFields = {
  usuario: {},
  password: {},
  nombre: {},
  apellido: {},
  fechaNacimiento: {},
  tipoUsuario: {
    value: USUARIO_OYENTE
  }
};

const initialArtistaFields = {
  generos: {
    value: []
  },
  fechaInicio: {
    value: moment()
  },
  descripcion: {
    value: ''
  },
  nombreFantasia: {
    value: ''
  },
  integrantes: []
};

class RegistroUsuarioContainer extends Component {
  constructor (props) {
    super(props);

    this.state = {
      usuarioFields : initialUsuarioFields,
      artistaFields: initialArtistaFields,
      formType: USUARIO_OYENTE
    };
  }

  renderForm = () => {
    const {formType, usuarioFields} = this.state;
    switch (formType) {
      case USUARIO_OYENTE :
        return (
          <RegistroUsuarioForm
            onSubmit={this.onSubmit}
            onChange={this.onDatosUsuarioChange}
            onCancel={this.onCancel}
            {...this.state.usuarioFields}
          />
        );
      case USUARIO_ARTISTA:
      case USUARIO_BANDA:
        return (
          <DatosArtistaForm
            onSubmit={this.onSubmit}
            onChange={this.onDatosArtistaChange}
            onCancel={this.onCancel}
            esBanda={formType === USUARIO_BANDA}
            agregarIntegrante={this.agregarIntegrante}
            removerIntegrante={this.removerIntegrante}
            {...this.state.artistaFields}
          />
        );
    }
  }

  onFormChange = (changedFields) => {
    switch (this.state.formType) {
      case USUARIO_OYENTE:
        this.setState({usuarioFields: {...this.state.usuarioFields, ...changedFields}});
        break;
      case USUARIO_ARTISTA:
      case USUARIO_BANDA:
        const artistaFields = this.state.artistaFields;
        this.setState({artistaFields: {...artistaFields, ...changedFields}});
        break;
    }
  }

  onDatosUsuarioChange = (changedFields) => {
    this.setState({usuarioFields: {...this.state.usuarioFields, ...changedFields}});
  }

  onDatosArtistaChange = (changedFields) => {
    const artistaFields = this.state.artistaFields;
    this.setState({artistaFields: {...artistaFields, ...changedFields}});
  }

  onSubmit = (e, values) => {
    const {registrarUsuario} = this.props;
    if (values.tipoUsuario !== USUARIO_OYENTE && this.state.formType === USUARIO_OYENTE) {
      this.setState({formType: values.tipoUsuario});
    } else {
      registrarUsuario(values);
    }
  }

  onCancel = () => {
    this.setState({formType: USUARIO_OYENTE, artistaFields: initialArtistaFields});
  }

  agregarIntegrante = (integrante) => {
    const {artistaFields} = this.state;
    const integrantes = [...artistaFields.integrantes];
    const integranteNuevo = {...integrante, key: integrantes.length};
    integrantes.push(integranteNuevo);
    this.setState({artistaFields: Object.assign({}, artistaFields, {integrantes})});
  }

  removerIntegrante = (integrante) => {
    const {artistaFields} = this.state;
    const integrantes = [...artistaFields.integrantes].filter(i => i.key !== integrante.key);
    this.setState({artistaFields: {...artistaFields, integrantes}});
  }

  onCancel = () => {
    const {history} = this.props;
    history.push('/login');
  }

  render () {
    const {error} = this.props;
    return (
      <FormWrapper
        error={error}
        title={'Complete sus datos'}
      >
        {this.renderForm()}
      </FormWrapper>
    );
  }
}

const mapStateToProps = (state) => ({
  error: state.altaUsuarioStatus.message
});
const mapDispatchToProps = (dispatch) => ({
  registrarUsuario: bindActionCreators(requestRegister, dispatch)
});

export default withRouter(
  connect(
    mapStateToProps,
    mapDispatchToProps
  )(RegistroUsuarioContainer)
);