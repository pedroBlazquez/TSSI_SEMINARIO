import React from 'react';
import { Select } from 'antd';
import {TIPOS_USUARIO, USUARIO_OYENTE} from '../utils/constants';

const Option = Select.Option;

export const TiposUsuario = (props) => (
  <Select {...props}>
    {TIPOS_USUARIO.map(u => <Option value={u.id} key={u.id}>{u.value}</Option>)}
  </Select>
);

export default TiposUsuario;
