import React, {Component} from 'react';

import {Table, Icon} from 'antd';

class IntegrantesBanda extends Component {

  constructor (props) {
    super(props);
    this.columns = [
      {
        title: 'Integrante',
        dataIndex: 'integrante',
        key: 'integrante',
      }, {
        title: 'Rol',
        dataIndex: 'rol',
        key: 'rol',
      }, {
        title: 'Fecha nacimiento',
        dataIndex: 'fechaNacimiento',
        key: 'fechaNacimiento',
        render: (text, record) => {
          return text.format('DD-MM-YYYY');
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
