package negocio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;

import aplicacion.Tools;
import conexion.Conexion;
import modelos.Album;
import modelos.Artista;
import modelos.Disco;
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
    public static List<JSONObject> setData(Conexion cn,List<Publicacion> publicaciones,String usermail,boolean w_artista) throws JsonProcessingException, JSONException
    {
        //Conexion cn = new Conexion();
        //cn.abrirConexion();
        List<JSONObject> list = new ArrayList<JSONObject>();
        for(Publicacion a : publicaciones)
        {
            JSONObject jobj = Tools.convertObj_toJSON(a);
            
            String idPublicacion = String.valueOf(a.getId());
            

            if(w_artista)
                jobj.put("artista", Tools.convertObj_toJSON(a.getArtista()));
            
            jobj.put("likes", LikeNegocio.getLikeCount(cn,"Publicacion",idPublicacion));
            
            jobj.put("liked", LikeNegocio.getUserLike(cn,"Publicacion",idPublicacion,usermail));
            
            jobj.put("compartido", CompartirNegocio.getCompartidoUsuario(cn,"Publicacion",idPublicacion,usermail));

            jobj.put("object_type", "Publicacion");
            
            list.add(jobj);
        }
        //cn.cerrarConexion();
        return list;
    }
}
