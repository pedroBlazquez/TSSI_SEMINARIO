USE db_musicapp;



INSERT INTO usuariotipos(descripcion) VALUES("Oyente");
INSERT INTO usuariotipos(descripcion) VALUES("Artista");
INSERT INTO usuariotipos(descripcion) VALUES("Banda");

INSERT INTO integranteroles(descripcion) VALUES("Vocalista");
INSERT INTO integranteroles(descripcion) VALUES("Gutarrista");
INSERT INTO integranteroles(descripcion) VALUES("Bajista");
INSERT INTO integranteroles(descripcion) VALUES("Baterista");
INSERT INTO integranteroles(descripcion) VALUES("DJ");

INSERT INTO generos(descripcion) VALUES("Rock");
INSERT INTO generos(descripcion) VALUES("Pop");
INSERT INTO generos(descripcion) VALUES("Electronica");
INSERT INTO generos(descripcion) VALUES("Rap");
INSERT INTO generos(descripcion) VALUES("Cumbia");
INSERT INTO generos(descripcion) VALUES("Reggaeton");
INSERT INTO generos(descripcion) VALUES("Heavy Metal");
INSERT INTO generos(descripcion) VALUES("Psychedelic");


insert into usuarios(nombre,apellido,estado,fechaAlta,fechaNacimiento,mail,password,idUsuarioTipo) values ("Christian","Laino",1, now(), "1995-03-27", "christianlaino@hotmail.com","asd123",1);
insert into usuarios(nombre,apellido,estado,fechaAlta,fechaNacimiento,mail,password,idUsuarioTipo) values ("DANIEL","GUTIERREZ",1, now(), "1995-03-27", "DANIEL@hotmail.com","asd123",1);
insert into usuarios(nombre,apellido,estado,fechaAlta,fechaNacimiento,mail,password,idUsuarioTipo) values ("MIGUELVICENTE","AGURTO",1, now(), "1995-03-27", "MIGUELVICENTE@hotmail.com","asd123",1);
insert into usuarios(nombre,apellido,estado,fechaAlta,fechaNacimiento,mail,password,idUsuarioTipo) values ("JORGE ","HERRERA",1, now(), "1995-03-27", "HERRERA@hotmail.com","asd123",1);
insert into usuarios(nombre,apellido,estado,fechaAlta,fechaNacimiento,mail,password,idUsuarioTipo) values ("JORGE ","HUAMANI",1, now(), "1995-03-27", "HUAMANI@hotmail.com","asd123",1);
insert into usuarios(nombre,apellido,estado,fechaAlta,fechaNacimiento,mail,password,idUsuarioTipo) values ("JORGE ","HUARCAYA",1, now(), "1995-03-27", "HUARCAYA@hotmail.com","asd123",1);
insert into usuarios(nombre,apellido,estado,fechaAlta,fechaNacimiento,mail,password,idUsuarioTipo) values ("JAVIER","HUAYTAN",1, now(), "1995-03-27", "HUAYTAN@hotmail.com","asd123",1);
insert into usuarios(nombre,apellido,estado,fechaAlta,fechaNacimiento,mail,password,idUsuarioTipo) values ("JAVIER","LLAJA",1, now(), "1995-03-27", "LLAJA@hotmail.com","asd123",1);
insert into usuarios(nombre,apellido,estado,fechaAlta,fechaNacimiento,mail,password,idUsuarioTipo) values ("JAVIER","MALDONADO",1, now(), "1995-03-27", "MALDONADO@hotmail.com","asd123",1);
insert into usuarios(nombre,apellido,estado,fechaAlta,fechaNacimiento,mail,password,idUsuarioTipo) values ("GUILLERMO","MAGUIÑA",1, now(), "1995-03-27", "MAGUIÑA@hotmail.com","asd123",1);
insert into usuarios(nombre,apellido,estado,fechaAlta,fechaNacimiento,mail,password,idUsuarioTipo) values ("GUILLERMO","MARTINEZ",1, now(), "1995-03-27", "MARTINEZ@hotmail.com","asd123",1);
insert into usuarios(nombre,apellido,estado,fechaAlta,fechaNacimiento,mail,password,idUsuarioTipo) values ("GUILLERMO","MEDINA",1, now(), "1995-03-27", "MEDINA@hotmail.com","asd123",1);
insert into usuarios(nombre,apellido,estado,fechaAlta,fechaNacimiento,mail,password,idUsuarioTipo) values ("DORIS","PARDAVE",1, now(), "1995-03-27", "PARDAVE@hotmail.com","asd123",1);
insert into usuarios(nombre,apellido,estado,fechaAlta,fechaNacimiento,mail,password,idUsuarioTipo) values ("DORIS","RIEGA",1, now(), "1995-03-27", "RIEGA@hotmail.com","asd123",1);
insert into usuarios(nombre,apellido,estado,fechaAlta,fechaNacimiento,mail,password,idUsuarioTipo) values ("DORIS","ROBLES",1, now(), "1995-03-27", "ROBLES@hotmail.com","asd123",1);
insert into usuarios(nombre,apellido,estado,fechaAlta,fechaNacimiento,mail,password,idUsuarioTipo) values ("ROBERTO","ROSALES",1, now(), "1995-03-27", "ROSALES@hotmail.com","asd123",1);
insert into usuarios(nombre,apellido,estado,fechaAlta,fechaNacimiento,mail,password,idUsuarioTipo) values ("ROBERTO","SOLANO",1, now(), "1995-03-27", "SOLANO@hotmail.com","asd123",1);
insert into usuarios(nombre,apellido,estado,fechaAlta,fechaNacimiento,mail,password,idUsuarioTipo) values ("ROBERTO","TENORIO",1, now(), "1995-03-27", "TENORIO@hotmail.com","asd123",1);
insert into usuarios(nombre,apellido,estado,fechaAlta,fechaNacimiento,mail,password,idUsuarioTipo) values ("ROBERTO","TORRES",1, now(), "1995-03-27", "TORRES@hotmail.com","asd123",1);
insert into usuarios(nombre,apellido,estado,fechaAlta,fechaNacimiento,mail,password,idUsuarioTipo) values ("PATRICIA","TRUJILLO",1, now(), "1995-03-27", "TRUJILLO@hotmail.com","asd123",1);
insert into usuarios(nombre,apellido,estado,fechaAlta,fechaNacimiento,mail,password,idUsuarioTipo) values ("PATRICIA","VERA",1, now(), "1995-03-27", "VERA@hotmail.com","asd123",1);
insert into usuarios(nombre,apellido,estado,fechaAlta,fechaNacimiento,mail,password,idUsuarioTipo) values ("PATRICIA","VILCA ",1, now(), "1995-03-27", "VILCA@hotmail.com","asd123",1);
insert into usuarios(nombre,apellido,estado,fechaAlta,fechaNacimiento,mail,password,idUsuarioTipo) values ("PATRICIA","ZEGARRA",1, now(), "1995-03-27", "ZEGARRA@hotmail.com","asd123",1);
insert into usuarios(nombre,apellido,estado,fechaAlta,fechaNacimiento,mail,password,idUsuarioTipo) values ("PATRICIA","ZU",1, now(), "1995-03-27", "ZU@hotmail.com","asd123",1);
insert into usuarios(nombre,apellido,estado,fechaAlta,fechaNacimiento,mail,password,idUsuarioTipo) values ("Kevin","Parker",1, now(), "1995-03-27", "kevinparker@hotmail.com","asd123",3);
insert into usuarios(nombre,apellido,estado,fechaAlta,fechaNacimiento,mail,password,idUsuarioTipo) values ("Axl","Rose",1, now(), "1995-03-27", "axlsitoxD@hotmail.com","asd123",3);
insert into usuarios(nombre,apellido,estado,fechaAlta,fechaNacimiento,mail,password,idUsuarioTipo) values ("Chizzo","Napoli",1, now(), "1995-03-27", "chizito@hotmail.com","asd123",3);
insert into usuarios(nombre,apellido,estado,fechaAlta,fechaNacimiento,mail,password,idUsuarioTipo) values ("Eelke","Kleijn",1, now(), "1995-03-27", "eelke@hotmail.com","asd123",2);



