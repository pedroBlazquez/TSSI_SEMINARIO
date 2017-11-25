import React, {Component} from 'react';
import {  DatePicker } from 'antd';
import moment from 'moment';

class FechaEvento extends Component {
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
  return moment(date).isBefore(moment.now()); 
}

export default FechaEvento;