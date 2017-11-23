import React, {Component} from 'react';
import {Icon} from 'antd';

class Reproductor extends Component {

  componentDidMount () {
    this.refs.reproductor.addEventListener('ended', () => {
      this.onNext();
    })
  }

  componentDidUpdate (prevProps) {
    if(prevProps.track !== this.props.track) {
      this.refs.reproductor.play();
    }
  }

  stopPlay = () => {
    this.refs.reproductor.pause();
    this.refs.reproductor.currentTime = 0;
  }

  onNext = () => {
    this.props.next();
  }

  onPrevious = () => {
    this.props.previous();
  }

  render () {
    const {track} = this.props;
    return (
      <div className={'relative'} style={{maxWidth: 360}}>
        <div className={'absolute'} onClick={this.onPrevious} style={{fontSize: 17, top: 5, left: 0, color: '#BABABA', cursor: 'pointer'}}>
          <Icon type="step-backward"/>
        </div>
        <div className={'absolute'} style={{left: 17}}>
          <audio ref={'reproductor'} src={track ? track.path : ''} type={'audio/mpeg'} controls></audio>        </div>
        <div className={'absolute'} onClick={this.onNext} style={{fontSize: 17, top: 5, right: 0, color: '#BABABA', cursor: 'pointer'}} >
          <Icon type="step-forward"/>
        </div>
        <div className={'absolute'} onClick={this.onNext} style={{fontSize: 17, top: 5, right: 20, color: '#BABABA', cursor: 'pointer'}} >
          <Icon type="step-forward"/>
        </div>
        <div className={'absolute'} onClick={this.stopPlay} style={{fontSize: 17, top: 5, right: 0, color: '#BABABA', cursor: 'pointer'}}>
          <Icon type="minus-square" />
        </div>
      </div>
    )
  }
}

class ReproductorContainer extends Component {
  render () {
    const {style, artista, ...reproductor} = this.props;
    return (
      <div style={{backgroundColor: '#FAFAFA', width: 'inherit', height: 40, ...style}}>
        <div className={'relative'}>
          <Reproductor {...reproductor}/>
          <div className={'absolute'} style={{left: 380, top: 0}}>
            <div><strong>Artista</strong></div>
            <div>Cancion</div>
          </div>  
        </div>
      </div>
    );
  }
}

export default ReproductorContainer;