-- HACER ESTO POR CADA ARTISTA ---------------------------------------------------

insert into artistas(descripcion,fechaInicio,nombreFantasia,idUsuario) values ("Tame Impala es una banda de rock psicodélico y rock espacial originaria de Perth, Australia, formada por Kevin Parker en el año 2007.","2007-01-01","Tame Impala", 25);

insert into integrantesartista(nombres,apellido,fechaNacimiento, idArtista, idRol) values ("Kevin","Parker","1995-03-27",1,1);

insert into generosartista(idArtista,idGenero) values (1,1);
insert into generosartista(idArtista,idGenero) values (1,7);


insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values ("Let it happen",1,"/path","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values ("Nangs",1,"/path","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values ("The moment",1,"/path","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values ("Yes I'm changing",1,"/path","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values ("Eventually",1,"/path","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values ("Gossip",1,"/path","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values ("The less I know the better",1,"/path","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values ("Past life",1,"/path","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values ("Disciples",1,"/path","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values ("Cause I'm a man",1,"/path","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values ("Reality in motion",1,"/path","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values ("Love/paranoia",1,"/path","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values ("New person, same old mistakes",1,"/path","2015-01-01");



insert into discos(fechaPublicacion,nombre,idArtista) values  ("2015-01-01","Currents",1);


insert into cancionesdisco(idDisco,idCancion) values (1,1);
insert into cancionesdisco(idDisco,idCancion) values (1,2);
insert into cancionesdisco(idDisco,idCancion) values (1,3);
insert into cancionesdisco(idDisco,idCancion) values (1,4);
insert into cancionesdisco(idDisco,idCancion) values (1,5);
insert into cancionesdisco(idDisco,idCancion) values (1,6);
insert into cancionesdisco(idDisco,idCancion) values (1,7);
insert into cancionesdisco(idDisco,idCancion) values (1,8);
insert into cancionesdisco(idDisco,idCancion) values (1,9);
insert into cancionesdisco(idDisco,idCancion) values (1,10);
insert into cancionesdisco(idDisco,idCancion) values (1,11);
insert into cancionesdisco(idDisco,idCancion) values (1,12);
insert into cancionesdisco(idDisco,idCancion) values (1,13);

-- HACER ESTO POR CADA ARTISTA ---------------------------------------------------

insert into artistas(descripcion,fechaInicio,nombreFantasia,idUsuario) values ("Guns N' Roses es una banda estadounidense de hard rock formada en Hollywood (Los Ángeles, California) en 1985. El grupo fue fundado por Axl Rose y el guitarrista rítmico Izzy Stradlin.","1985-01-01","Guns N'Roses", 26);

insert into integrantesartista(nombres,apellido,fechaNacimiento, idArtista, idRol) values ("Axl","Rose","1995-03-27",1,1);

insert into generosartista(idArtista,idGenero) values (2,1);
insert into generosartista(idArtista,idGenero) values (2,8);


insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values ("Welcome to the Jungle",2,"/path","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"It's So Easy",2,"/path","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"Nightrain",2,"/path","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"Out ta Get Me",2,"/path","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"Mr. Brownstone",2,"/path","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values ("Paradise City",2,"/path","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"My Michelle",2,"/path","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"Think About You",2,"/path","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"Sweet Child o' Mine",2,"/path","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"You're Crazy",2,"/path","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"Anything Goes",2,"/path","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"Rocket Queen",2,"/path","2015-01-01");

insert into discos(fechaPublicacion,nombre,idArtista) values  ("1987-01-01","Appetite for Destruction",2);

insert into cancionesdisco(idDisco,idCancion) values (2,14);
insert into cancionesdisco(idDisco,idCancion) values (2,15);
insert into cancionesdisco(idDisco,idCancion) values (2,16);
insert into cancionesdisco(idDisco,idCancion) values (2,17);
insert into cancionesdisco(idDisco,idCancion) values (2,18);
insert into cancionesdisco(idDisco,idCancion) values (2,19);
insert into cancionesdisco(idDisco,idCancion) values (2,20);
insert into cancionesdisco(idDisco,idCancion) values (2,21);
insert into cancionesdisco(idDisco,idCancion) values (2,22);
insert into cancionesdisco(idDisco,idCancion) values (2,23);
insert into cancionesdisco(idDisco,idCancion) values (2,24);
insert into cancionesdisco(idDisco,idCancion) values (2,25);







-- LA RENGA ---------------------------------------------------

insert into artistas(descripcion,fechaInicio,nombreFantasia,idUsuario) values ("La Renga es una banda de hard rock formada el 31 de diciembre de 1988, en el barrio porteño de Mataderos, al oeste de la ciudad de Buenos Aires.","1990-01-01","La Renga", 27);

insert into integrantesartista(nombres,apellido,fechaNacimiento, idArtista, idRol) values ("Chizzo","Napoli","1995-03-27",3,1);

insert into generosartista(idArtista,idGenero) values (3,1);
insert into generosartista(idArtista,idGenero) values (3,8);


insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values ("Desnudo Para Siempre(Despedazado por Mil Partes)",3,"/path","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"A La Carga Mi Rocanrol",3,"/path","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"El Final Es En Donde Partí",3,"/path","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"La Balada del Diablo y La Muerte",3,"/path","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"Cuándo Vendrán",3,"/path","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values ("Psilocybe Mexicana",3,"/path","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"Paja Brava",3,"/path","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"Lo Frágil de La Locura",3,"/path","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"Veneno",3,"/path","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"El Viento Que Todo Empuja",3,"/path","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"Hablando de La Libertad",3,"/path","2015-01-01");


insert into discos(fechaPublicacion,nombre,idArtista) values  ("1987-01-01","Despedazado por Mil Partes",3);

insert into cancionesdisco(idDisco,idCancion) values (3,26);
insert into cancionesdisco(idDisco,idCancion) values (3,27);
insert into cancionesdisco(idDisco,idCancion) values (3,28);
insert into cancionesdisco(idDisco,idCancion) values (3,29);
insert into cancionesdisco(idDisco,idCancion) values (3,30);
insert into cancionesdisco(idDisco,idCancion) values (3,31);
insert into cancionesdisco(idDisco,idCancion) values (3,32);
insert into cancionesdisco(idDisco,idCancion) values (3,33);
insert into cancionesdisco(idDisco,idCancion) values (3,34);
insert into cancionesdisco(idDisco,idCancion) values (3,35);
insert into cancionesdisco(idDisco,idCancion) values (3,36);

-- ---------------------------------------------------------------------


insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values ("Right Next Door to Hell",2,"/path","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"Dust N' Bones",2,"/path","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"Live and Let Die" ,2,"/path","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"Don't Cry",2,"/path","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"Perfect Crime",2,"/path","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values ("You Ain't the First",2,"/path","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"Bad Obsession",2,"/path","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"Back Off Bitch",2,"/path","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"Double Talkin' Jive",2,"/path","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"November Rain",2,"/path","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"The Garden",2,"/path","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"Garden of Eden",2,"/path","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"Don't Damn Me",2,"/path","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"Bad Apples",2,"/path","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"Dead Horse",2,"/path","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"Coma",2,"/path","2015-01-01");

insert into discos(fechaPublicacion,nombre,idArtista) values  ("1987-01-01","Use your illussion I",2);

insert into cancionesdisco(idDisco,idCancion) values (2,37);
insert into cancionesdisco(idDisco,idCancion) values (2,38);
insert into cancionesdisco(idDisco,idCancion) values (2,39);
insert into cancionesdisco(idDisco,idCancion) values (2,40);
insert into cancionesdisco(idDisco,idCancion) values (2,41);
insert into cancionesdisco(idDisco,idCancion) values (2,42);
insert into cancionesdisco(idDisco,idCancion) values (2,43);
insert into cancionesdisco(idDisco,idCancion) values (2,44);
insert into cancionesdisco(idDisco,idCancion) values (2,45);
insert into cancionesdisco(idDisco,idCancion) values (2,46);
insert into cancionesdisco(idDisco,idCancion) values (2,47);
insert into cancionesdisco(idDisco,idCancion) values (2,48);
insert into cancionesdisco(idDisco,idCancion) values (2,49);
insert into cancionesdisco(idDisco,idCancion) values (2,50);
insert into cancionesdisco(idDisco,idCancion) values (2,51);
insert into cancionesdisco(idDisco,idCancion) values (2,52);



insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"Civil War",2,"/path","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"14 Years",2,"/path","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"Yesterdays",2,"/path","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"Knockin' on Heaven's Door",2,"/path","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"Get in the Ring",2,"/path","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values ("Shotgun Blues",2,"/path","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"Breakdown",2,"/path","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"Pretty Tied Up",2,"/path","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"Locomotive",2,"/path","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"So Fine",2,"/path","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"Estranged",2,"/path","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"You Could Be Mine",2,"/path","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"Don't Cry",2,"/path","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"My World",2,"/path","2015-01-01");

insert into discos(fechaPublicacion,nombre,idArtista) values  ("1987-01-01","Use Your Illusion II",2);

insert into cancionesdisco(idDisco,idCancion) values (2,53);
insert into cancionesdisco(idDisco,idCancion) values (2,54);
insert into cancionesdisco(idDisco,idCancion) values (2,55);
insert into cancionesdisco(idDisco,idCancion) values (2,56);
insert into cancionesdisco(idDisco,idCancion) values (2,57);
insert into cancionesdisco(idDisco,idCancion) values (2,58);
insert into cancionesdisco(idDisco,idCancion) values (2,59);
insert into cancionesdisco(idDisco,idCancion) values (2,60);
insert into cancionesdisco(idDisco,idCancion) values (2,61);
insert into cancionesdisco(idDisco,idCancion) values (2,62);
insert into cancionesdisco(idDisco,idCancion) values (2,63);
insert into cancionesdisco(idDisco,idCancion) values (2,64);
insert into cancionesdisco(idDisco,idCancion) values (2,65);
insert into cancionesdisco(idDisco,idCancion) values (2,66);



insert into albums(fechaPublicacion,nombre,idArtista) values (now(),"Use Your Illusion", 2);
insert into discosalbum(idAlbum,idDisco) values (1,4);
insert into discosalbum(idAlbum,idDisco) values (1,5);







-- HACER ESTO POR CADA ARTISTA ---------------------------------------------------

insert into artistas(descripcion,fechaInicio,nombreFantasia,idUsuario) values ("Eelke Kleijn es un DJ de minimal/techno nacido en Holanda.","2007-01-01","Eelke Kleijn", 28);

insert into integrantesartista(nombres,apellido,fechaNacimiento, idArtista, idRol) values ("Eelke","Kleijn","1995-03-27",1,5);

insert into generosartista(idArtista,idGenero) values (1,3);


insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values ("Mistakes I've Made",4,"/path","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values ("A Tale Of 2 Lovers",4,"/path","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values ("Welcome to Orion",4,"/path","2015-01-01");








-- publicaciones ---------------------------------------------------------------------------------------------------------------------------

insert into publicaciones (fechaPublicacion,texto,idArtista) values (now(),"how's it going my perris?", 1);
insert into publicaciones (fechaPublicacion,texto,idArtista) values (now(),"very excited to be here Kappa", 1);

-- listas ------------------------------------------------------------------------------------------------------------------------------

insert into listasreproduccion(estado,fechaAlta,nombre,privacidad,idUsuario) values (1,now(),"lista1",1,1);
insert into listasreproduccion(estado,fechaAlta,nombre,privacidad,idUsuario) values (1,now(),"lista2",0,1);

insert into cancioneslista(idLista,idCancion) values (1,1);
insert into cancioneslista(idLista,idCancion) values (1,2);
insert into cancioneslista(idLista,idCancion) values (1,3);
insert into cancioneslista(idLista,idCancion) values (1,4);
insert into cancioneslista(idLista,idCancion) values (1,5); 

insert into cancioneslista(idLista,idCancion) values (2,6);
insert into cancioneslista(idLista,idCancion) values (2,7);
insert into cancioneslista(idLista,idCancion) values (2,8);
insert into cancioneslista(idLista,idCancion) values (2,9);
insert into cancioneslista(idLista,idCancion) values (2,10);

-- eventos--------------------------------------------------------------------------------------------------------------------------------------

insert into eventos(costo, descripcion,direccion,fechaEvento,fechaPublicacion,nombre,idArtista) values (400,"Recital de Tame Imapala","Juncal 15900", "2018-02-02",now(),"Awakening",1);

-- --------------------------------------------------------------------------------------------------------------------------------------


