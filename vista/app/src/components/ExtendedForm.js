import React, {Component} from 'react';

export default function (FormElement) {

  return class ExtendedForm extends Component {

    handleSubmit = (e) => {
      const {onSubmit, onFormValidationFail, form} = this.props;
      const {validateFields} = form;
      e.preventDefault();
      validateFields((errors, values) => {
        if (!errors) {
          onSubmit(values);
        } else {
          if (typeof onFormValidationFail === 'function') {
            onFormValidationFail(errors);
          }
        }
      })
    }

    render () {
      return <FormElement {...this.props} onSubmit={this.handleSubmit}/> 
    }
    
  }
}
