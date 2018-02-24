package negocio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;

import aplicacion.Tools;
import conexion.Conexion;
import modelos.AccionLikeCompartir;
import modelos.Album;
import modelos.Artista;
import modelos.Cancion;
import modelos.Compartido;
import modelos.Disco;
import modelos.Evento;
import modelos.Genero;
import modelos.Publicacion;
import modelos.Usuario;

public class CompartirNegocio {

    
    public static ResponseEntity<Object> Compartir(String Tipo, String id, String usermail)
    {
        try
        {
            Conexion cn = new Conexion();
            cn.abrirConexion();

            Usuario usuario = UsuarioNegocio.getUsuarioByMail(cn, usermail);
            List<modelos.Compartido> list_exists = new ArrayList<Compartido>();
            
            //chequea existencia
            if(Tipo.equals("Album"))
                list_exists = cn.getListQuery("from modelos.Compartido WHERE accion.album.id = "+id+" and usuario.id = "+usuario.getId(),1);
            else if(Tipo.equals("Artista"))
                list_exists = cn.getListQuery("from modelos.Compartido WHERE accion.artista.id = "+id+" and usuario.id = "+usuario.getId(),1);
            else if(Tipo.equals("Cancion"))
                list_exists = cn.getListQuery("from modelos.Compartido WHERE accion.cancion.id = "+id+" and usuario.id = "+usuario.getId(),1);
            else if(Tipo.equals("Disco"))
                list_exists = cn.getListQuery("from modelos.Compartido WHERE accion.disco.id = "+id+" and usuario.id = "+usuario.getId(),1);
            else if(Tipo.equals("Evento"))
                list_exists = cn.getListQuery("from modelos.Compartido WHERE accion.evento.id = "+id+" and usuario.id = "+usuario.getId(),1);
            else if(Tipo.equals("Publicacion"))
                list_exists = cn.getListQuery("from modelos.Compartido WHERE accion.publicacion.id = "+id+" and usuario.id = "+usuario.getId(),1);
            
            if(list_exists.isEmpty()) //si no existe agrega compartido
            {
                AccionLikeCompartir accion = new AccionLikeCompartir();
                if(Tipo.equals("Album")){
                    List<Album> list = cn.getListQuery("from modelos.Album WHERE id = "+id );
                    accion.setAlbum(list.get(0));
                } else if(Tipo.equals("Artista")){
                    List<Artista> list = cn.getListQuery("from modelos.Artista WHERE id = "+id );
                    accion.setArtista(list.get(0));
                } else if(Tipo.equals("Cancion")){
                    List<Cancion> list = cn.getListQuery("from modelos.Cancion WHERE id = "+id );
                    accion.setCancion(list.get(0));
                } else if(Tipo.equals("Disco")){
                    List<Disco> list = cn.getListQuery("from modelos.Disco WHERE id = "+id ); 
                    accion.setDisco(list.get(0));
                } else if(Tipo.equals("Evento")){
                    List<Evento> list = cn.getListQuery("from modelos.Evento WHERE id = "+id );
                    accion.setEvento(list.get(0));
                } else if(Tipo.equals("Publicacion")){
                    List<Publicacion> list = cn.getListQuery("from modelos.Publicacion WHERE id = "+ id );
                    accion.setPublicacion(list.get(0));
                }
                accion.setFechaAccion(new Date());
                modelos.Compartido compartido = new modelos.Compartido(usuario,accion);
                cn.add(compartido);
            } 
            else
                cn.delete(list_exists.get(0));
            
            cn.cerrarConexion();
            return new ResponseEntity<Object>(HttpStatus.OK);
        }catch(Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity<Object>(HttpStatus.NOT_MODIFIED);
        }
    }
    
