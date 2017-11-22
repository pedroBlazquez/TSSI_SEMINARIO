import React, {Component} from 'react';
import {Icon} from 'antd';

class Reproductor extends Component {
  render () {
    return (
      <div className={'relative'} style={{maxWidth: 340, height: 30, backgroundColor: '#ffffff'}}>
        <div className={'absolute'} style={{fontSize: 17, top: 5, left: 0, color: '#BABABA', cursor: 'pointer'}}>
          <Icon type="step-backward" />
        </div>
        <div className={'absolute'} style={{left: 17}}>
          <audio controls></audio>
        </div>
        <div className={'absolute'} style={{fontSize: 17, top: 5, right: 0, color: '#BABABA', cursor: 'pointer'}} >
          <Icon type="step-forward"/>
        </div>
      </div>
    )
  }
}

export default Reproductor;
