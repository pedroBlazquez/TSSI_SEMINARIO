package negocio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import conexion.Conexion;
import modelos.AccionLikeCompartir;
import modelos.Album;
import modelos.Artista;
import modelos.Cancion;
import modelos.Disco;
import modelos.Evento;
import modelos.Like;
import modelos.Publicacion;
import modelos.Usuario;

public class LikeNegocio {

    
    public static ResponseEntity<Object> Like(String Tipo, String id, String usermail)
    {
        try
        {
            Conexion cn = new Conexion();
            cn.abrirConexion();

            List<Usuario> usuarios = cn.getListQuery("from modelos.Usuario WHERE mail = '"+usermail+"'");
            Usuario usuario = usuarios.get(0);
            
            List<modelos.Like> list_exists = new ArrayList<Like>();
            
            //chequea existencia
            if(Tipo.equals("Album"))
                list_exists = cn.getListQuery("from modelos.Like WHERE accion.album.id = "+id+" and usuario.id = "+usuario.getId());
            else if(Tipo.equals("Artista"))
                list_exists = cn.getListQuery("from modelos.Like WHERE accion.artista.id = "+id+" and usuario.id = "+usuario.getId());
            else if(Tipo.equals("Cancion"))
                list_exists = cn.getListQuery("from modelos.Like WHERE accion.cancion.id = "+id+" and usuario.id = "+usuario.getId());
            else if(Tipo.equals("Disco"))
                list_exists = cn.getListQuery("from modelos.Like WHERE accion.disco.id = "+id+" and usuario.id = "+usuario.getId());
            else if(Tipo.equals("Evento"))
                list_exists = cn.getListQuery("from modelos.Like WHERE accion.evento.id = "+id+" and usuario.id = "+usuario.getId());
            else if(Tipo.equals("Publicacion"))
                list_exists = cn.getListQuery("from modelos.Like WHERE accion.publicacion.id = "+id+" and usuario.id = "+usuario.getId());
            
            if(list_exists.isEmpty()) //si no existe agrega like
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
                modelos.Like like = new modelos.Like(usuario,accion);
                cn.add(like);
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
    
    public static ResponseEntity<Object> getUserLike(String Tipo,String id,String usermail)
    {
        Conexion cn = new Conexion();
        cn.abrirConexion();
        
        List<Usuario> usuarios = cn.getListQuery("from modelos.Usuario WHERE mail = '"+usermail+"'");
        Usuario usuario = usuarios.get(0);
        List<modelos.Like> list_exists = new ArrayList<Like>();
        //chequea existencia
        if(Tipo.equals("Album"))
            list_exists = cn.getListQuery("from modelos.Like WHERE accion.album.id = "+id+" and usuario.id = "+usuario.getId());
        else if(Tipo.equals("Artista"))
            list_exists = cn.getListQuery("from modelos.Like WHERE accion.artista.id = "+id+" and usuario.id = "+usuario.getId());
        else if(Tipo.equals("Cancion"))
            list_exists = cn.getListQuery("from modelos.Like WHERE accion.cancion.id = "+id+" and usuario.id = "+usuario.getId());
        else if(Tipo.equals("Disco"))
            list_exists = cn.getListQuery("from modelos.Like WHERE accion.disco.id = "+id+" and usuario.id = "+usuario.getId());
        else if(Tipo.equals("Evento"))
            list_exists = cn.getListQuery("from modelos.Like WHERE accion.evento.id = "+id+" and usuario.id = "+usuario.getId());
        else if(Tipo.equals("Publicacion"))
            list_exists = cn.getListQuery("from modelos.Like WHERE accion.publicacion.id = "+id+" and usuario.id = "+usuario.getId());
        
        cn.cerrarConexion();
        if(list_exists.isEmpty())
            return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<Object>(HttpStatus.OK);
        
    }
    
    public static ResponseEntity<Object> getUserLikes(String usermail)
    {
        Conexion cn = new Conexion();
        cn.abrirConexion();
        
        List<Usuario> usuarios = cn.getListQuery("from modelos.Usuario WHERE mail = '"+usermail+"'");
        Usuario usuario = usuarios.get(0);
        List<modelos.Like> list = cn.getListQuery("from modelos.Like WHERE usuario.id = "+usuario.getId());
        
        cn.cerrarConexion();
        if(list.isEmpty())
            return new ResponseEntity<Object>(list,HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<Object>(list,HttpStatus.OK);
        
    }
    
    public static ResponseEntity<Object> getLikeCount(String Tipo,String id)
    {
        Conexion cn = new Conexion();
        cn.abrirConexion();
        
        List<modelos.Like> list_exists = new ArrayList<Like>();
        //chequea existencia
        if(Tipo.equals("Album"))
            list_exists = cn.getListQuery("from modelos.Like WHERE accion.album.id = "+id);
        else if(Tipo.equals("Artista"))
            list_exists = cn.getListQuery("from modelos.Like WHERE accion.artista.id = "+id);
        else if(Tipo.equals("Cancion"))
            list_exists = cn.getListQuery("from modelos.Like WHERE accion.cancion.id = "+id);
        else if(Tipo.equals("Disco"))
            list_exists = cn.getListQuery("from modelos.Like WHERE accion.disco.id = "+id);
        else if(Tipo.equals("Evento"))
            list_exists = cn.getListQuery("from modelos.Like WHERE accion.evento.id = "+id);
        else if(Tipo.equals("Publicacion"))
            list_exists = cn.getListQuery("from modelos.Like WHERE accion.publicacion.id = "+id);
        
        cn.cerrarConexion();
        if(list_exists.isEmpty())
            return new ResponseEntity<Object>(list_exists.size(),HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<Object>(list_exists.size(),HttpStatus.OK);
        
    }
    
  
    
}
