import React from 'react';
import {Icon, Tooltip} from 'antd';

import '../styles/icons.css';

export const InfoTooltip = (props) => (
  <Tooltip {...props}>
    <Icon type={'info-circle'} className={'icon-info'}/>
  </Tooltip>
);

export default InfoTooltip;
