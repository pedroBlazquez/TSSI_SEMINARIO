package negocio;

import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import conexion.Conexion;
import modelos.Artista;
import modelos.Publicacion;

public class PublicacionNegocio {
    
    public static ResponseEntity<Object> CreatePublicacion(String texto,String usermail)
    {
        try
        {
            Conexion cn = new Conexion();
            cn.abrirConexion();
            
            List<Artista> list_artistas = cn.getListQuery("from modelos.Artista WHERE usuario.mail = '"+usermail+"'");
            
            Publicacion new_Publicacion = new Publicacion(texto,new Date(), list_artistas.get(0));
            
            cn.add(new_Publicacion);
            
            cn.cerrarConexion();
            return new ResponseEntity<Object>(HttpStatus.CREATED);
        }catch(Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity<Object>(HttpStatus.NOT_MODIFIED);
        }
    }
    
    public static ResponseEntity<Object> UpdatePublicacion(String idPublicacion,String texto)
    {
        try
        {
            Conexion cn = new Conexion();
            cn.abrirConexion();
            
            Publicacion upd_Publicacion = (Publicacion) cn.ReadOne_simpleid(Publicacion.class, Integer.parseInt(idPublicacion));
            
            upd_Publicacion.setTexto(texto);
            
            cn.update(upd_Publicacion);
            
            cn.cerrarConexion();
            return new ResponseEntity<Object>(HttpStatus.ACCEPTED);
        }catch(Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity<Object>(HttpStatus.NOT_MODIFIED);
        }
    }
    
   
    public static ResponseEntity<Object> DeletePublicacion(String idPublicacion)
    {
        try
        {
            Conexion cn = new Conexion();
            cn.abrirConexion();
            
            Publicacion del_Publicacion = (Publicacion) cn.ReadOne_simpleid(Publicacion.class, Integer.parseInt(idPublicacion));
            cn.delete(del_Publicacion);
            cn.cerrarConexion();
            return new ResponseEntity<Object>(HttpStatus.OK);
        }catch(Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity<Object>(HttpStatus.NOT_MODIFIED);
        }
    }
}
