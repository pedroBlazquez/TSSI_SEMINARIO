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
