USE db_musicapp;

INSERT INTO usuariotipos(descripcion) VALUES("Oyente");
INSERT INTO usuariotipos(descripcion) VALUES("Artista");
INSERT INTO usuariotipos(descripcion) VALUES("Banda");

INSERT INTO integranteroles(descripcion) VALUES("Vocalista");
INSERT INTO integranteroles(descripcion) VALUES("Gutarrista");
INSERT INTO integranteroles(descripcion) VALUES("Bajista");
INSERT INTO integranteroles(descripcion) VALUES("Baterista");

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
insert into usuarios(nombre,apellido,estado,fechaAlta,fechaNacimiento,mail,password,idUsuarioTipo) values ("Kevin","Parker",1, now(), "1995-03-27", "kevinparker@hotmail.com","asd123",2);



-- HACER ESTO POR CADA ARTISTA ---------------------------------------------------

insert into artistas(descripcion,fechaInicio,nombreFantasia,idUsuario) values ("Tame Impala es una banda de rock psicodélico y rock espacial originaria de Perth, Australia, formada por Kevin Parker en el año 2007.","2007-01-01","Tame Impala", 25);

insert into integrantesartista(nombres,apellido,fechaNacimiento, idArtista, idRol) values ("Kevin","Parker","1995-03-27",1,1);

insert into generosartista(idArtista,idGenero) values (1,1);
insert into generosartista(idArtista,idGenero) values (1,8);

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


