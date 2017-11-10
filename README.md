# TSSI_SEMINARIO
Trabajo práctico para materia "Seminario" de la carrera Tec.Sup. en Sistemas informáticos

# Instalación

### API
Para poder trabajar con la API va a ser necesario importar /api a la IDE y ejecutar el instalador de Maven. En Eclipse es:
* Click derecho sobre el proyecto
* Run As
* Maven Install

Una vez que el build es exitoso, abrir la consola sobre el directorio /api y correr el comando:
	`java -jar target/gs-rest-service-0.1.0.jar`

# Como trabajar con Git
Para poder enviar cambios al repositorio se deberá:
1. Crear un nuevo branch. `git checkout -b branch-name`
2. Seleccionar los archivos que se quieren enviar. `git add .` para seleccionar todo o `git add path/del/archivo`para seleccionar uno en particular.
3. Crear commit con los cambios. `git commit -m "Mensaje"`
4. Enviar los cambios. `git push origin branch-name`

# Endpoints

### ALBUMS

#### Obtener 1 album por id
* GET /albums/{idalbum} 
#### Obtener lista de albums de un artista
* GET /albums/getArtista/{idartista} 
#### Obtiene discos de un album
* GET /albums/getDiscos/{idalbum}
#### Crear album
* POST /albums/ 
* json:
"nombre":"nombre_Album",
"discos":[idDisco1,idDisco2]
#### Update album
* PUT /albums/
* json:
"idAlbum":"id_Album",
"nombre":"nombre_Album",
"discos":[idDisco1,idDisco2]
#### Delete album
* DELETE /albums/
* json:
"idAlbum":"id_Album"

### DISCOS
#### Obtener 1 disco por id
* GET /discos/{iddisco}
#### Obtener lista de discos de un artista
* GET /discos/getArtista/{idartista}
#### Obtener canciones de un disco
* GET /discos/getCanciones/{iddisco}
#### Crear disco (falta imagen)
* POST /discos/
* json:
"nombre":"nombre_disco",
"genero":"genero_disco",
"canciones":[idcancion1,idcancion2]
#### Update disco (falta imagen)
* PUT /discos/
* json:
"idDisco":"id_Disco",
"nombre":"nombre_disco",
"genero":"genero_disco",
"canciones":[idcancion1,idcancion2]
#### Delete disco
* DELETE /discos/
* json:
"idDisco":"id_Disco"

### CANCIONES
#### Obtener cancion por id
* GET /canciones/{idcancion}
#### Obtener lista de canciones de un artista
* GET /canciones/getArtista/{idartista}
#### Obtener discos de una cancion
* GET /canciones/getDiscos/{idcancion}
#### Crear cancion (falta audio e imagen)
* POST /canciones/
* json:
"nombre":"nombre_cancion",
"genero":"genero_cancion"
#### Update cancion (falta audio e imagen)
* PUT /canciones/
* json:
"idCancion":"id_Cancion",
"nombre":"nombre_cancion",
"genero":"genero_cancion"
#### Delete cancion
* DELETE /canciones/ (delete )
* json:
"idCancion":"id_Cancion"

### EVENTOS
#### Obtener evento por id
* GET /eventos/{idevento}
#### Obtener lista de eventos de un artista
* GET /eventos/getArtista/{idartista}
#### Crear Evento (costo => int, definir formato de fecha)
* POST /eventos/
* json:
"nombre":"nombre_evento",
"descripcion":"descripcion_evento",
"direccion":"direccion_evento",
"costo": costo_evento,
"fechaEvento":"fechaEvento"
#### Update Evento (costo => int, definir formato de fecha)
* PUT /eventos/ 
* json:
"idEvento":"id_Evento",
"nombre":"nombre_evento",
"descripcion":"descripcion_evento",
"direccion":"direccion_evento",
"costo": costo_evento,
"fechaEvento":"fechaEvento"
#### Delete Evento
* DELETE /eventos/
* json:
"idEvento":"id_Evento"

