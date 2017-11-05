import React from 'react';

const marginLeft = {marginLeft: 10};

export const InputWithIcon = ({input, icon}) => (
  <div className={'flex flex-space-between'}>
    {input}
  <div style={marginLeft}>{icon}</div>
</div>
);

export default InputWithIcon;
