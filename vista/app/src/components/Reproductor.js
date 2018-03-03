import React, {Component} from 'react';
import {Icon} from 'antd';
import MenuColaReproduccion from './MenuColaReproduccion';

class Reproductor extends Component {

  componentDidMount () {
    this.refs.reproductor.addEventListener('ended', () => {
      this.onNext();
    });

    this.refs.reproductor.addEventListener('pause', () => {
      this.props.estaReproduciendo(false);
    });

    this.refs.reproductor.addEventListener('play', () => {
      this.props.estaReproduciendo(true);
    });
  }

  componentDidUpdate (prevProps) {
    const {estado, ultimoPlay, ultimaPausa, cola, track} = this.props;
    if (prevProps.cola !== cola && !cola.length) {
      this.refs.reproductor.src = '';
    } if(prevProps.track !== track) {
      this.stopPlay();
      this.refs.reproductor.play();
    } else {
      if(prevProps.ultimoPlay !== ultimoPlay) {
        this.refs.reproductor.play();
      }
      if (prevProps.ultimaPausa !== ultimaPausa) {
        this.refs.reproductor.pause();
      }
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
          <audio ref={'reproductor'} src={track ? track.archivo : ''} type={'audio/mpeg'} controls></audio>        </div>
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
      <div style={{backgroundColor: '#FAFAFA', width: 580, height: 40, ...style}}>
        <div className={'relative'}>
          <Reproductor {...reproductor}/>
          <div className={'absolute'} style={{width: 180, left: 380, top: 0}}>
            <div className={'flex'}>
              <div>
                <div className={'ellipsis'} style={{width: 160}}><strong>{artista && artista.nombreFantasia}</strong></div>
                <div className={'ellipsis'} style={{width: 160}}>{reproductor.track && reproductor.track.nombre}</div>
              </div>
              <div className={'margin-10p'}>
                <MenuColaReproduccion canciones={reproductor.cola} playing={reproductor.track} />
              </div>
            </div>
          </div>  
        </div>
      </div>
    );
  }
}


export default ReproductorContainer;
