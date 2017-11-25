import React from 'react';

const center = {
    display: 'block',
    margin: '0 auto',
    width: '85%',
    border: '1px black solid',
}

const center2 = {
    display: 'block',
    margin: '0 auto',
    width: '50%',
    border: '1px black solid',
}

const center3 = {
    display: 'block',
    margin: '0 auto',
    width: '25%',
    border: '1px black solid',
}

export const ManualDeUsuario = () => (
<div style={{backgroundColor: 'white'}}>
<h1>Indice</h1>
<ul>
    <li>
            <h2>Login</h2>
    </li>
    <li>
            <h2>Home</h2>
        </li>
        <li>
                <h2>Perfil</h2>
            </li>
            <li>
                    <h2>Administrar contenido</h2>
                </li>
           
            </ul>





<h1>Login</h1>
<br /><br />
        <img src="https://puu.sh/yjjbD/7f76fc4e71.png"  style={center} />
<br /><br />
<p>o registrar un nuevo usuario.</p>
<br />
<br /><br />
<img src="https://puu.sh/yjnjh/067bb2f220.png" style={center} />
<p>Aca elegiremos el tipo de cuenta que crearemos, ya sea oyente, artista o banda.</p>
<br />
<p>En caso de ser artista o banda, nos pedirá datos adicionales</p>
<br />
<img src="https://puu.sh/ysKFt/8799aa44c2.png" style={center} />
<h1>Home</h1>
<br />
<p>Al ingresar seremos re-dirigidos a la pagina principal, la cual nos mostrara todas las novedades.</p>
<br />
<p>En caso de ser una nueva cuenta, no tendremos novedades hasta empezar a seguir a otros usuarios o artistas.</p>
<img src="https://puu.sh/yt2Ik/9836156195.png" style={center} />
<br />
<p>En la pantalla principal encontraremos el buscador con el cual podremos buscar canciones, discos, albumes, artistas y eventos.</p>
<img src="https://puu.sh/yt2vD/43b92e8bf8.png" style={center2} />
<h1>Perfil</h1>
<br />
<p>Al hacer click en nuestro mail podremos ingresar a nuestro perfil.</p>
<br />
<img src="https://puu.sh/yt2Tk/050b2414d5.png" style={{display:'block', margin:'0 auto'}} height="100px" width="250px" />
<br />

Aqui podremos consultar la informacion del usuario, editarla y crear nuevas publicaciones o administrar el contenido.

<br/>

<img src="https://puu.sh/yt2FL/dae6de4b25.png" style={center} />

<br />

<h1>Seguidores/seguidos</h1>


Para seguir a un usuario o artista podemos darle like en su perfil haciendo click en el corazon.

<img src="https://puu.sh/yt2PI/0371f021d9.png" class="center3" />



En esta seccion podremos ver que usuarios seguimos / nos siguen
<img src="https://puu.sh/yt2Cm/65cc9526ae.png" style={center} />



<h1>Administrar contenido</h1>

<br />

Dentro de administrar contenido podremos dar de alta canciones, discos, albumes y eventos.

<img src="https://puu.sh/ysLQq/6856a1ea99.png" style={center} />

<br />


<img src="https://puu.sh/ysLJm/92ad0bfad5.png"   style={center2} />
<br />
<img src="https://puu.sh/ysO1m/465b7e5d38.png"   style={center2}    />
<br />
<img src="https://puu.sh/ysOwQ/6fa2dcd65c.png"   style={center2}    />
</div>
)

export default ManualDeUsuario;
