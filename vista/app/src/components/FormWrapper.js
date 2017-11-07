import React from 'react';
import '../styles/Form.css';

const FormWrapper = ({error, success, title, children}) => (
  <div className="form-container">
    <h2 className="title">{title}</h2>
    {error  && <div className="error-container">{error}</div>}
    {success && <div className="success-container">{success}</div>}
    {children}
  </div>
);

export default FormWrapper;
