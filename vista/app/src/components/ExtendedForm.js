import React, {Component} from 'react';
import { Form } from 'antd';

export class ExtendedForm extends Component {

  handleSubmit = (e) => {
    const {onSubmit, onFormValidationFail, form} = this.props;
    const {validateFields} = form;
    e.preventDefault();
    validateFields((errors, values) => {
      if (!errors) {
        onSubmit(values);
      } else {
        onFormValidationFail(errors);
      }
    })
  }

  render () {
    const {children, ...other} = this.props;
    return(
      <Form {...other} onSubmit={this.handleSubmit}>
        {children}
      </Form>
    )
  }
  
} 

const ExtendedFormHoc = (createParameters) => Form.create(createParameters)(ExtendedForm);

export const FormItem = Form.Item;

export default ExtendedFormHoc;
