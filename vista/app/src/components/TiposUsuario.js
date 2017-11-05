import React from 'react';
import { Select } from 'antd';
import {TIPOS_USUARIO, USUARIO_OYENTE} from '../utils/constants';

const Option = Select.Option;

export const TiposUsuario = (props) => (
  <Select defaultValue={USUARIO_OYENTE} {...props}>
    {TIPOS_USUARIO.map(u => <Option value={u} key={u}>{u}</Option>)}
  </Select>
);

export default TiposUsuario;
