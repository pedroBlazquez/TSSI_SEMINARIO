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

### Endpoints

# ALBUMS

GET /albums/{idalbum} (Obtiene 1 album por id)

GET /albums/getArtista/{idartista} (Obtiene lista de albums de un artista)

GET /albums/getDiscos/{idalbum} (Obtiene discos de un album)

POST /albums/ (crear album)
json:
"nombre":"nombre_Album",
"discos":[idDisco1,idDisco2]

PUT /albums/ (update album)
json:
"idAlbum":"id_Album",
"nombre":"nombre_Album",
"discos":[idDisco1,idDisco2]

DELETE /albums/ (delete album)
json:
"idAlbum":"id_Album"

# DISCOS

GET /discos/{iddisco} (Obtiene 1 disco por id)

GET /discos/getArtista/{idartista} (Obtiene lista de discos de un artista)

GET /discos/getCanciones/{iddisco} (Obtiene canciones de un disco)

POST /discos/ (crear )
json:
"nombre":"nombre_disco",
"genero":"genero_disco",
"canciones":[idcancion1,idcancion2]

PUT /discos/ (update )
json:
"idDisco":"id_Disco",
"nombre":"nombre_disco",
"genero":"genero_disco",
"canciones":[idcancion1,idcancion2]

DELETE /discos/ (delete )
json:
"idDisco":"id_Disco"

# CANCIONES

GET /canciones/{idcancion} (Obtiene 1 cancion por id)

GET /canciones/getArtista/{idartista} (Obtiene lista de canciones de un artista)

GET /canciones/getDiscos/{idcancion} (Obtiene discos de una cancion)

POST /canciones/ (crear )
json:
"nombre":"nombre_cancion",
"genero":"genero_cancion"

PUT /canciones/ (update )
json:
"idCancion":"id_Cancion",
"nombre":"nombre_cancion",
"genero":"genero_cancion"

DELETE /canciones/ (delete )
json:
"idCancion":"id_Cancion"

# EVENTOS

GET /eventos/{idevento} (Obtiene 1 evento por id)

GET /eventos/getArtista/{idartista} (Obtiene lista de eventos de un artista)

POST /eventos/ (crear )
json:
"nombre":"nombre_evento",
"descripcion":"descripcion_evento",
"direccion":"direccion_evento",
"costo": costo_evento,    (Int)
"fechaEvento":"fechaEvento"    (hay que definir el formato de fecha)

PUT /eventos/ (update )
json:
"idEvento":"id_Evento",
"nombre":"nombre_evento",
"descripcion":"descripcion_evento",
"direccion":"direccion_evento",
"costo": costo_evento,    (Int)
"fechaEvento":"fechaEvento"    (hay que definir el formato de fecha)

DELETE /eventos/ (delete )
json:
"idEvento":"id_Evento"

# Publicaciones

GET /publicaciones/{idpublicacion} (Obtiene 1 publicacion por id)

GET /publicaciones/getArtista/{idartista} (Obtiene lista de publicaciones de un artista)

POST /publicaciones/ (crear )
json:
"texto":"texto_publicacion"

PUT /publicaciones/ (update )
json:
"idPublicacion":"id_Publicacion",
"texto":"texto_publicacion"

DELETE /publicaciones/ (delete )
json:
"idPublicacion":"id_Publicacion"

# SEGUIDOS

GET /usuario/getSeguidos/{idUsuario} (Obtiene lista de seguidos por un usuario, 0 = usuario actual)

GET /usuario/getSeguidores/{idUsuario} (Obtiene lista de seguidores de un usuario, 0 = usuario actual)

GET /usuario/getSeguimiento/{idUsuario} (Comprueba si el usuario actual sigue a un usuario especifico)

POST /usuario/seguir (crear/borrar seguimiento del usuario actual a otro especifico, si existe lo borra, sino lo crea)
json:
"idUsuario":"idUsuario_a_seguir"
