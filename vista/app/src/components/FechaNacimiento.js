import React, {Component} from 'react';
import {  DatePicker } from 'antd';
import moment from 'moment';

class FechaNacimiento extends Component {
  render () {
    return (
      <DatePicker
        {...this.props}
        disabledDate={checkDisabledTime}
      />
    );
  }
}

function checkDisabledTime (date) {
  return moment(date).isAfter(moment.now()); 
}

export default FechaNacimiento;
