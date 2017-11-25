import React, {Component} from 'react';
import {Table, Icon} from 'antd';
import moment from 'moment';

import {ROLES_INTEGRANTES} from '../utils/constants';

class IntegrantesBanda extends Component {

  constructor (props) {
    super(props);
    this.columns = [
      {
        title: 'Integrante',
        dataIndex: 'nombre',
        key: 'integrante',
        render: (nombre, integrante) => {
          return integrante.nombre + ', ' + integrante.apellido;
        }
      }, {
        title: 'Rol',
        dataIndex: 'rol',
        key: 'rol',
        render: (rol) => {
          if (typeof rol === 'strong' || !isNaN(rol)) {
            return ROLES_INTEGRANTES.find(r => r.id.toString() === rol.toString()).value
          }

          return ROLES_INTEGRANTES.find(r => r.id.toString() === rol.id.toString()).value
        }
      }, {
        title: 'Fecha nacimiento',
        dataIndex: 'fechaNacimiento',
        key: 'fechaNacimiento',
        render: (text, record) => {
          return moment(text).isValid() ? moment(text).format('DD-MM-YYYY') : text;
        }
      },{
        title: 'Remover',
        dataIndex: 'remover',
        key: 'remover',
        render: (text, record) => {
          return <Icon type="delete" onClick={this.onDelete(record)} />
        }
      }
    ]
  } 

  onDelete = (record) => () => {
    const {onDelete} = this.props;
    onDelete(record);
  }

  render () {
    return (
      <Table 
        columns={this.columns}
        dataSource={this.props.integrantes}
        size={'small'}
        pagination={false}
      />
    );
  }
}

export default IntegrantesBanda;
