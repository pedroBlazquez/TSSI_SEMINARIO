import React, {Component} from 'react';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import { Form, Button, Icon, message } from 'antd';
import Upload from './UploadSingleFile';
import ExtendedForm from './ExtendedForm';

import {guardarFotoPerfil, actualizarFotoPerfil} from '../actions/perfilActions';
import {validateFile} from '../utils/validators';

const FormItem = Form.Item;

class FormFotoPerfil extends Component {

    constructor (props) {
        super(props)

        this.state = {profileUrl: ''}
    }

    onSubmit = (e) => {
        e.preventDefault();
        this.props.guardarFotoPerfil(this.state.profileUrl);
        message.success("Foto cambiada con exito");
        this.props.actualizarFotoPerfil(this.state.profileUrl);
    }

    onChange = (data) => {
        if (data.file.status === 'done') {
            this.setState({profileUrl: data.file.response})
        }
    }

    render () {
        const {form} = this.props;
        return (
            <div>
                <Form onSubmit={this.onSubmit}>
                    <FormItem>
                        <Upload 
                            accept='image/*'
                            name={'file'}
                            action={'http://localhost:8080/archivo/subirPerfilFoto'}
                            multiple = {false}
                            onChange = {this.onChange}
                        >
                            <Button>
                                <Icon type='upload'/>
                                {'Subir Foto'}
                            </Button>
                        </Upload>
                    </FormItem>
                    <FormItem>
                        <Button htmlType="submit" className={'green-button'} disabled={!this.state.profileUrl}>
                            {'Confirmar'}
                        </Button>
                    </FormItem>
                </Form>
            </div>
        );
    }

}

const mapDispatchToProps = (dispatch) => ({
    guardarFotoPerfil: bindActionCreators(guardarFotoPerfil, dispatch),
    actualizarFotoPerfil: bindActionCreators(actualizarFotoPerfil, dispatch)
});

const form = Form.create({})(ExtendedForm(FormFotoPerfil));

export default connect(null, mapDispatchToProps)(form);
