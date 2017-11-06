import React, {Component} from 'react';
import { Select } from 'antd';
import {ROLES_INTEGRANTES} from '../utils/constants';

const Option = Select.Option;

class RolesIntegrante extends Component {
 render () {
  return (
    <Select defaultValue={ROLES_INTEGRANTES[0]} {...this.props}>
      {ROLES_INTEGRANTES.map(r => <Option value={r} key={r}>{r}</Option>)}
    </Select>
  );
 }
}

export default RolesIntegrante;
