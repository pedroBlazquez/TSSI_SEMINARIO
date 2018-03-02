import React, {Component} from 'react';
import { Checkbox, Select } from 'antd';

import {GENEROS} from '../utils/constants';

const CheckboxGroup = Checkbox.Group;
const Option = Select.Option;

const checkboxOptions = GENEROS.map(g => ({label: g.value, value: g.id}));

class GenerosMusicales extends Component {
 render () {
  return (
    <CheckboxGroup options={checkboxOptions} {...this.props}/>
  );
 }
}

export class GenerosMusicalesDD extends Component {
  render () {
    const {value, ...other} = this.props;
    return (
      <Select {...other} defaultValue={value.toString()} placeholder="Seleccione un genero musical">
        {GENEROS.map(g => <Option value={g.id.toString()} key={g.id}>{g.value}</Option>)}
      </Select>
    );
  }
}

export default GenerosMusicales;