### Publicaciones
#### Obtener publicacion por id
* GET /publicaciones/{idpublicacion}
#### Obtener publicaciones de un artista
* GET /publicaciones/getArtista/{idartista}
#### Crear Publicacion
* POST /publicaciones/
* json:
"texto":"texto_publicacion"
#### Update Publicacion
* PUT /publicaciones/
* json:
"idPublicacion":"id_Publicacion",
"texto":"texto_publicacion"
#### Delete Publicacion
* DELETE /publicaciones/
* json:
"idPublicacion":"id_Publicacion"

### SEGUIDOS
#### Obtener lista de seguidos por un usuario (0 = usuario actual)
* GET /usuario/getSeguidos/{idUsuario}
#### Obtener lista de seguidores de un usuario (0 = usuario actual)
* GET /usuario/getSeguidores/{idUsuario}
#### Comprobar si usuario actual sigue a un usuario especifico
* GET /usuario/getSeguimiento/{idUsuario}
#### Crear/Borrar seguimiento de usuario actual a otro especifico (si existe lo borra, sino lo crea)
* POST /usuario/seguir
* json:
"idUsuario":"idUsuario_a_seguir"

### LISTAS DE REPRODUCCION
#### Obtener lista de reproduccion por su id
* GET /listas/{idlista}
#### Obtener listas de reproduccion (publicas y privadas) del usuario actual
* GET /listas/getUsuario
#### Obtener listas de reproduccion (publicas) de un usuario especifico
* GET /listas/getUsuario/{idusuario}
#### Obtener canciones de una lista
* GET /listas/getCanciones/{idlista}
#### Crear lista
* POST /listas/
* json:
"nombre":"nombre_lista",
"privacidad": true/false,
"canciones":[idcancion1,idcancion2]
#### Update lista
* PUT /listas/
* json:
"idListaReproduccion":"id_ListaReproduccion",
"nombre":"nombre_lista",
"privacidad": true/false,
"canciones":[idcancion1,idcancion2]
#### Update lista - Agregar Cancion
* PUT /listas/agregarCancion
* json:
"idListaReproduccion":"id_ListaReproduccion",
"idCancion":"id_Cancion"
#### Delete lista
* DELETE /listas/
* json:
"idListaReproduccion":"id_ListaReproduccion"

### LIKES
#### Obtener todos los objetos con like del usuario actual
* GET /like/getUserLikes
#### Comprobar like del usuario actual a un objeto especifico
##### (tipo_objeto = Album/Artista/Cancion/Disco/Evento/Publicacion)
* POST /like/getUserLike
* json:
"id":"id_objeto",
"tipo": "tipo_objeto" 
#### Cantidad de likes de un objeto especifico
##### (tipo_objeto = Album/Artista/Cancion/Disco/Evento/Publicacion)
* POST /like/getLikeCount
* json:
"id":"id_objeto",
"tipo": "tipo_objeto"
#### Crear/Borrar like del usuario actual a un objeto especifico (si existe lo borra, sino lo crea) 
##### (tipo_objeto = Album/Artista/Cancion/Disco/Evento/Publicacion)
* POST /like/
* json:
"id":"id_objeto",
"tipo": "tipo_objeto"

### COMPARTIR
#### Obtener todos los objetos compartidos por el usuario (0 = usuario actual)
GET /compartir/getCompartidos/{idUsuario}
#### Comprobar "compartir" del usuario actual a un objeto especifico 
##### (tipo_objeto = Album/Artista/Cancion/Disco/Evento/Publicacion)
* POST /compartir/getCompartidoUsuario
* json:
"id":"id_objeto",
"tipo": "tipo_objeto
#### Crear/Borrar "compartir" del usuario actual a un objeto especifico (si existe lo borra, sino lo crea) 
##### (tipo_objeto = Album/Artista/Cancion/Disco/Evento/Publicacion)
* POST /compartir/
* json:
"id":"id_objeto",
"tipo": "tipo_objeto"

### INICIO / NOVEDADES
#### Obtener novedades recomendadas para el usuario actual
GET /Inicio

### ARTISTA
#### Obtener artistas por su id
* GET /artistas/{idartista}

#### Obtener artistas por su id de Usuario
* GET /artistas/getbyUsuario/{idusuario}

### USUARIO
#### Obtener usuario por su id
* GET /usuario/{idusuario}

