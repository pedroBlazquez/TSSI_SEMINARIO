import React, {Component} from 'react';
import { Checkbox } from 'antd';

import {GENEROS} from '../utils/constants';

const CheckboxGroup = Checkbox.Group;

const checkboxOptions = GENEROS.map(g => ({label: g, value: g}));

class GenerosMusicales extends Component {
 render () {
  return (
    <CheckboxGroup options={checkboxOptions} {...this.props}/>
  );
 }
}

export default GenerosMusicales;
