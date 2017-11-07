package negocio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import aplicacion.Tools;
import conexion.Conexion;
import modelos.Artista;
import modelos.Cancion;
import modelos.CancionDisco;
import modelos.Disco;
import modelos.Evento;
import modelos.Genero;
import modelos.GeneroCancion;
import modelos.GeneroDisco;
import modelos.Publicacion;

public class PublicacionNegocio {
    
    public static ResponseEntity CreatePublicacion(String texto,String usermail)
    {
        try
        {
            Conexion cn = new Conexion();
            cn.abrirConexion();
            
            List<Artista> list_artistas = cn.getListQuery("from modelos.Artista WHERE usuario.mail = '"+usermail+"'");
            
            Publicacion new_Publicacion = new Publicacion(texto,new Date(), list_artistas.get(0));
            
            cn.add(new_Publicacion);
            
            cn.cerrarConexion();
            return new ResponseEntity(HttpStatus.CREATED);
        }catch(Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NOT_MODIFIED);
        }
    }
    
    public static ResponseEntity UpdatePublicacion(String idPublicacion,String texto)
    {
        try
        {
            Conexion cn = new Conexion();
            cn.abrirConexion();
            
            Publicacion upd_Publicacion = (Publicacion) cn.ReadOne_simpleid(Publicacion.class, Integer.parseInt(idPublicacion));
            
            upd_Publicacion.setTexto(texto);
            
            cn.update(upd_Publicacion);
            
            cn.cerrarConexion();
            return new ResponseEntity(HttpStatus.ACCEPTED);
        }catch(Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NOT_MODIFIED);
        }
    }
    
   
    public static ResponseEntity DeletePublicacion(String idPublicacion)
    {
        try
        {
            Conexion cn = new Conexion();
            cn.abrirConexion();
            
            Publicacion del_Publicacion = (Publicacion) cn.ReadOne_simpleid(Publicacion.class, Integer.parseInt(idPublicacion));
            cn.delete(del_Publicacion);
            cn.cerrarConexion();
            return new ResponseEntity(HttpStatus.OK);
        }catch(Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NOT_MODIFIED);
        }
    }
}
