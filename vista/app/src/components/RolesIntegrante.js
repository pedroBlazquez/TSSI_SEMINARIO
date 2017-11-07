import React, {Component} from 'react';
import { Select } from 'antd';
import {ROLES_INTEGRANTES} from '../utils/constants';

const Option = Select.Option;

class RolesIntegrante extends Component {

  render () {
    return (
      <Select {...this.props} >
        {ROLES_INTEGRANTES.map(r => <Option value={r.id} key={r.id}>{r.value}</Option>)}
      </Select>
    );
 }
}

export default RolesIntegrante;
