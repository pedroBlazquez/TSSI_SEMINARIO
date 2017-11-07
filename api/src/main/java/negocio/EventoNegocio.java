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

public class EventoNegocio {
    
    public static ResponseEntity CreateEvento(String nombre,String descripcion,String direccion, Date fechaEvento, int costo,String usermail)
    {
        try
        {
            Conexion cn = new Conexion();
            cn.abrirConexion();
            
            List<Artista> list_artistas = cn.getListQuery("from modelos.Artista WHERE usuario.mail = '"+usermail+"'");
            
            Evento new_Evento = new Evento(nombre,descripcion,fechaEvento,direccion,costo,new Date(), list_artistas.get(0));
            
            cn.add(new_Evento);
            
            cn.cerrarConexion();
            return new ResponseEntity(HttpStatus.CREATED);
        }catch(Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NOT_MODIFIED);
        }
    }
    public static ResponseEntity UpdateEvento(String idEvento,String nombre,String descripcion,String direccion, Date fechaEvento, int costo)
    {
        try
        {
            Conexion cn = new Conexion();
            cn.abrirConexion();
            
            Evento upd_Evento = (Evento) cn.ReadOne_simpleid(Evento.class, Integer.parseInt(idEvento));
            
            upd_Evento.setNombre(nombre);
            upd_Evento.setDescripcion(descripcion);
            upd_Evento.setDireccion(direccion);
            upd_Evento.setFechaEvento(fechaEvento);
            upd_Evento.setCosto(costo);
            
            cn.update(upd_Evento);
            
            cn.cerrarConexion();
            return new ResponseEntity(HttpStatus.ACCEPTED);
        }catch(Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NOT_MODIFIED);
        }
    }
    
   
    public static ResponseEntity DeleteEvento(String idEvento)
    {
        try
        {
            Conexion cn = new Conexion();
            cn.abrirConexion();
            
            Evento del_Evento = (Evento) cn.ReadOne_simpleid(Evento.class, Integer.parseInt(idEvento));
            cn.delete(del_Evento);
            cn.cerrarConexion();
            return new ResponseEntity(HttpStatus.OK);
        }catch(Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NOT_MODIFIED);
        }
    }
}
