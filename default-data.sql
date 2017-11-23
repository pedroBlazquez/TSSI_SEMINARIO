
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
-- INSERT INTO generos(descripcion) VALUES("Electronica");
INSERT INTO generos(descripcion) VALUES("Rap");
INSERT INTO generos(descripcion) VALUES("Cumbia");
INSERT INTO generos(descripcion) VALUES("Reggaeton");
INSERT INTO generos(descripcion) VALUES("Heavy Metal");
-- INSERT INTO generos(descripcion) VALUES("Psychedelic");


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
insert into generosartista(idArtista,idGenero) values (1,6);


insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values ("Let it happen",1,"/MusicAppArchivos/25/canciones/Tame_Impala_-_Let_It_Happen[Mp3Converter.net].mp3","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values ("Nangs",1,"/MusicAppArchivos/25/canciones/Tame_Impala_-_Nangs_Psychedelic_Longer_Version[Mp3Converter.net].mp3","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values ("The moment",1,"/MusicAppArchivos/25/canciones/Tame_Impala_-_The_Moment_Music_Video[Mp3Converter.net].mp3","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values ("Yes I'm changing",1,"/MusicAppArchivos/25/canciones/Tame_Impala_-_Yes_im_changing[Mp3Converter.net].mp3","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values ("Eventually",1,"/MusicAppArchivos/25/canciones/TAME_IMPALA_-_Eventually[Mp3Converter.net].mp3","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values ("Gossip",1,"/MusicAppArchivos/25/canciones/Tame_Impala_-_Gossip[Mp3Converter.net].mp3","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values ("The less I know the better",1,"/MusicAppArchivos/25/canciones/Tame_Impala_The_Less_I_Know_The_Better_no_intro_Optional_Subs[Mp3Converter.net].mp3","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values ("Past life",1,"/MusicAppArchivos/25/canciones/Tame_impala_-_Past_life[Mp3Converter.net].mp3","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values ("Disciples",1,"/MusicAppArchivos/25/canciones/TAME_IMPALA_-_Disciples[Mp3Converter.net].mp3","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values ("Cause I'm a man",1,"/MusicAppArchivos/25/canciones/Tame_Impala_-_Cause_Im_A_Man[Mp3Converter.net].mp3","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values ("Reality in motion",1,"/MusicAppArchivos/25/canciones/Tame_Impala_-_Reality_In_Motion[Mp3Converter.net].mp3","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values ("Love/paranoia",1,"/MusicAppArchivos/25/canciones/tame_impala-loveparanoia[Mp3Converter.net].mp3","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values ("New person, same old mistakes",1,"/MusicAppArchivos/25/canciones/Tame_Impala_-_New_Person_Same_Old_Mistakes[Mp3Converter.net].mp3","2015-01-01");



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
insert into generosartista(idArtista,idGenero) values (2,6);


insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values ("Welcome to the Jungle",2,"/MusicAppArchivos/26/canciones/Guns_N_Roses_-_Welcome_To_The_Jungle[Mp3Converter.net].mp3","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"It's So Easy",2,"/MusicAppArchivos/26/canciones/Guns_N_Roses_-_Its_So_Easy_Live_Recording[Mp3Converter.net].mp3","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"Nightrain",2,"/MusicAppArchivos/26/canciones/Guns_n_Roses_Night_Train[Mp3Converter.net].mp3","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"Out ta Get Me",2,"/MusicAppArchivos/26/canciones/Out_Ta_Get_Me_Appetite_For_Destruction_Guns_N_Roses[Mp3Converter.net].mp3","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"Mr. Brownstone",2,"/MusicAppArchivos/26/canciones/Guns_N_Roses_-_Mr_Brownstone[Mp3Converter.net].mp3","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values ("Paradise City",2,"/MusicAppArchivos/26/canciones/Guns_N_Roses_-_Paradise_City[Mp3Converter.net].mp3","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"My Michelle",2,"/MusicAppArchivos/26/canciones/My_Michelle_Appetite_For_Destruction_Guns_N_Roses[Mp3Converter.net].mp3","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"Think About You",2,"/MusicAppArchivos/26/canciones/Think_About_You_Appetite_For_Destruction_Guns_N_Roses[Mp3Converter.net].mp3","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"Sweet Child o' Mine",2,"/MusicAppArchivos/26/canciones/Guns_N_Roses_-_Sweet_Child_O_Mine[Mp3Converter.net].mp3","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"You're Crazy",2,"/MusicAppArchivos/26/canciones/Youre_Crazy_Appetite_For_Destruction_Guns_N_Roses[Mp3Converter.net].mp3","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"Anything Goes",2,"/MusicAppArchivos/26/canciones/Anything_Goes_Appettite_For_Destruction_Guns_N_Roses[Mp3Converter.net].mp3","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"Rocket Queen",2,"/MusicAppArchivos/26/canciones/Rocket_Queen_Appetite_For_Destruction_Guns_N_Roses[Mp3Converter.net].mp3","2015-01-01");

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
insert into generosartista(idArtista,idGenero) values (3,6);


insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values ("Desnudo Para Siempre(Despedazado por Mil Partes)",3,"/MusicAppArchivos/27/canciones/01_Desnudo_para_siempre_o_despedazado_por_mil_partes_Despedazado_por_mil_partes_-_La_Renga[Mp3Converter.net].mp3","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"A La Carga Mi Rocanrol",3,"/MusicAppArchivos/27/canciones/02_A_la_carga_mi_rocanrol_Despedazado_por_mil_partes_-_La_Renga[Mp3Converter.net].mp3","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"El Final Es En Donde Partí",3,"/MusicAppArchivos/27/canciones/El_final_es_en_donde_partí-La_Renga_letra[Mp3Converter.net].mp3","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"La Balada del Diablo y La Muerte",3,"/MusicAppArchivos/27/canciones/La_balada_del_diablo_y_la_muerte[Mp3Converter.net].mp3","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"Cuándo Vendrán",3,"/MusicAppArchivos/27/canciones/05_Cuando_vendrán_Despedazado_por_mil_partes_-_La_Renga[Mp3Converter.net].mp3","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values ("Psilocybe Mexicana",3,"/MusicAppArchivos/27/canciones/La_Renga_-_Psilocybe_Mexicana[Mp3Converter.net].mp3","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"Paja Brava",3,"/MusicAppArchivos/27/canciones/La_Renga_-_07_Paja_Brava_Despedazado_Por_Mil_Partes_1996[Mp3Converter.net].mp3","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"Lo Frágil de La Locura",3,"/MusicAppArchivos/27/canciones/Lo_frágil_de_la_locura_-_La_Renga_con_letra[Mp3Converter.net].mp3","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"Veneno",3,"/MusicAppArchivos/27/canciones/Veneno_La_Renga[Mp3Converter.net].mp3","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"El Viento Que Todo Empuja",3,"/MusicAppArchivos/27/canciones/La_Renga_-_10_El_Viento_Que_Todo_Empuja_Despedazado_Por_Mil_Partes_1996[Mp3Converter.net].mp3","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"Hablando de La Libertad",3,"/MusicAppArchivos/27/canciones/La_Renga_-_Hablando_de_la_libertad[Mp3Converter.net].mp3","2015-01-01");


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


insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values ("Right Next Door to Hell",2,"/MusicAppArchivos/26/canciones/Right_Next_Door_to_Hell_lyrics_Guns_N_Roses[Mp3Converter.net].mp3","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"Dust N' Bones",2,"/MusicAppArchivos/26/canciones/Guns_N_Roses_-_Dust_N_Bones[Mp3Converter.net].mp3","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"Live and Let Die" ,2,"/MusicAppArchivos/26/canciones/Guns_N_Roses-Live_and_Let_Die_wLyrics[Mp3Converter.net].mp3","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"Don't Cry",2,"/MusicAppArchivos/26/canciones/Guns_N_Roses_-_Dont_Cry[Mp3Converter.net].mp3","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"Perfect Crime",2,"/MusicAppArchivos/26/canciones/Guns_N_Roses_-_Perfect_Crime[Mp3Converter.net].mp3","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values ("You Ain't the First",2,"/MusicAppArchivos/26/canciones/Guns_N_Roses_-_You_aint_the_first_original[Mp3Converter.net].mp3","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"Bad Obsession",2,"/MusicAppArchivos/26/canciones/Guns_N_Roses-Bad_Obsession_wLyrics[Mp3Converter.net].mp3","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"Back Off Bitch",2,"/MusicAppArchivos/26/canciones/Guns_N_Roses_-_Back_Off_Bitch[Mp3Converter.net].mp3","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"Double Talkin' Jive",2,"/MusicAppArchivos/26/canciones/Guns_N_Roses_-_Double_Talkin_Jive[Mp3Converter.net].mp3","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"November Rain",2,"/MusicAppArchivos/26/canciones/Guns_N_Roses-November_Rain_wLyrics[Mp3Converter.net].mp3","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"The Garden",2,"/MusicAppArchivos/26/canciones/Guns_N_Roses-The_Garden_wLyrics_feat_Alice_Cooper[Mp3Converter.net].mp3","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"Garden of Eden",2,"/MusicAppArchivos/26/canciones/Guns_N_Roses_-_Garden_Of_Eden[Mp3Converter.net].mp3","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"Don't Damn Me",2,"/MusicAppArchivos/26/canciones/Guns_N_Roses-Dont_Damn_Me_wLyrics[Mp3Converter.net].mp3","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"Bad Apples",2,"/MusicAppArchivos/26/canciones/Guns_N_Roses_-_Bad_Apples[Mp3Converter.net].mp3","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"Dead Horse",2,"/MusicAppArchivos/26/canciones/Guns_N_Roses-Dead_Horse_wLyrics[Mp3Converter.net].mp3","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"Coma",2,"/MusicAppArchivos/26/canciones/Guns_N_Roses_-_Coma[Mp3Converter.net].mp3","2015-01-01");

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



insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"Civil War",2,"/MusicAppArchivos/26/canciones/GnR_Civil_War[Mp3Converter.net].mp3","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"14 Years",2,"/MusicAppArchivos/26/canciones/Guns_N_Roses-14_Years_wLyrics[Mp3Converter.net].mp3","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"Yesterdays",2,"/MusicAppArchivos/26/canciones/Guns_N_Roses_-_Yesterdays[Mp3Converter.net].mp3","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"Knockin' on Heaven's Door",2,"/MusicAppArchivos/26/canciones/Guns_N_Roses_-_Knockin_On_Heavens_Door[Mp3Converter.net].mp3","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"Get in the Ring",2,"/MusicAppArchivos/26/canciones/Get_in_the_Ring_Guns_n_Roses[Mp3Converter.net].mp3","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values ("Shotgun Blues",2,"/MusicAppArchivos/26/canciones/Guns_N_Roses_-_Shotgun_Blues[Mp3Converter.net].mp3","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"Breakdown",2,"/MusicAppArchivos/26/canciones/Guns_N_Roses_-_Breakdown[Mp3Converter.net].mp3","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"Pretty Tied Up",2,"/MusicAppArchivos/26/canciones/Guns_N_Roses_-_Pretty_Tied_Up[Mp3Converter.net].mp3","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"Locomotive",2,"/MusicAppArchivos/26/canciones/Guns_N_Roses_-_Locomotive_-_Lyrics[Mp3Converter.net].mp3","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"So Fine",2,"/MusicAppArchivos/26/canciones/Guns_N_Roses_-_So_Fine[Mp3Converter.net].mp3","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"Estranged",2,"/MusicAppArchivos/26/canciones/Guns_N_Roses-Estranged_wLyrics[Mp3Converter.net].mp3","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"You Could Be Mine",2,"/MusicAppArchivos/26/canciones/Guns_N_Roses-You_Could_Be_Mine_wLyrics[Mp3Converter.net].mp3","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"Don't Cry",2,"/MusicAppArchivos/26/canciones/Guns_N_Roses_-_Dont_Cry[Mp3Converter.net].mp3","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values (	"My World",2,"/MusicAppArchivos/26/canciones/MY_WORLD-_GUNS_N_ROSES[Mp3Converter.net].mp3","2015-01-01");

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

insert into generosartista(idArtista,idGenero) values (4,1);


insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values ("Mistakes I've Made",4,"/MusicAppArchivos/28/canciones/Eelke_Kleijn_-_Mistakes_Ive_Made_Original_Mix[Mp3Converter.net].mp3","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values ("A Tale Of 2 Lovers",4,"/MusicAppArchivos/28/canciones/Eelke_Kleijn_-_A_Tale_Of_Two_Lovers[Mp3Converter.net].mp3","2015-01-01");
insert into canciones(nombre, idArtista, archivo,fechaPublicacion) values ("45 Billion Years",4,"/MusicAppArchivos/28/canciones/Eelke_Kleijn_–_45_Billion_Years_Original_Mix[Mp3Converter.net].mp3","2015-01-01");








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

insert into generoscancion (idCancion,idGenero) select a.idCancion, 1 from canciones a; -- -cambiar genero
insert into generosdisco (idDisco,idGenero) select a.idDisco, 1 from discos a; -- -cambiar genero






-- seguidores------------------------------------------------------------------------------
insert into seguidos (idSeguido, idSeguidor) values (1,2);
insert into seguidos (idSeguido, idSeguidor) values (1,3);
insert into seguidos (idSeguido, idSeguidor) values (1,4);
insert into seguidos (idSeguido, idSeguidor) values (1,5);
insert into seguidos (idSeguido, idSeguidor) values (1,6);
insert into seguidos (idSeguido, idSeguidor) values (1,7);
insert into seguidos (idSeguido, idSeguidor) values (2,8);
insert into seguidos (idSeguido, idSeguidor) values (2,9);
insert into seguidos (idSeguido, idSeguidor) values (2,10);
insert into seguidos (idSeguido, idSeguidor) values (3,11);
insert into seguidos (idSeguido, idSeguidor) values (3,12);
insert into seguidos (idSeguido, idSeguidor) values (3,13);
insert into seguidos (idSeguido, idSeguidor) values (3,14);
insert into seguidos (idSeguido, idSeguidor) values (4,15);
insert into seguidos (idSeguido, idSeguidor) values (4,16);
insert into seguidos (idSeguido, idSeguidor) values (4,17);
insert into seguidos (idSeguido, idSeguidor) values (5,18);
insert into seguidos (idSeguido, idSeguidor) values (5,19);
insert into seguidos (idSeguido, idSeguidor) values (5,20);
insert into seguidos (idSeguido, idSeguidor) values (6,21);
insert into seguidos (idSeguido, idSeguidor) values (6,22);
insert into seguidos (idSeguido, idSeguidor) values (6,23);
insert into seguidos (idSeguido, idSeguidor) values (7,24);
insert into seguidos (idSeguido, idSeguidor) values (7,25);
insert into seguidos (idSeguido, idSeguidor) values (7,26);
insert into seguidos (idSeguido, idSeguidor) values (8,27);
insert into seguidos (idSeguido, idSeguidor) values (8,20);
insert into seguidos (idSeguido, idSeguidor) values (9,2);
insert into seguidos (idSeguido, idSeguidor) values (9,3);
insert into seguidos (idSeguido, idSeguidor) values (9,4);
insert into seguidos (idSeguido, idSeguidor) values (10,5);
insert into seguidos (idSeguido, idSeguidor) values (10,6);
insert into seguidos (idSeguido, idSeguidor) values (10,7);
insert into seguidos (idSeguido, idSeguidor) values (11,8);
insert into seguidos (idSeguido, idSeguidor) values (11,9);
insert into seguidos (idSeguido, idSeguidor) values (11,10);
insert into seguidos (idSeguido, idSeguidor) values (12,11);
insert into seguidos (idSeguido, idSeguidor) values (13,12);
insert into seguidos (idSeguido, idSeguidor) values (14,13);
insert into seguidos (idSeguido, idSeguidor) values (1,14);
insert into seguidos (idSeguido, idSeguidor) values (2,15);
insert into seguidos (idSeguido, idSeguidor) values (3,16);
insert into seguidos (idSeguido, idSeguidor) values (6,19);
insert into seguidos (idSeguido, idSeguidor) values (7,20);
insert into seguidos (idSeguido, idSeguidor) values (8,21);
insert into seguidos (idSeguido, idSeguidor) values (9,22);
insert into seguidos (idSeguido, idSeguidor) values (10,23);
insert into seguidos (idSeguido, idSeguidor) values (11,24);
insert into seguidos (idSeguido, idSeguidor) values (12,25);
insert into seguidos (idSeguido, idSeguidor) values (13,26);
insert into seguidos (idSeguido, idSeguidor) values (14,27);
insert into seguidos (idSeguido, idSeguidor) values (15,20);
insert into seguidos (idSeguido, idSeguidor) values (17,2);
insert into seguidos (idSeguido, idSeguidor) values (18,3);
insert into seguidos (idSeguido, idSeguidor) values (19,4);
insert into seguidos (idSeguido, idSeguidor) values (20,5);
insert into seguidos (idSeguido, idSeguidor) values (21,6);
insert into seguidos (idSeguido, idSeguidor) values (22,7);
insert into seguidos (idSeguido, idSeguidor) values (23,8);
insert into seguidos (idSeguido, idSeguidor) values (24,9);
insert into seguidos (idSeguido, idSeguidor) values (25,10);
insert into seguidos (idSeguido, idSeguidor) values (26,11);
insert into seguidos (idSeguido, idSeguidor) values (27,12);
insert into seguidos (idSeguido, idSeguidor) values (28,13);
insert into seguidos (idSeguido, idSeguidor) values (28,14);
insert into seguidos (idSeguido, idSeguidor) values (1,15);
insert into seguidos (idSeguido, idSeguidor) values (2,16);
insert into seguidos (idSeguido, idSeguidor) values (13,17);
insert into seguidos (idSeguido, idSeguidor) values (14,19);
insert into seguidos (idSeguido, idSeguidor) values (16,21);
insert into seguidos (idSeguido, idSeguidor) values (17,22);
insert into seguidos (idSeguido, idSeguidor) values (18,23);
insert into seguidos (idSeguido, idSeguidor) values (19,24);
insert into seguidos (idSeguido, idSeguidor) values (21,25);
insert into seguidos (idSeguido, idSeguidor) values (21,26);
insert into seguidos (idSeguido, idSeguidor) values (21,27);
insert into seguidos (idSeguido, idSeguidor) values (21,20);
insert into seguidos (idSeguido, idSeguidor) values (21,2);
insert into seguidos (idSeguido, idSeguidor) values (21,3);
insert into seguidos (idSeguido, idSeguidor) values (22,4);
insert into seguidos (idSeguido, idSeguidor) values (23,5);
insert into seguidos (idSeguido, idSeguidor) values (23,6);
insert into seguidos (idSeguido, idSeguidor) values (23,7);
insert into seguidos (idSeguido, idSeguidor) values (23,9);
insert into seguidos (idSeguido, idSeguidor) values (23,10);
insert into seguidos (idSeguido, idSeguidor) values (24,11);
insert into seguidos (idSeguido, idSeguidor) values (24,12);
insert into seguidos (idSeguido, idSeguidor) values (24,13);
insert into seguidos (idSeguido, idSeguidor) values (25,14);
insert into seguidos (idSeguido, idSeguidor) values (25,15);
insert into seguidos (idSeguido, idSeguidor) values (25,16);
insert into seguidos (idSeguido, idSeguidor) values (25,17);
insert into seguidos (idSeguido, idSeguidor) values (25,18);
insert into seguidos (idSeguido, idSeguidor) values (26,19);
insert into seguidos (idSeguido, idSeguidor) values (26,20);
insert into seguidos (idSeguido, idSeguidor) values (26,21);
insert into seguidos (idSeguido, idSeguidor) values (26,22);
insert into seguidos (idSeguido, idSeguidor) values (27,23);
insert into seguidos (idSeguido, idSeguidor) values (27,24);
insert into seguidos (idSeguido, idSeguidor) values (27,25);
insert into seguidos (idSeguido, idSeguidor) values (27,26);
insert into seguidos (idSeguido, idSeguidor) values (28,27);
insert into seguidos (idSeguido, idSeguidor) values (27,20);




-- ----------------------------------------------------------------------------------------



