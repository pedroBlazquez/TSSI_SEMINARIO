package negocio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import aplicacion.Tools;
import conexion.Conexion;
import modelos.Artista;
import modelos.Genero;
import modelos.Usuario;

public class InicioNegocio {
    
    public static List<JSONObject> getNovedades(String usermail)
    {
        try
        {
            Conexion cn = new Conexion();
            cn.abrirConexion();
            
            //obtengo usuario actual
            List<Integer> usuarios = cn.getListQuery("select u.id from modelos.Usuario u WHERE u.mail = '"+usermail+"'");
            Integer usuario = usuarios.get(0);
            
            //creo lista para devolver
            List<JSONObject> return_list = new ArrayList<JSONObject>();
            //lista de prioridad (aparece primero)
            List<JSONObject> priority_list = new ArrayList<JSONObject>();
            //lista secundaria
            List<JSONObject> general_list = new ArrayList<JSONObject>();
            
            //obtengo artistas seguidos
            List<Integer> artistas_seguidos = cn.getListQuery(
                "select s.idSeguidos.seguido.artista.id from modelos.Seguidos s " + 
                "WHERE s.idSeguidos.seguidor.id = "+usuario+
                " and s.idSeguidos.seguido.usuarioTipo.id != " + 1); //CAMBIAR NUMERO ACORDE AL USUARIO TIPO OYENTE
            
            
            if(!artistas_seguidos.isEmpty())
            {
                //fecha actual
                String date_now = Tools.DateFormatter(new Date());
                //fecha desde la cual se toman novedades
                String date_novedades = Tools.DateFormatter(Tools.GetDateDifference(2));
                
                //armo query para filtrar por artistas seguidos
                StringBuilder query_artistas_seguidos = new StringBuilder();
                for (Integer seguido : artistas_seguidos)
                    query_artistas_seguidos.append(seguido).append(",");
                query_artistas_seguidos.deleteCharAt(query_artistas_seguidos.length() - 1);
                
                //set all to Lazy and use join fetch to get lazy data... SELECT DISTINCT a FROM Author a JOIN FETCH a.books b
                //use org.hibernate.annotations.Cache 
                //Query cache is not turned on by default. You'll have to do something like query.setCacheable(true); to ensure that query is cached. Or set the hibernate.cache.use_query_cache property in Hibernate config file.
                
                //busco novedades para los artistas seguidos
                priority_list.addAll(CancionNegocio.setData(cn.getListQuery("from Cancion c JOIN FETCH c.artista ar WHERE ar.id in ("+query_artistas_seguidos+") and c.fechaPublicacion > '"+date_novedades+"'"+" order by fechaPublicacion desc"), usermail,true));
                priority_list.addAll(DiscoNegocio.setData(cn.getListQuery("from Disco d JOIN FETCH d.artista ar WHERE ar.id in ("+query_artistas_seguidos+") and d.fechaPublicacion > '"+date_novedades+"'"+" order by fechaPublicacion desc"), usermail,false,true));
                priority_list.addAll(AlbumNegocio.setData(cn.getListQuery("from Album a JOIN FETCH a.artista ar WHERE ar.id in ("+query_artistas_seguidos+") and a.fechaPublicacion > '"+date_novedades+"'"+" order by fechaPublicacion desc"), usermail,false,true));
                priority_list.addAll(PublicacionNegocio.setData(cn.getListQuery("from Publicacion p JOIN FETCH p.artista ar WHERE ar.id in ("+query_artistas_seguidos+") and p.fechaPublicacion > '"+date_novedades+"'"+" order by fechaPublicacion desc"), usermail,true));
                priority_list.addAll(EventoNegocio.setData(cn.getListQuery("from Evento e JOIN FETCH e.artista ar WHERE ar.id in ("+query_artistas_seguidos+") and e.fechaPublicacion > '"+date_novedades+"' and e.fechaEvento > '"+date_now+"'"+" order by fechaPublicacion desc"), usermail,true));
                
                /*priority_list.addAll(cn.getListQuery("from Cancion WHERE artista.id in ("+query_artistas_seguidos+") and fechaPublicacion > '"+date_novedades+"'"));
                priority_list.addAll(cn.getListQuery("from Disco WHERE artista.id in ("+query_artistas_seguidos+") and fechaPublicacion > '"+date_novedades+"'"));
                priority_list.addAll(cn.getListQuery("from Album WHERE artista.id in ("+query_artistas_seguidos+") and fechaPublicacion > '"+date_novedades+"'"));
                priority_list.addAll(cn.getListQuery("from Publicacion WHERE artista.id in ("+query_artistas_seguidos+") and fechaPublicacion > '"+date_novedades+"'"));
                priority_list.addAll(cn.getListQuery("from Evento WHERE artista.id in ("+query_artistas_seguidos+") and fechaPublicacion > '"+date_novedades+"' and fechaEvento > '"+date_now+"'"));
                */
                
                //obtengo generos de estos artistas, para definir los generos que le gustan al usuario
                /*Set<Genero> generos1_set = new TreeSet<Genero>(new Comparator<Genero>() {
                    @Override
                    public int compare(Genero o1, Genero o2) {
                        if(o1.getId() == o2.getId())
                            return 0;
                        else
                            return 1;
                    }
                });*/
                Set<Integer> generos1_set = new TreeSet<Integer>();
                generos1_set.addAll(cn.getListQuery("select distinct ga.idGeneroArtista.genero.id from GeneroArtista ga "
                            + "WHERE ga.idGeneroArtista.artista.id IN ("+query_artistas_seguidos+")"
                            +" order by ga.idGeneroArtista.genero.id"));
                
                //armo query para filtrar por generos que le gustan al usuario
                StringBuilder query_in_generos = new StringBuilder();
                for (Integer g : generos1_set)
                    query_in_generos.append(g).append(",");
                query_in_generos.deleteCharAt(query_in_generos.length() - 1);
                
                //busco novedades para Discos y Canciones de los generos que le gustan al usuario
                general_list.addAll(CancionNegocio.setData(cn.getListQuery("select distinct gc.idGeneroCancion.cancion from GeneroCancion gc JOIN FETCH gc.idGeneroCancion.cancion.artista ar WHERE ar.id not in ("+query_artistas_seguidos+") and gc.idGeneroCancion.genero.id in ("+query_in_generos+") and gc.idGeneroCancion.cancion.fechaPublicacion > '"+date_novedades+"'"+" order by fechaPublicacion desc",10), usermail,true));
                general_list.addAll(DiscoNegocio.setData(cn.getListQuery("select distinct gd.idGeneroDisco.disco from GeneroDisco gd JOIN FETCH gd.idGeneroDisco.disco.artista ar WHERE ar.id not in ("+query_artistas_seguidos+") and gd.idGeneroDisco.genero.id in ("+query_in_generos+") and gd.idGeneroDisco.disco.fechaPublicacion > '"+date_novedades+"'"+" order by fechaPublicacion desc",10), usermail,false,true));
                
                //obtengo artistas del genero que le gustan al usuario, pero que no sigue
                List<Artista> artistas_genero = cn.getListQuery("select distinct ga.idGeneroArtista.artista from GeneroArtista ga WHERE ga.idGeneroArtista.artista.id not in ("+query_artistas_seguidos+") and ga.idGeneroArtista.genero.id in ("+query_in_generos+")");
                if(!artistas_genero.isEmpty())
                {
                    //armo query para filtrar por artistas de los generos
                    StringBuilder query_artistas_genero = new StringBuilder();
                    for (Artista a : artistas_genero)
                        query_artistas_genero.append(a.getId()).append(",");
                    query_artistas_genero.deleteCharAt(query_artistas_genero.length() - 1);
                    
                    //busco novedades para Album, Publicacion y Evento, de artistas del genero, que no sean seguidos por el usuario
                    general_list.addAll(AlbumNegocio.setData(cn.getListQuery("from Album a JOIN FETCH a.artista ar WHERE ar.id in ("+query_artistas_genero+") and a.fechaPublicacion > '"+date_novedades+"'"+" order by fechaPublicacion desc",10), usermail,false,true));
                    general_list.addAll(PublicacionNegocio.setData(cn.getListQuery("from Publicacion p JOIN FETCH p.artista ar WHERE ar.id in ("+query_artistas_genero+") and p.fechaPublicacion > '"+date_novedades+"'"+" order by fechaPublicacion desc",10), usermail,true));
                    general_list.addAll(EventoNegocio.setData(cn.getListQuery("from Evento e JOIN FETCH e.artista ar WHERE ar.id in ("+query_artistas_genero+") and e.fechaPublicacion > '"+date_novedades+"' and e.fechaEvento > '"+date_now+"'"+" order by fechaPublicacion desc",10), usermail,true));
                    
                    //agrego aleatoriamente 3 artistas que de los generos que le gustan al usuario
                    Collections.shuffle(artistas_genero);
                    int cant_artistas_genero = artistas_genero.size(); 
                    if(cant_artistas_genero>2)
                        cant_artistas_genero = 3;
                    artistas_genero.subList(0,cant_artistas_genero);
                    
                    List<JSONObject> artistas = ArtistaNegocio.setData(artistas_genero, usermail,false);
                    //Collections.shuffle(artistas);
                    //int loop_count = 0;
                    for(JSONObject a : artistas)
                    {
                      //  loop_count++;
                        general_list.add(a);
                        //if(loop_count == 3)
                          //  break;
                    }
                }
                //mezclo ambas listas
                Collections.shuffle(priority_list);
                Collections.shuffle(general_list);
                
                //agrego listas a return list
                return_list.addAll(priority_list);
                return_list.addAll(general_list);
            }
            
            cn.cerrarConexion();
            return return_list;
        }catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    public static ResponseEntity<Object> Buscar(String busqueda,String genero,String artista,String direccion,String desdehasta,Date fecha,String usermail)
    {
        try
        {
            Conexion cn = new Conexion();
            cn.abrirConexion();
            
            //obtengo usuario actual
            //List<Integer> usuarios = cn.getListQuery("select u.id from modelos.Usuario u  WHERE u.mail = '"+usermail+"'");
            //Integer usuario = usuarios.get(0);
            
            //fecha actual
            String date_now = Tools.DateFormatter(new Date());
            
            int top_1 = 10;
            int top_2 = 2;
            int top_3 = 1;
            
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
                    String signo = ">";
                    if(desdehasta.equals("Desde"))
                        signo = "<";
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
                        priority_list.addAll(CancionNegocio.setData(cn.getListQuery("select distinct gc.idGeneroCancion.cancion from GeneroCancion gc JOIN FETCH gc.idGeneroCancion.cancion.artista a WHERE gc.idGeneroCancion.genero.id = "+idGenero+" "+filtro_cancion+" order by gc.idGeneroCancion.cancion.fechaPublicacion desc",top_1), usermail,true));
                        priority_list.addAll(DiscoNegocio.setData(cn.getListQuery("select distinct gd.idGeneroDisco.disco from GeneroDisco gd JOIN FETCH gd.idGeneroDisco.disco.artista a WHERE gd.idGeneroDisco.genero.id = "+idGenero+" "+filtro_disco+" order by gd.idGeneroDisco.disco.fechaPublicacion desc",top_1), usermail,false,true));
                    }
                    List<Artista> artistas_genero = cn.getListQuery("select distinct ga.idGeneroArtista.artista from GeneroArtista ga WHERE ga.idGeneroArtista.genero.id = "+idGenero+" "+filtro_artista_artistagenero);
                    if(!artistas_genero.isEmpty() || !artistas_genero.isEmpty())
                    {
                        //armo query para filtrar por artistas de los generos
                        StringBuilder query_artistas_genero = new StringBuilder();
                        for (Artista a : artistas_genero)
                            query_artistas_genero.append(a.getId()).append(",");
                        query_artistas_genero.deleteCharAt(query_artistas_genero.length() - 1);
                        if(!is_evento)
                        {
                            priority_list.addAll(AlbumNegocio.setData(cn.getListQuery("from Album a JOIN FETCH a.artista ar WHERE ar.id in ("+query_artistas_genero+") "+st_nombre+" order by a.fechaPublicacion desc ",top_1), usermail,false,true));
                            priority_list.addAll(PublicacionNegocio.setData(cn.getListQuery("from Publicacion p JOIN FETCH p.artista a WHERE a.id in ("+query_artistas_genero+") "+st_publicacion+" order by p.fechaPublicacion desc",top_1), usermail,true));
                        }
                        priority_list.addAll(EventoNegocio.setData(cn.getListQuery("from Evento e JOIN FETCH e.artista a WHERE a.id in ("+query_artistas_genero+") "+st_nombre+filtro_direccion+" and e.fechaEvento "+filtro_fechaEvento+" order by e.fechaEvento desc",top_1), usermail,true));
                        
                    }
                    if(!is_evento)
                        priority_list.addAll(ArtistaNegocio.setData(artistas_genero, usermail,false));
                }
                else
                {
                    if(!is_evento)
                    {
                        String filtros = st_nombre+filtro_artista_directo;
                        if(!filtros.equals("") || !filtros.isEmpty())
                        {
                            filtros = filtros.replaceFirst(" and ", "");
                            filtros = filtros.replaceFirst(" artista.", " ar.");
                            
                            priority_list.addAll(CancionNegocio.setData(cn.getListQuery("from Cancion c JOIN FETCH c.artista ar WHERE  "+filtros+" order by fechaPublicacion desc",top_1), usermail,true));
                            priority_list.addAll(DiscoNegocio.setData(cn.getListQuery("from Disco d JOIN FETCH d.artista ar WHERE  "+filtros+" order by fechaPublicacion desc",top_1), usermail,false,true));
                            priority_list.addAll(AlbumNegocio.setData(cn.getListQuery("from Album a JOIN FETCH a.artista ar WHERE  "+filtros+" order by fechaPublicacion desc",top_1), usermail,false,true));
                        }
                        
                        filtros = st_publicacion+filtro_artista_directo;
                        if(!filtros.equals("") || !filtros.isEmpty())
                        {
                            filtros = filtros.replaceFirst(" and ", "");
                            filtros = filtros.replaceFirst(" artista.", " ar.");
                            priority_list.addAll(PublicacionNegocio.setData(cn.getListQuery("from Publicacion p JOIN FETCH p.artista ar WHERE  "+filtros+" order by fechaPublicacion desc"), usermail,true));
                        }
                        
                        if(!busqueda_usuario.equals(""))
                        {
                            priority_list.addAll(UsuarioNegocio.setData(cn.getListQuery("from Usuario WHERE "+busqueda_usuario+" and usuarioTipo.id = 1",top_1),usermail));
                        }
                        
                        if(!filtro_artista_directo.equals(""))
                        {
                            filtros = filtro_artista_directo.replaceFirst(" and ", "").replaceFirst(" or ", "").replaceFirst("artista.", "");
                            priority_list.addAll(ArtistaNegocio.setData(cn.getListQuery("from Artista WHERE "+filtros,top_1),usermail,false));
                        }
                    }
                    String filtros = st_nombre+filtro_artista_directo;
                    if(!filtros.equals("") || !filtros.isEmpty())
                    {
                        filtros = filtros.replaceFirst(" artista.", " ar.");
                    }
                    priority_list.addAll(EventoNegocio.setData(cn.getListQuery("from Evento e JOIN FETCH e.artista ar WHERE e.fechaEvento "+filtro_fechaEvento+" "+filtros+filtro_direccion+" order by e.fechaEvento desc",top_1), usermail,true));
                    
                }
                
                
            }
            
            
            cn.cerrarConexion();
            return new ResponseEntity<Object>(priority_list.toString(),HttpStatus.OK);
        }catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
  
}

