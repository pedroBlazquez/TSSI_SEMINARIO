package negocio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;

import aplicacion.Tools;
import conexion.Conexion;
import modelos.Artista;
import modelos.Evento;
import modelos.Publicacion;

public class EventoNegocio {
    
    public static ResponseEntity<Object> CreateEvento(String nombre,String descripcion,String direccion, Date fechaEvento, int costo,String usermail,String imagen)
    {
        try
        {
            Conexion cn = new Conexion();
            cn.abrirConexion();
            
            List<Artista> list_artistas = cn.getListQuery("from modelos.Artista WHERE usuario.mail = '"+usermail+"'");
            
            Evento new_Evento = new Evento(nombre,descripcion,fechaEvento,direccion,costo,new Date(), list_artistas.get(0),imagen);
            
            cn.add(new_Evento);
            
            cn.cerrarConexion();
            return new ResponseEntity<Object>(HttpStatus.CREATED);
        }catch(Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity<Object>(HttpStatus.NOT_MODIFIED);
        }
    }
    public static ResponseEntity<Object> UpdateEvento(String idEvento,String nombre,String descripcion,String direccion, Date fechaEvento, int costo,String imagen)
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
            upd_Evento.setImagen(imagen);
            
            cn.update(upd_Evento);
            
            cn.cerrarConexion();
            return new ResponseEntity<Object>(HttpStatus.ACCEPTED);
        }catch(Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity<Object>(HttpStatus.NOT_MODIFIED);
        }
    }
    public static List<JSONObject> setData(Conexion cn,List<Evento> eventos,String usermail,boolean w_artista) throws JsonProcessingException, JSONException
    {
        //Conexion cn = new Conexion();
        //cn.abrirConexion();
        List<JSONObject> list = new ArrayList<JSONObject>();
        for(Evento a : eventos)
        {
            JSONObject jobj = Tools.convertObj_toJSON(a);
            
            String idEvento = String.valueOf(a.getId());

            
            if(w_artista)
                jobj.put("artista", Tools.convertObj_toJSON(a.getArtista()));
            
            jobj.put("likes", LikeNegocio.getLikeCount(cn,"Evento",idEvento));
            
            jobj.put("liked", LikeNegocio.getUserLike(cn,"Evento",idEvento,usermail));
            
            jobj.put("compartido", CompartirNegocio.getCompartidoUsuario(cn,"Evento",idEvento,usermail));

            jobj.put("object_type", "Evento");
            
            list.add(jobj);
        }
        //cn.cerrarConexion();
        return list;
    }
    
   
    public static ResponseEntity<Object> DeleteEvento(String idEvento)
    {
        try
        {
            Conexion cn = new Conexion();
            cn.abrirConexion();
            
            Evento del_Evento = (Evento) cn.ReadOne_simpleid(Evento.class, Integer.parseInt(idEvento));
            cn.delete(del_Evento);
            cn.cerrarConexion();
            return new ResponseEntity<Object>(HttpStatus.OK);
        }catch(Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity<Object>(HttpStatus.NOT_MODIFIED);
        }
    }
}
