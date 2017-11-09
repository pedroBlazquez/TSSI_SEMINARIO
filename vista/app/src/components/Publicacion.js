import React, {Component} from 'react';

import {Card, Avatar} from 'antd';

const CardTitle = ({user}) => (
  <div className={'flex flex-space-between'}>
    <Avatar icon={'user'}/>
    <h2>{user.nombreFantasia}</h2>
  </div>
);

class Publicacion extends Component {
  render () {
    const {user, children} = this.props;
    return (
      <Card
        className={'margin-10p'}
        title={<CardTitle user={user}/>}
      >
        {children}
      </Card>
    );
  }
}

export default Publicacion;
