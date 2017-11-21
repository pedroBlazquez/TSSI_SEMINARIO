import React from 'react';
import {Link} from 'react-router-dom'
import {Card} from 'antd';

export const ListaSeguidores = ({seguidores, title}) => (
  <Card className={'margin-10p'} title={title}>
  {seguidores.length ? 
    <ul>
      {seguidores.map(s => 
        <li>
          <Link to={`/perfil/${s.id}`}>
            {`${s.nombre}, ${s.apellido}`} 
          </Link>
        </li>
      )}
    </ul> :
    <p>{'No se encontró información'}</p>
  }
  </Card>
);

