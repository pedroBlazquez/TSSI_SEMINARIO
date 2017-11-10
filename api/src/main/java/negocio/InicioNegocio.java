package negocio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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
            List<Usuario> usuarios = cn.getListQuery("from modelos.Usuario WHERE mail = '"+usermail+"'");
            Usuario usuario = usuarios.get(0);
            
            //creo lista para devolver
            List<JSONObject> return_list = new ArrayList<JSONObject>();
            //lista de prioridad (aparece primero)
            List<JSONObject> priority_list = new ArrayList<JSONObject>();
            //lista secundaria
            List<JSONObject> general_list = new ArrayList<JSONObject>();
            
            //obtengo artistas seguidos
            List<Artista> artistas_seguidos = cn.getListQuery(
                "select s.idSeguidos.seguido.artista from modelos.Seguidos s " + 
                "WHERE s.idSeguidos.seguidor.id = "+usuario.getId()+
                " and s.idSeguidos.seguido.usuarioTipo.id != " + 2); //CAMBIAR NUMERO ACORDE AL USUARIO TIPO OYENTE
            
            if(!artistas_seguidos.isEmpty())
            {
                //fecha actual
                String date_now = Tools.DateFormatter(new Date());
                //fecha desde la cual se toman novedades
                String date_novedades = Tools.DateFormatter(Tools.GetDateDifference(2));
                
                //armo query para filtrar por artistas seguidos
                StringBuilder query_artistas_seguidos = new StringBuilder();
                for (Artista seguido : artistas_seguidos)
                    query_artistas_seguidos.append(seguido.getId()).append(",");
                query_artistas_seguidos.deleteCharAt(query_artistas_seguidos.length() - 1);
                
                //busco novedades para los artistas seguidos
                priority_list.addAll(CancionNegocio.setData(cn.getListQuery("from Cancion WHERE artista.id in ("+query_artistas_seguidos+") and fechaPublicacion > '"+date_novedades+"'"), usermail));
                priority_list.addAll(DiscoNegocio.setData(cn.getListQuery("from Disco WHERE artista.id in ("+query_artistas_seguidos+") and fechaPublicacion > '"+date_novedades+"'"), usermail));
                priority_list.addAll(AlbumNegocio.setData(cn.getListQuery("from Album WHERE artista.id in ("+query_artistas_seguidos+") and fechaPublicacion > '"+date_novedades+"'"), usermail));
                priority_list.addAll(PublicacionNegocio.setData(cn.getListQuery("from Publicacion WHERE artista.id in ("+query_artistas_seguidos+") and fechaPublicacion > '"+date_novedades+"'"), usermail));
                priority_list.addAll(EventoNegocio.setData(cn.getListQuery("from Evento WHERE artista.id in ("+query_artistas_seguidos+") and fechaPublicacion > '"+date_novedades+"' and fechaEvento > '"+date_now+"'"), usermail));
                
                /*priority_list.addAll(cn.getListQuery("from Cancion WHERE artista.id in ("+query_artistas_seguidos+") and fechaPublicacion > '"+date_novedades+"'"));
                priority_list.addAll(cn.getListQuery("from Disco WHERE artista.id in ("+query_artistas_seguidos+") and fechaPublicacion > '"+date_novedades+"'"));
                priority_list.addAll(cn.getListQuery("from Album WHERE artista.id in ("+query_artistas_seguidos+") and fechaPublicacion > '"+date_novedades+"'"));
                priority_list.addAll(cn.getListQuery("from Publicacion WHERE artista.id in ("+query_artistas_seguidos+") and fechaPublicacion > '"+date_novedades+"'"));
                priority_list.addAll(cn.getListQuery("from Evento WHERE artista.id in ("+query_artistas_seguidos+") and fechaPublicacion > '"+date_novedades+"' and fechaEvento > '"+date_now+"'"));
                */
                
                //obtengo generos de estos artistas, para definir los generos que le gustan al usuario
                Set<Genero> generos1_set = new TreeSet<Genero>(new Comparator<Genero>() {
                    @Override
                    public int compare(Genero o1, Genero o2) {
                        if(o1.getId() == o2.getId())
                            return 0;
                        else
                            return 1;
                    }
                });
                for (Artista seguido : artistas_seguidos)
                    generos1_set.addAll(cn.getListQuery("select ga.idGeneroArtista.genero from GeneroArtista ga "
                            + "WHERE ga.idGeneroArtista.artista.id = "+seguido.getId()
                            +" order by ga.idGeneroArtista.genero.id"));
                
                //armo query para filtrar por generos que le gustan al usuario
                StringBuilder query_in_generos = new StringBuilder();
                for (Genero g : generos1_set)
                    query_in_generos.append(g.getId()).append(",");
                query_in_generos.deleteCharAt(query_in_generos.length() - 1);
                
                //busco novedades para Discos y Canciones de los generos que le gustan al usuario
                general_list.addAll(CancionNegocio.setData(cn.getListQuery("select gc.idGeneroCancion.cancion from GeneroCancion gc WHERE gc.idGeneroCancion.cancion.artista.id not in ("+query_artistas_seguidos+") and gc.idGeneroCancion.genero.id in ("+query_in_generos+") and gc.idGeneroCancion.cancion.fechaPublicacion > '"+date_novedades+"'"), usermail));
                general_list.addAll(DiscoNegocio.setData(cn.getListQuery("select gd.idGeneroDisco.disco from GeneroDisco gd WHERE gd.idGeneroDisco.disco.artista.id not in ("+query_artistas_seguidos+") and gd.idGeneroDisco.genero.id in ("+query_in_generos+") and gd.idGeneroDisco.disco.fechaPublicacion > '"+date_novedades+"'"), usermail));
                
                //obtengo artistas del genero que le gustan al usuario, pero que no sigue
                List<Artista> artistas_genero = cn.getListQuery("select ga.idGeneroArtista.artista from GeneroArtista ga WHERE ga.idGeneroArtista.artista.id not in ("+query_artistas_seguidos+") and ga.idGeneroArtista.genero.id in ("+query_in_generos+")");
                if(!artistas_genero.isEmpty())
                {
                    //armo query para filtrar por artistas de los generos
                    StringBuilder query_artistas_genero = new StringBuilder();
                    for (Artista a : artistas_genero)
                        query_artistas_genero.append(a.getId()).append(",");
                    query_artistas_genero.deleteCharAt(query_artistas_genero.length() - 1);
                    
                    //busco novedades para Album, Publicacion y Evento, de artistas del genero, que no sean seguidos por el usuario
                    general_list.addAll(AlbumNegocio.setData(cn.getListQuery("from Album WHERE artista.id in ("+query_artistas_genero+") and fechaPublicacion > '"+date_novedades+"'"), usermail));
                    general_list.addAll(PublicacionNegocio.setData(cn.getListQuery("from Publicacion WHERE artista.id in ("+query_artistas_genero+") and fechaPublicacion > '"+date_novedades+"'"), usermail));
                    general_list.addAll(EventoNegocio.setData(cn.getListQuery("from Evento WHERE artista.id in ("+query_artistas_genero+") and fechaPublicacion > '"+date_novedades+"' and fechaEvento > '"+date_now+"'"), usermail));
                    
                    //agrego aleatoriamente 3 artistas que de los generos que le gustan al usuario
                    List<JSONObject> artistas = ArtistaNegocio.setData(artistas_genero, usermail);
                    Collections.shuffle(artistas);
                    int loop_count = 0;
                    for(JSONObject a : artistas)
                    {
                        loop_count++;
                        general_list.add(a);
                        if(loop_count == 3)
                            break;
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
            List<Usuario> usuarios = cn.getListQuery("from modelos.Usuario WHERE mail = '"+usermail+"'");
            Usuario usuario = usuarios.get(0);
            
            
            
            cn.cerrarConexion();
            return new ResponseEntity<Object>(usuarios,HttpStatus.NOT_MODIFIED);
        }catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
  
}