    /*public static ResponseEntity<Object> getCompartidosUsuario(Conexion cn,int idUsuario,String usermail) throws JsonProcessingException, JSONException
    {
        //Conexion cn = new Conexion();
        //cn.abrirConexion();
        
        if(idUsuario == 0)
            idUsuario = UsuarioNegocio.getIdUsuarioByMail(cn, usermail);
        
        List<modelos.Compartido> list = cn.getListQuery("from modelos.Compartido c "
                + " LEFT JOIN FETCH c.accion.cancion.artista ca "
                + " LEFT JOIN FETCH c.accion.disco.artista da "
                + " LEFT JOIN FETCH c.accion.album.artista aa "
                + " LEFT JOIN FETCH c.accion.publicacion.artista pa "
                + " LEFT JOIN FETCH c.accion.evento.artista ea "
                + " WHERE c.usuario.id = "+idUsuario+ " order by c.accion.fechaAccion desc");
        
        List<JSONObject> jobj_list = setData(cn,list,usermail);
        
        
        //cn.cerrarConexion();
        if(list.isEmpty())
            return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<Object>(jobj_list.toString(),HttpStatus.OK);
        
    }
    */
    public static ResponseEntity<Object> getCompartidosUsuario(Conexion cn,int idUsuario,String usermail) throws JsonProcessingException, JSONException
    {
        //Conexion cn = new Conexion();
        //cn.abrirConexion();
        Usuario u = new Usuario();
        if(idUsuario == 0)
            u = UsuarioNegocio.getUsuarioByMail(cn, usermail);
        else
            u = UsuarioNegocio.getById(cn, idUsuario);
        
        String id_artista = "";
        List<Artista> res_artista = cn.getListQuery("from modelos.Artista WHERE usuario.id = "+u.getId());
        if(!res_artista.isEmpty())
            id_artista = String.valueOf(res_artista.get(0).getId()); 
        
        
        List<modelos.Compartido> list = cn.getListQuery("from modelos.Compartido c "
                + " LEFT JOIN FETCH c.accion.cancion.artista ca "
                + " LEFT JOIN FETCH c.accion.disco.artista da "
                + " LEFT JOIN FETCH c.accion.album.artista aa "
                + " LEFT JOIN FETCH c.accion.publicacion.artista pa "
                + " LEFT JOIN FETCH c.accion.evento.artista ea "
                + " WHERE c.usuario.id = "+u.getId()+ " order by c.accion.fechaAccion desc");
        
        //si es artista agrega las publicaciones del mismo a la lista de compartidos.
        if(!id_artista.equals(""))
        {
            //get publicaciones
            List<Publicacion> publicaciones = cn.getListQuery("from modelos.Publicacion p JOIN FETCH p.artista pa WHERE pa.id = "+id_artista + " order by p.fechaPublicacion desc");  
            //convertir publicaciones en objeto compartir y agregar a lista
            for(Publicacion p : publicaciones)
            {
                modelos.AccionLikeCompartir alc = new AccionLikeCompartir(p.getFechaPublicacion());
                alc.setPublicacion(p);
                modelos.Compartido c = new Compartido(u,alc);
                list.add(c);
            }
            //ordenar lista por fechaAccion
            Collections.sort(list);
            
        }
        
        List<JSONObject> jobj_list = setData(cn,list,usermail);
        
        
        //cn.cerrarConexion();
        if(list.isEmpty())
            return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<Object>(jobj_list.toString(),HttpStatus.OK);
        
    }
    
