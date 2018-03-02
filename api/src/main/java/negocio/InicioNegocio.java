package negocio;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import aplicacion.Tools;
import conexion.Conexion;
import modelos.Artista;
import modelos.Cancion;

public class InicioNegocio {
    
    public static List<JSONObject> getNovedades(Conexion cn,String usermail)
    {
        try
        {
            //Conexion cn = new Conexion();
            //cn.abrirConexion();
            
            //obtengo usuario actual
            Integer usuario = UsuarioNegocio.getIdUsuarioByMail(cn, usermail);
            String id_artista = "";
            List<Artista> res_artista = cn.getListQuery("from modelos.Artista WHERE usuario.id = "+usuario);
            if(!res_artista.isEmpty())
                id_artista = String.valueOf(res_artista.get(0).getId()); 
            
            //creo lista para devolver
            List<JSONObject> return_list = new ArrayList<JSONObject>();
            //lista de prioridad (aparece primero)
            List<JSONObject> top1_list = new ArrayList<JSONObject>();
            //lista secundaria
            List<JSONObject> top2_list = new ArrayList<JSONObject>();

            List<JSONObject> top3_list = new ArrayList<JSONObject>();
            
            //obtengo artistas seguidos
            List<Integer> artistas_seguidos = cn.getListQuery(
                "select s.idSeguidos.seguido.artista.id from modelos.Seguidos s " + 
                "WHERE s.idSeguidos.seguidor.id = "+usuario+
                " and s.idSeguidos.seguido.id != " + usuario +
                " and s.idSeguidos.seguido.usuarioTipo.id != " + 1); //CAMBIAR NUMERO ACORDE AL USUARIO TIPO OYENTE
            

            //fecha actual
            String date_now = Tools.DateFormatter(Tools.GetDateDifference(1));
            //fecha desde la cual se toman novedades
            String date_novedades = Tools.DateFormatter(Tools.GetDateDifference(7));

            StringBuilder query_artistas_seguidos = new StringBuilder();
            StringBuilder query_artistas_genero = new StringBuilder();

            Set<Integer> generos1_set = new TreeSet<Integer>();
            
            if(!artistas_seguidos.isEmpty())
            {
                for (Integer seguido : artistas_seguidos)
                    query_artistas_seguidos.append(seguido).append(",");
                query_artistas_seguidos.deleteCharAt(query_artistas_seguidos.length() - 1);
                
                
                //NOVEDADES DE ARTISTAS SEGUIDOS
                top1_list.addAll(CancionNegocio.setData(cn,cn.getListQuery("from Cancion c JOIN FETCH c.artista ar WHERE ar.id in ("+query_artistas_seguidos+") and c.fechaPublicacion > '"+date_novedades+"'"+" order by fechaPublicacion desc"), usermail,true,true));
                top1_list.addAll(DiscoNegocio.setData(cn,cn.getListQuery("from Disco d JOIN FETCH d.artista ar WHERE ar.id in ("+query_artistas_seguidos+") and d.fechaPublicacion > '"+date_novedades+"'"+" order by fechaPublicacion desc"), usermail,false,true));
                top1_list.addAll(AlbumNegocio.setData(cn,cn.getListQuery("from Album a JOIN FETCH a.artista ar WHERE ar.id in ("+query_artistas_seguidos+") and a.fechaPublicacion > '"+date_novedades+"'"+" order by fechaPublicacion desc"), usermail,false,true));
                top1_list.addAll(PublicacionNegocio.setData(cn,cn.getListQuery("from Publicacion p JOIN FETCH p.artista ar WHERE ar.id in ("+query_artistas_seguidos+") and p.fechaPublicacion > '"+date_novedades+"'"+" order by fechaPublicacion desc"), usermail,true));
                top1_list.addAll(EventoNegocio.setData(cn,cn.getListQuery("from Evento e JOIN FETCH e.artista ar WHERE ar.id in ("+query_artistas_seguidos+") and e.fechaEvento > '"+date_now+"' and (e.fechaPublicacion > '"+date_novedades+"' or e.fechaEvento < '"+Tools.DateFormatter(Tools.GetDateDifference(-7))+"') order by fechaPublicacion desc"), usermail,true));

                //--------------------------------------------------------------------
                //NOVEDADES DE GENEROS DE ARTISTAS SEGUIDOS + SUGERENCIA DE NUEVOS ARTISTAS DE ESTOS GENEROS
                
                generos1_set.addAll(cn.getListQuery("select distinct ga.idGeneroArtista.genero.id from GeneroArtista ga "
                            + "WHERE ga.idGeneroArtista.artista.id IN ("+query_artistas_seguidos+")"
                            +" order by ga.idGeneroArtista.genero.id"));
                
            }
            
            String filtro_artista_noseayo ="";
            if(!id_artista.equals(""))
                filtro_artista_noseayo = " and l.accion.cancion.artista.id != "+id_artista;
            List<Integer> cancioneslike_list = cn.getListQuery("select distinct l.accion.cancion.id FROM Like l WHERE l.usuario.id = "+usuario+" and l.accion.cancion != null"+filtro_artista_noseayo);
            
            if(!cancioneslike_list.isEmpty())
            {    //GET GENEROS FROM CANCIONES LIKEADAS
                
                StringBuilder query_cancioneslike = new StringBuilder();
                for (Integer c : cancioneslike_list)
                    query_cancioneslike.append(c).append(",");
                if(query_cancioneslike != null)
                    query_cancioneslike.deleteCharAt(query_cancioneslike.length() - 1);
                
                generos1_set.addAll(cn.getListQuery("select distinct ga.idGeneroCancion.genero.id from GeneroCancion ga "
                        + "WHERE ga.idGeneroCancion.cancion.id IN ("+query_cancioneslike+")"
                        +" order by ga.idGeneroCancion.genero.id"));
            }
            
            if(!generos1_set.isEmpty())
            {
                //lleno query para filtrar por generos que le gustan al usuario
                
                StringBuilder query_in_generos = new StringBuilder();
                for (Integer g : generos1_set)
                    query_in_generos.append(g).append(",");
                query_in_generos.deleteCharAt(query_in_generos.length() - 1);
                
                int max_result = 10;
                

                String filtro = "";
                if(!artistas_seguidos.isEmpty())
                    filtro = "and ar.id not in ("+query_artistas_seguidos+") "; 
                if(!id_artista.equals(""))
                    filtro = "and ar.id != "+id_artista+" "; //Y artista NO SEA YO
                
                //busco novedades para Discos y Canciones de los generos que le gustan al usuario
                top2_list.addAll(CancionNegocio.setData(cn,cn.getListQuery("select distinct gc.idGeneroCancion.cancion from GeneroCancion gc JOIN FETCH gc.idGeneroCancion.cancion.artista ar WHERE  gc.idGeneroCancion.genero.id in ("+query_in_generos+") "+filtro+" and gc.idGeneroCancion.cancion.fechaPublicacion > '"+date_novedades+"'"+" order by fechaPublicacion desc",max_result ), usermail,true,true));
                top2_list.addAll(DiscoNegocio.setData(cn,cn.getListQuery("select distinct gd.idGeneroDisco.disco from GeneroDisco gd JOIN FETCH gd.idGeneroDisco.disco.artista ar WHERE  gd.idGeneroDisco.genero.id in ("+query_in_generos+") "+filtro+" and gd.idGeneroDisco.disco.fechaPublicacion > '"+date_novedades+"'"+" order by fechaPublicacion desc",max_result ), usermail,false,true));
                
                if(!artistas_seguidos.isEmpty())
                    filtro = " and ga.idGeneroArtista.artista.id not in ("+query_artistas_seguidos+")";
                if(!id_artista.equals(""))
                        filtro = "and ga.idGeneroArtista.artista.id != "+id_artista+" "; //Y artista NO SEA YO
                
                //obtengo artistas del genero que le gustan al usuario, pero que no sigue
                List<Artista> artistas_genero = cn.getListQuery("select distinct ga.idGeneroArtista.artista from GeneroArtista ga WHERE ga.idGeneroArtista.genero.id in ("+query_in_generos+") " +filtro);
                if(!artistas_genero.isEmpty())
                {
                    //armo query para filtrar por artistas de los generos
                    for (Artista a : artistas_genero)
                        query_artistas_genero.append(a.getId()).append(",");
                    query_artistas_genero.deleteCharAt(query_artistas_genero.length() - 1);
                    
                    //busco novedades para Album, Publicacion y Evento, de artistas del genero, que no sean seguidos por el usuario
                    top2_list.addAll(AlbumNegocio.setData(cn,cn.getListQuery("from Album a JOIN FETCH a.artista ar WHERE ar.id in ("+query_artistas_genero+") and a.fechaPublicacion > '"+date_novedades+"'"+" order by fechaPublicacion desc",max_result ), usermail,false,true));
                    top2_list.addAll(PublicacionNegocio.setData(cn,cn.getListQuery("from Publicacion p JOIN FETCH p.artista ar WHERE ar.id in ("+query_artistas_genero+") and p.fechaPublicacion > '"+date_novedades+"'"+" order by fechaPublicacion desc",max_result ), usermail,true));
                    top2_list.addAll(EventoNegocio.setData(cn,cn.getListQuery("from Evento e JOIN FETCH e.artista ar WHERE ar.id in ("+query_artistas_genero+") and e.fechaEvento > '"+date_now+"' and (e.fechaPublicacion > '"+date_novedades+"' or e.fechaEvento < '"+Tools.DateFormatter(Tools.GetDateDifference(-7))+"')  order by fechaPublicacion desc",max_result ), usermail,true));
                    
                    //agrego aleatoriamente 10 artistas que de los generos que le gustan al usuario
                    Collections.shuffle(artistas_genero);
                    int cant_artistas_genero = artistas_genero.size(); 
                    if(cant_artistas_genero>(max_result-1))
                        cant_artistas_genero = max_result;
                    List<Artista> list_artistas_sugeridos = artistas_genero.subList(0,cant_artistas_genero);
                    top2_list.addAll(ArtistaNegocio.setData(cn,list_artistas_sugeridos, usermail,false,true));
                }
            }
            
            
            //--------------------------------------------------------------------
            //NOVEDADES DE ARTISTAS SEGUIDOS POR LOS USUARIOS SEGUIDOS, (que no se los siga directamente y que no sean de los generos de los seguidos directamente)

            //obtengo usuarios seguidos por los usuarios que sigo
            List<Integer> usuarios_seguidos = cn.getListQuery(
                "select s.idSeguidos.seguido.id from modelos.Seguidos s " + 
                "WHERE s.idSeguidos.seguidor.id = "+usuario+
                " and s.idSeguidos.seguido.id != "+usuario); //y seguido no sea yo
            if(!usuarios_seguidos.isEmpty())
            {   
                //armo query para traer seguidos por los usuarios que sigo
                StringBuilder query_usuarios_seguidos = new StringBuilder();
                for (Integer seguido : usuarios_seguidos)
                    query_usuarios_seguidos.append(seguido).append(",");
                query_usuarios_seguidos.deleteCharAt(query_usuarios_seguidos.length() - 1);
                
                String query = "select distinct s.idSeguidos.seguido.artista from modelos.Seguidos s " + 
                        "WHERE s.idSeguidos.seguidor.id IN ("+query_usuarios_seguidos+") "
                                + " and s.idSeguidos.seguido.id != "+usuario; //Y SEGUIDO NO SEA YO (afecta a artistas, ya que solo trae este tipo de objeto)
                
                if(!query_artistas_seguidos.toString().equals(""))
                    query += " and s.idSeguidos.seguido.artista.id not in ("+query_artistas_seguidos+")";
                if(!query_artistas_genero.toString().equals(""))
                    query += " and s.idSeguidos.seguido.artista.id not in ("+query_artistas_genero+")";
                
                List<Artista> seguidos_usuarios_seguidos = cn.getListQuery(query);
                
                if(!seguidos_usuarios_seguidos.isEmpty())
                {
                    //armo query para buscar por los artistas seguidos de los usuarios que sigo
                    StringBuilder query_seguidos_usuarios_seguidos = new StringBuilder();
                    for (Artista artista : seguidos_usuarios_seguidos)
                        query_seguidos_usuarios_seguidos.append(artista.getId()).append(",");
                    query_seguidos_usuarios_seguidos.deleteCharAt(query_seguidos_usuarios_seguidos.length() - 1);
                    
                    
                    int max_result = 3;
                    
                    //busco novedades para los artistas seguidos por mis seguidos
                    top3_list.addAll(CancionNegocio.setData(cn,cn.getListQuery("from Cancion c JOIN FETCH c.artista ar WHERE ar.id in ("+query_seguidos_usuarios_seguidos+") and c.fechaPublicacion > '"+date_novedades+"'"+" order by fechaPublicacion desc",max_result), usermail,true,true));
                    top3_list.addAll(DiscoNegocio.setData(cn,cn.getListQuery("from Disco d JOIN FETCH d.artista ar WHERE ar.id in ("+query_seguidos_usuarios_seguidos+") and d.fechaPublicacion > '"+date_novedades+"'"+" order by fechaPublicacion desc",max_result), usermail,false,true));
                    top3_list.addAll(AlbumNegocio.setData(cn,cn.getListQuery("from Album a JOIN FETCH a.artista ar WHERE ar.id in ("+query_seguidos_usuarios_seguidos+") and a.fechaPublicacion > '"+date_novedades+"'"+" order by fechaPublicacion desc",max_result), usermail,false,true));
                    top3_list.addAll(PublicacionNegocio.setData(cn,cn.getListQuery("from Publicacion p JOIN FETCH p.artista ar WHERE ar.id in ("+query_seguidos_usuarios_seguidos+") and p.fechaPublicacion > '"+date_novedades+"'"+" order by fechaPublicacion desc",max_result), usermail,true));
                    top3_list.addAll(EventoNegocio.setData(cn,cn.getListQuery("from Evento e JOIN FETCH e.artista ar WHERE ar.id in ("+query_seguidos_usuarios_seguidos+") and e.fechaEvento > '"+date_now+"' and (e.fechaPublicacion > '"+date_novedades+"' or e.fechaEvento < '"+Tools.DateFormatter(Tools.GetDateDifference(-7))+"')  order by fechaPublicacion desc",max_result), usermail,true));
                    
                    
                    
                    Collections.shuffle(seguidos_usuarios_seguidos);
                    int cant_artistas = seguidos_usuarios_seguidos.size(); 
                    if(cant_artistas>(max_result-1))
                        cant_artistas = max_result;
                    List<Artista> list_artistas_sugeridos = seguidos_usuarios_seguidos.subList(0,cant_artistas);
                    top3_list.addAll(ArtistaNegocio.setData(cn,list_artistas_sugeridos, usermail,false,true));
                }
            }

            
            //--------------------------------------------------------------------

            
            //mezclo ambas listas
            //Collections.shuffle(priority_list);
            //Collections.shuffle(general_list);
            Collections.sort(top1_list, new DateComparator());
            Collections.sort(top2_list, new DateComparator());
            Collections.sort(top3_list, new DateComparator());
            
            //agrego listas a return list
            return_list.addAll(top1_list);
            return_list.addAll(top2_list);
            return_list.addAll(top3_list);
            
            
          //--------------------------------------------------------------------

            if(return_list.isEmpty())
            {
                //agrega 5 artistas random
                int max_artista = 5;
                String filtro_art = "";
                if(!id_artista.equals(""))
                    filtro_art = " WHERE id != "+id_artista; //Y artista NO SEA YO
                List<Artista> art_list = cn.getListQuery("from Artista"+filtro_art);
                Collections.shuffle(art_list);
                int cant_artistas = art_list.size(); 
                if(cant_artistas>(max_artista-1))
                    cant_artistas = max_artista;
                return_list.addAll(ArtistaNegocio.setData(cn,art_list.subList(0,cant_artistas), usermail,false,true));
                
                //agrega 10 canciones random, sobre las ultimas 30 publicadas
                int max_canciones = 10;
                String filtro_can = "";
                if(!id_artista.equals(""))
                    filtro_can = " WHERE ar.id != "+id_artista+" "; //Y artista NO SEA YO
                List<Cancion> can_list = cn.getListQuery("from Cancion c JOIN FETCH c.artista ar "+filtro_can+" order by fechaPublicacion desc",30);
                Collections.shuffle(can_list);
                int cant_canciones = can_list.size(); 
                if(cant_canciones>(max_canciones-1))
                    cant_canciones = max_canciones;
                return_list.addAll(CancionNegocio.setData(cn,can_list.subList(0,cant_canciones), usermail,true,true));
                
                //mescla los resultados
                Collections.shuffle(return_list);
            }
            
            
            //cn.cerrarConexion();
            return return_list;
        }catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    static class DateComparator implements Comparator<JSONObject>{
        @Override
        public int compare(JSONObject a, JSONObject b) {

            /*Random r = new Random();
            int Low = 2;
            int High = 7;
            int Result = r.nextInt(High-Low) + Low;
            Date fechaA = Tools.GetDateDifference(Result);
            Result = r.nextInt(High-Low) + Low;
            Date fechaB = Tools.GetDateDifference(Result);*/
            
            Date fechaA = new Date();
            Date fechaB = new Date();
            try
            {
                //obtengo tipos de objeto
                String typeA = a.getString("object_type");
                String typeB = b.getString("object_type");
                
                //A
                if(!typeA.equals("Artista") && !typeA.equals("Usuario"))
                    fechaA = new Date(a.getLong("fechaPublicacion"));
                else
                {
                    String variable_name = "fechaInicio";
                    if(typeA.equals("Usuario"))
                        variable_name = "fechaNacimiento";
                    
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(new Date(a.getLong(variable_name)));
                    
                    Integer subs = cal.get(Calendar.DAY_OF_WEEK);
                    if(subs < 2)
                        subs = subs + 2;
                    
                    fechaA = Tools.GetDateDifference(subs);
                }
                
                
                //B
                if(!typeB.equals("Artista") && !typeB.equals("Usuario"))
                    fechaB = new Date(b.getLong("fechaPublicacion"));
                else
                {
                    String variable_name = "fechaInicio";
                    if(typeB.equals("Usuario"))
                        variable_name = "fechaNacimiento";
                    
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(new Date(b.getLong(variable_name)));
                    
                    Integer subs = cal.get(Calendar.DAY_OF_WEEK);
                    if(subs < 2)
                        subs = subs + 2;
                    
                    fechaB = Tools.GetDateDifference(subs);
                }
            }
            catch(JSONException x)
            {
                x.printStackTrace();
                System.err.println("ObjectA: "+a.toString());
                System.err.println("ObjectB: "+b.toString());
            }
            Integer returnvalue = 0;
            if(fechaA.before(fechaB))
                returnvalue = 1;
            else if (fechaA.after(fechaB))
                returnvalue = -1;
            
            
            return returnvalue;
        }
    }
    
    public static ResponseEntity<Object> Buscar(Conexion cn,String busqueda,String genero,String artista,String direccion,String desdehasta,Date fecha,String usermail)
    {
        try
        {
            //Conexion cn = new Conexion();
            //cn.abrirConexion();
            
            //obtengo usuario actual
            List<Integer> usuarios = cn.getListQuery("select u.id from modelos.Usuario u  WHERE u.mail = '"+usermail+"'");
            Integer usuario = usuarios.get(0);
            //obtengo id_artista si lo fuere
            String id_artista = "";
            List<Artista> res_artista = cn.getListQuery("from modelos.Artista WHERE usuario.id = "+usuario);
            if(!res_artista.isEmpty())
                id_artista = String.valueOf(res_artista.get(0).getId()); 
            
            
            
            //fecha actual
            String date_now = Tools.DateFormatter(Tools.GetDateDifference(1));
            
            int top_1 = 20;
            //int top_2 = 2;
            //int top_3 = 1;
            
            //creo lista para devolver
            List<JSONObject> return_list = new ArrayList<JSONObject>();
            //lista de prioridad (aparece primero)
            List<JSONObject> priority_list = new ArrayList<JSONObject>();
            //lista secundaria
            List<JSONObject> general_list = new ArrayList<JSONObject>();
            
            if(!busqueda.equals("") || !genero.equals("") || !artista.equals("") || !direccion.equals("") || fecha != null)
            {
            
                boolean is_evento = false;
                if(!direccion.equals("") || fecha != null)
                    is_evento = true;
                
                String filtro_artista_directo = "";
                if(!artista.equals(""))
                    filtro_artista_directo = " and artista.nombreFantasia like '%"+artista+"%' ";
                else if (!busqueda.equals(""))
                    filtro_artista_directo = " or artista.nombreFantasia like '%"+busqueda+"%' ";
                    
                String filtro_artista_artistagenero = "";
                if(!artista.equals(""))
                    filtro_artista_artistagenero = " and ga.idGeneroArtista.artista.nombreFantasia like '%"+artista+"%' ";
                else if (!busqueda.equals(""))
                    filtro_artista_artistagenero = " or ga.idGeneroArtista.artista.nombreFantasia like '%"+busqueda+"%' ";
                
                
                
                String filtro_fechaEvento = " > '"+date_now+"' ";
                if(fecha != null)
                {
                    String signo = "<";
                    if(desdehasta.equals("Desde"))
                        signo = ">";
                    filtro_fechaEvento += "and fechaEvento "+signo+" '"+Tools.DateFormatter(fecha)+"' ";
                }
                String st_nombre = "";
                String st_publicacion = "";
                String busqueda_usuario = "";
                if(!busqueda.equals("") || !busqueda.isEmpty())
                {
                    st_nombre = " and nombre like '%"+busqueda+"%' ";
                    st_publicacion = " and texto like '%"+busqueda+"%' ";
                    
                    String nombre = busqueda;
                    String apellido = "";
                    if(busqueda.indexOf(' ') > 1)
                    {
                        nombre = busqueda.substring(0, busqueda.indexOf(' '));
                        apellido = busqueda.substring(busqueda.indexOf(' ')+1);
                    }
                    
                    if(genero.equals("") && artista.equals("") && direccion.equals("") && fecha == null)
                    {
                        if(!apellido.equals(""))
                            busqueda_usuario = " nombre like '"+nombre+"%' and apellido like '"+apellido+"%' ";
                        else
                            busqueda_usuario = " (nombre like '"+nombre+"%' or apellido like '"+nombre+"%' )";
                    }                     
                }
                String filtro_direccion = "";
                if(!direccion.equals("") || !direccion.isEmpty())
                {
                    filtro_direccion = " and direccion like '%"+direccion+"%' ";
                }
                
                
                
                if(!genero.equals("") || !genero.isEmpty())
                {
                    List<Integer> list_generos = cn.getListQuery("select g.id from modelos.Genero g WHERE g.descripcion = '"+genero+"'");
                    int idGenero = list_generos.get(0);
                    
                    String filtro_cancion = "";
                    String filtro_disco = "";
                    if(!artista.equals("") || !artista.isEmpty())
                    {
                        filtro_cancion = " and gc.idGeneroCancion.cancion.artista.nombreFantasia like '%"+artista+"%' and gc.idGeneroCancion.cancion.nombre like '%"+busqueda+"%' ";
                        filtro_disco = " and gd.idGeneroDisco.disco.artista.nombreFantasia like '%"+artista+"%' and gd.idGeneroDisco.disco.nombre like '%"+busqueda+"%' ";
                    }
                    else if(!busqueda.equals("") || !busqueda.isEmpty())
                    {
                        filtro_cancion = " and ( gc.idGeneroCancion.cancion.artista.nombreFantasia like '%"+busqueda+"%' or gc.idGeneroCancion.cancion.nombre like '%"+busqueda+"%' )";
                        filtro_disco = " and ( gd.idGeneroDisco.disco.artista.nombreFantasia like '%"+busqueda+"%' or gd.idGeneroDisco.disco.nombre like '%"+busqueda+"%' )";
                        
                    }
                    
                    if(!is_evento)
                    {
                        general_list.addAll(CancionNegocio.setData(cn,cn.getListQuery("select distinct gc.idGeneroCancion.cancion from GeneroCancion gc JOIN FETCH gc.idGeneroCancion.cancion.artista a WHERE gc.idGeneroCancion.genero.id = "+idGenero+" "+filtro_cancion+" order by gc.idGeneroCancion.cancion.fechaPublicacion desc",top_1), usermail,true,true));
                        general_list.addAll(DiscoNegocio.setData(cn,cn.getListQuery("select distinct gd.idGeneroDisco.disco from GeneroDisco gd JOIN FETCH gd.idGeneroDisco.disco.artista a WHERE gd.idGeneroDisco.genero.id = "+idGenero+" "+filtro_disco+" order by gd.idGeneroDisco.disco.fechaPublicacion desc",top_1), usermail,false,true));
                    }
                    
                    List<Artista> artistas_genero = cn.getListQuery("select distinct ga.idGeneroArtista.artista from GeneroArtista ga WHERE ga.idGeneroArtista.genero.id = "+idGenero+" "+filtro_artista_artistagenero);
                    if(!artistas_genero.isEmpty() )//|| !artistas_genero.isEmpty())
                    {
                        //armo query para filtrar por artistas de los generos
                        StringBuilder query_artistas_genero = new StringBuilder();
                        for (Artista a : artistas_genero)
                            query_artistas_genero.append(a.getId()).append(",");
                        query_artistas_genero.deleteCharAt(query_artistas_genero.length() - 1);
                        if(!is_evento)
                        {
                            general_list.addAll(AlbumNegocio.setData(cn,cn.getListQuery("from Album a JOIN FETCH a.artista ar WHERE ar.id in ("+query_artistas_genero+") "+st_nombre+" order by a.fechaPublicacion desc ",top_1), usermail,false,true));
                            general_list.addAll(PublicacionNegocio.setData(cn,cn.getListQuery("from Publicacion p JOIN FETCH p.artista a WHERE a.id in ("+query_artistas_genero+") "+st_publicacion+" order by p.fechaPublicacion desc",top_1), usermail,true));
                        }
                        general_list.addAll(EventoNegocio.setData(cn,cn.getListQuery("from Evento e JOIN FETCH e.artista a WHERE a.id in ("+query_artistas_genero+") "+st_nombre+filtro_direccion+" and e.fechaEvento "+filtro_fechaEvento+" order by e.fechaEvento desc",top_1), usermail,true));
                        

                        if(!is_evento)
                        {
                            //artista no sea yo
                            String filtro_artista_noseayo = "";
                            if(!id_artista.equals(""))
                                filtro_artista_noseayo = " ga.idGeneroArtista.artista.id != "+id_artista+" ";
                            
                            List<Artista> artistas_genero_noseayo = cn.getListQuery("select distinct ga.idGeneroArtista.artista from GeneroArtista ga WHERE "+filtro_artista_noseayo+" and ga.idGeneroArtista.genero.id = "+idGenero+" "+filtro_artista_artistagenero,top_1);
                            if(!artistas_genero.isEmpty())
                            {
                                List<JSONObject> temp_list = ArtistaNegocio.setData(cn,artistas_genero_noseayo, usermail,false,true);
                                if(!artista.equals(""))
                                    priority_list.addAll(temp_list);
                                else
                                    general_list.addAll(temp_list);
                            }
                        }
                    }
                }
                else
                {
                    if(!is_evento)
                    {
                        String filtros = st_nombre+filtro_artista_directo;
                        if(!filtros.equals("") || !filtros.isEmpty())
                        {
                            filtros = filtros.replaceFirst(" and ", "");
                            filtros = filtros.replaceFirst("artista.", "ar.");
                            
                            general_list.addAll(CancionNegocio.setData(cn,cn.getListQuery("from Cancion c JOIN FETCH c.artista ar WHERE  "+filtros+" order by fechaPublicacion desc",top_1), usermail,true,true));
                            general_list.addAll(DiscoNegocio.setData(cn,cn.getListQuery("from Disco d JOIN FETCH d.artista ar WHERE  "+filtros+" order by fechaPublicacion desc",top_1), usermail,false,true));
                            general_list.addAll(AlbumNegocio.setData(cn,cn.getListQuery("from Album a JOIN FETCH a.artista ar WHERE  "+filtros+" order by fechaPublicacion desc",top_1), usermail,false,true));
                        }
                        
                        filtros = st_publicacion+filtro_artista_directo;
                        if(!filtros.equals("") || !filtros.isEmpty())
                        {
                            filtros = filtros.replaceFirst(" and ", "");
                            filtros = filtros.replaceFirst("artista.", "ar.");
                            general_list.addAll(PublicacionNegocio.setData(cn,cn.getListQuery("from Publicacion p JOIN FETCH p.artista ar WHERE  "+filtros+" order by fechaPublicacion desc",top_1), usermail,true));
                        }
                        
                        if(!busqueda_usuario.equals(""))
                        {
                            //usuario no sea yo
                            priority_list.addAll(UsuarioNegocio.setData(cn,cn.getListQuery("from Usuario WHERE "+busqueda_usuario+" and id != "+ usuario+" and usuarioTipo.id = 1",top_1),usermail,false));
                        }
                        
                        if(!filtro_artista_directo.equals(""))
                        {
                            filtros = filtro_artista_directo.replaceFirst(" and ", "").replaceFirst(" or ", "").replaceFirst("artista.", "");
                            
                            //artista no sea yo
                            String filtro_artista_noseayo = "";
                            if(!id_artista.equals(""))
                                filtro_artista_noseayo = " and id != "+id_artista+" ";
                            
                            List<JSONObject> temp_list = ArtistaNegocio.setData(cn,cn.getListQuery("from Artista WHERE "+filtros+filtro_artista_noseayo,top_1),usermail,false,true);
                            //if(!artista.equals(""))
                                priority_list.addAll(temp_list);
                            //else
                            //    general_list.addAll(temp_list);
                        }
                    }
                    String filtros = st_nombre+filtro_artista_directo;
                    if(!filtros.equals("") || !filtros.isEmpty())
                    {
                        filtros = filtros.replaceFirst("artista.", "ar.");
                    }
                    general_list.addAll(EventoNegocio.setData(cn,cn.getListQuery("from Evento e JOIN FETCH e.artista ar WHERE e.fechaEvento "+filtro_fechaEvento+" "+filtros+filtro_direccion+" order by e.fechaEvento desc",top_1), usermail,true));
                    
                }
                
                
            }
            

            //mezclo ambas listas
            //Collections.shuffle(priority_list);
            //Collections.shuffle(general_list);
            Collections.sort(priority_list, new DateComparator());
            Collections.sort(general_list, new DateComparator());

            return_list.addAll(priority_list);
            return_list.addAll(general_list);
            
            cn.cerrarConexion();
            if(return_list.isEmpty())
                return new ResponseEntity<Object>(return_list.toString(),HttpStatus.NO_CONTENT);
            else
                return new ResponseEntity<Object>(return_list.toString(),HttpStatus.OK);
        }catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
  
}

