import React from 'react';
import {  DatePicker } from 'antd';
import moment from 'moment';

export const FechaNacimiento = (props) => (
  <DatePicker
    {...props}
    disabledDate={checkDisabledTime}
    placeholder={'Fecha de nacimiento'}
  />
);

function checkDisabledTime (date) {
  return moment(date).isAfter(moment.now()); 
}

export default FechaNacimiento;
