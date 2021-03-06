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
import modelos.Cancion;
import modelos.CancionLista;
import modelos.Disco;
import modelos.Genero;
import modelos.ListaReproduccion;
import modelos.Usuario;

public class ListasNegocio {
    
    public static ResponseEntity<Object> CreateListaReproduccion(String nombre,boolean privacidad, ArrayList<String> canciones, String usermail)
    {
        try
        {
            Conexion cn = new Conexion();
            cn.abrirConexion();
            
            Usuario usuario = UsuarioNegocio.getUsuarioByMail(cn, usermail);
            
            ListaReproduccion new_ListaReproduccion = new ListaReproduccion(nombre,new Date(),false,privacidad,usuario);
            
            if(!canciones.isEmpty())
            {
                String INcanciones = Tools.Convert_Array_toStringComma(canciones);
                List<Cancion> list_canciones = cn.getListQuery("from modelos.Cancion WHERE id IN ("+INcanciones+")");
            
                List<CancionLista> new_CancionListaReproduccion = new ArrayList<CancionLista>();
                for(Cancion c : list_canciones)
                {
                    new_CancionListaReproduccion.add(new CancionLista(c, new_ListaReproduccion));
                }
                
                new_ListaReproduccion.setCancionesLista(new_CancionListaReproduccion);
            }
            
            cn.add(new_ListaReproduccion);
            
            cn.cerrarConexion();
            return new ResponseEntity<Object>(HttpStatus.CREATED);
        }catch(Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity<Object>(HttpStatus.NOT_MODIFIED);
        }
    }
    public static ResponseEntity<Object> UpdateListaReproduccion(String idLista,String nombre,boolean privacidad,  ArrayList<String> canciones)
    {
        try
        {
            Conexion cn = new Conexion();
            cn.abrirConexion();
            
            ListaReproduccion upd_ListaReproduccion = (ListaReproduccion) cn.ReadOne_simpleid(ListaReproduccion.class, Integer.parseInt(idLista));
            
            upd_ListaReproduccion.setNombre(nombre);
            upd_ListaReproduccion.setPrivacidad(privacidad);
            
            if(!canciones.isEmpty())
            {
            
                String INcanciones = Tools.Convert_Array_toStringComma(canciones);
                
                List<Cancion> list_canciones = cn.getListQuery("from modelos.Cancion WHERE id IN ("+INcanciones+")");
                
                List<CancionLista> new_CancionListaReproduccion = new ArrayList<CancionLista>();
                for(Cancion c : list_canciones)
                    new_CancionListaReproduccion.add(new CancionLista(c, upd_ListaReproduccion));
                
                upd_ListaReproduccion.setCancionesLista(new_CancionListaReproduccion);
                
                cn.deleteList(cn.getListQuery("from modelos.CancionLista WHERE idCancionLista.lista.id = "+idLista));
                
            }
            else
            {
                cn.deleteList(cn.getListQuery("from modelos.CancionLista WHERE idCancionLista.lista.id = "+idLista));
                
            }
                
            
            cn.update(upd_ListaReproduccion);
            
            cn.cerrarConexion();
            return new ResponseEntity<Object>(HttpStatus.ACCEPTED);
        }catch(Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity<Object>(HttpStatus.NOT_MODIFIED);
        }
    }
    public static ResponseEntity<Object> UpdateListaReproduccion_agregarCancion(String idLista,String idCancion)
    {
        try
        {
            Conexion cn = new Conexion();
            cn.abrirConexion();
            
            ListaReproduccion upd_ListaReproduccion = (ListaReproduccion) cn.ReadOne_simpleid(ListaReproduccion.class, Integer.parseInt(idLista));
            
            List<Cancion> list_canciones = cn.getListQuery("from modelos.Cancion WHERE id = "+idCancion);
            
            List<CancionLista> new_CancionListaReproduccion = new ArrayList<CancionLista>();
            for(Cancion c : list_canciones)
                new_CancionListaReproduccion.add(new CancionLista(c, upd_ListaReproduccion));
            
            upd_ListaReproduccion.setCancionesLista(new_CancionListaReproduccion);
            
            cn.update(upd_ListaReproduccion);
            
            cn.cerrarConexion();
            return new ResponseEntity<Object>(HttpStatus.ACCEPTED);
        }catch(Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity<Object>(HttpStatus.NOT_MODIFIED);
        }
    }
    public static ResponseEntity<Object> DeleteListaReproduccion(String idListaReproduccion)
    {
        try
        {
            Conexion cn = new Conexion();
            cn.abrirConexion();
            
            ListaReproduccion del_ListaReproduccion = (ListaReproduccion) cn.ReadOne_simpleid(ListaReproduccion.class, Integer.parseInt(idListaReproduccion));
            cn.delete(del_ListaReproduccion);
            cn.cerrarConexion();
            return new ResponseEntity<Object>(HttpStatus.OK);
        }catch(Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity<Object>(HttpStatus.NOT_MODIFIED);
        }
    }
    public static List<JSONObject> setData(Conexion cn, List<ListaReproduccion> listas,String usermail) throws JsonProcessingException, JSONException
    {
        //Conexion cn = new Conexion();
        //cn.abrirConexion();
        List<JSONObject> list = new ArrayList<JSONObject>();
        for(ListaReproduccion a : listas)
        {
            JSONObject jobj = Tools.convertObj_toJSON(a);
            
            String idListaReproduccion = String.valueOf(a.getId());
            
            List<Cancion> canciones = cn.getListQuery("select cd.idCancionLista.cancion from modelos.CancionLista cd JOIN FETCH cd.idCancionLista.cancion.artista a WHERE cd.idCancionLista.lista.id = "+idListaReproduccion);
            jobj.put("canciones", new JSONArray(CancionNegocio.setData(cn,canciones,usermail,true,true)));

            jobj.put("object_type", "Lista");
            
            list.add(jobj);
        }
        //cn.cerrarConexion();
        return list;
    }
}
