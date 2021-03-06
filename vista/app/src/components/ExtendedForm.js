import React, {Component} from 'react';

export default function (FormElement) {

  return class ExtendedForm extends Component {

    handleSubmit = (e) => {
      const {onSubmit, onFormValidationFail, form} = this.props;
      const {validateFields} = form;
      e.preventDefault();
      e.stopPropagation();
      validateFields((errors, values) => {
        if (!errors) {
          onSubmit(e, values);
        } else {
          if (typeof onFormValidationFail === 'function') {
            onFormValidationFail(e, values, errors);
          }
        }
      })
    }

    render () {
      return <FormElement {...this.props} onSubmit={this.handleSubmit}/> 
    }
    
  }
}