    public static boolean getCompartidoUsuario(Conexion cn,String Tipo,String id,String usermail)
    {
        //Conexion cn = new Conexion();
        //cn.abrirConexion();
        
        Integer usuario = UsuarioNegocio.getIdUsuarioByMail(cn, usermail);
        List<Integer> list_exists = new ArrayList<Integer>();
        //chequea existencia
        if(Tipo.equals("Album"))
            list_exists = cn.getListQuery("select c.id from modelos.Compartido c WHERE c.accion.album.id = "+id+" and c.usuario.id = "+usuario,1);
        else if(Tipo.equals("Artista"))
            list_exists = cn.getListQuery("select c.id from modelos.Compartido c WHERE c.accion.artista.id = "+id+" and c.usuario.id = "+usuario,1);
        else if(Tipo.equals("Cancion"))
            list_exists = cn.getListQuery("select c.id from modelos.Compartido c WHERE c.accion.cancion.id = "+id+" and c.usuario.id = "+usuario,1);
        else if(Tipo.equals("Disco"))
            list_exists = cn.getListQuery("select c.id from modelos.Compartido c WHERE c.accion.disco.id = "+id+" and c.usuario.id = "+usuario,1);
        else if(Tipo.equals("Evento"))
            list_exists = cn.getListQuery("select c.id from modelos.Compartido c WHERE c.accion.evento.id = "+id+" and c.usuario.id = "+usuario,1);
        else if(Tipo.equals("Publicacion"))
            list_exists = cn.getListQuery("select c.id from modelos.Compartido c WHERE c.accion.publicacion.id = "+id+" and c.usuario.id = "+usuario,1);
        
        //cn.cerrarConexion();
        if(list_exists.isEmpty())
            return false;
        else
            return true;
        
    }
    public static List<JSONObject> setData(Conexion cn,List<Compartido> compartidos,String usermail) throws JsonProcessingException, JSONException
    {
        //Conexion cn = new Conexion();
        //cn.abrirConexion();
        List<JSONObject> list = new ArrayList<JSONObject>();
        for(Compartido c : compartidos)
        {
            AccionLikeCompartir accion = c.getAccion();
            JSONObject jobj = Tools.convertObj_toJSON(accion);
            
            boolean found = false;
            if(!found)
            {
                Album album = accion.getAlbum();
                if(album != null)
                {
                    found = true;
                    List<Album> aux_list = new ArrayList<Album>();
                    aux_list.add(album);
                    jobj.put("object_type", "Album");
                    jobj.put("album", AlbumNegocio.setData(cn,aux_list, usermail, false, true).get(0));
                }
            }
            if(!found)
            {
                Artista artista = accion.getArtista();
                if(artista != null)
                {
                    found = true;
                    List<Artista> aux_list = new ArrayList<Artista>();
                    aux_list.add(artista);
                    jobj.put("object_type", "Artista");
                    jobj.put("artista", ArtistaNegocio.setData(cn,aux_list, usermail, false,true).get(0));
                }
            }
            if(!found)
            {
                Cancion cancion = accion.getCancion();
                if(cancion != null)
                {
                    found = true;
                    List<Cancion> aux_list = new ArrayList<Cancion>();
                    aux_list.add(cancion);
                    jobj.put("object_type", "Cancion");
                    jobj.put("cancion", CancionNegocio.setData(cn,aux_list, usermail, true,true).get(0));
                }
            }
            if(!found)
            {
                Disco disco = accion.getDisco();
                if(disco != null)
                {
                    found = true;
                    List<Disco> aux_list = new ArrayList<Disco>();
                    aux_list.add(disco);
                    jobj.put("object_type", "Disco");
                    jobj.put("disco", DiscoNegocio.setData(cn,aux_list, usermail,false, true).get(0));
                }
            }
            if(!found)
            {
                Publicacion publicacion = accion.getPublicacion();
                if(publicacion != null)
                {
                    found = true;
                    List<Publicacion> aux_list = new ArrayList<Publicacion>();
                    aux_list.add(publicacion);
                    jobj.put("object_type", "Publicacion");
                    jobj.put("publicacion", PublicacionNegocio.setData(cn,aux_list, usermail, true).get(0));
                }
            }
            if(!found)
            {
                Evento evento = accion.getEvento();
                if(evento != null)
                {
                    found = true;
                    List<Evento> aux_list = new ArrayList<Evento>();
                    aux_list.add(evento);
                    jobj.put("object_type", "Evento");
                    jobj.put("evento", EventoNegocio.setData(cn,aux_list, usermail, true).get(0));
                }
            }
            
            list.add(jobj);
        }
        //cn.cerrarConexion();
        return list;
    }
    
  
    
}
