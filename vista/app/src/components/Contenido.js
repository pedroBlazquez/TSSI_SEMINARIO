import React, {Component} from 'react';

import {Card, Avatar} from 'antd';

const CardTitle = ({user}) => (
  <div className={'flex flex-space-between'}>
    <Avatar icon={'user'}/>
    <h2>{user.nombreFantasia}</h2>
  </div>
);

class Contenido extends Component {
  render () {
    const {user, children} = this.props;
    return (
      <Card
        className={'full-width margin-10p'}
        title={<CardTitle user={user}/>}
      >
        {children}
      </Card>
    );
  }
}

export default Contenido;
