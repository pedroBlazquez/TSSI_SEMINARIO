import React from 'react';

export const MainContent = ({children, className, ...other}) => (
  <div className={`main-content ${className}`} {...other}>
    {children}
  </div>
);

export default MainContent;
