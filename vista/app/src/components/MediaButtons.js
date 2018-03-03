import React from 'react';

import Compartir from './Compartir';
import Like from './Like';
import BotonPlay from './BotonPlay';
import Agregar from './AgregarCancionLista';
import { OBJECT_TYPES } from '../utils/constants';

import '../styles/MediaButtons.css';

const MediaButtons = ({play = false, agregar = false, share = true, like = true, typeContent, content}) =>  (
  <div className={'flex container'}>
    {play && typeContent === OBJECT_TYPES.CANCION &&
      <BotonPlay size={'small'} cancion={{...content, artista: content.artista}}/>
    }
    {agregar && typeContent === OBJECT_TYPES.CANCION &&
      <div>
        <Agregar 
          cancion={content}
          style={{width: 20, height: 20}}
        />
      </div>
    }
    {share && 
      <div>
      <Compartir
        id={content.id}
        shared={content.compartido}
        typeContent={typeContent}
        size={'small'}
      />
      </div>
    }
    {like &&
      <div>
        <Like
          id={content.id}
          likes={content.likes}
          isLiked={content.liked}
          typeContent={typeContent}
          size={'small'}
        />
      </div>
    }
  </div>
);

export default MediaButtons;
