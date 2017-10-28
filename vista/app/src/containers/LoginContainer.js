import React, {Component} from 'react';
import LoginForm from '../components/LoginForm';

class LoginContainer extends Component {
  constructor (props) {
    super(props);
    this.state = {
      usuario: {
        value: ''
      },
      password: {
        value: ''
      }
    }
  }

  onFormChange = (changedFields) => {
    this.setState({...this.state, ...changedFields});
  }

  onSubmit = (e) => {
    e.preventDefault();
    console.log(e);
  }

  render () {
    const {error} = this.props;
    const {usuario, password} = this.state;
    return (
      <LoginForm
        usuario={usuario}
        password={password}
        onChange={this.onFormChange}
        onSubmit={this.onSubmit}
        error={error}
      />
    );
  }

} 

export default LoginContainer;
