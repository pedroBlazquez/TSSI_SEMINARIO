package negocio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import aplicacion.Tools;
import conexion.Conexion;
import modelos.Cancion;
import modelos.CancionLista;
import modelos.ListaReproduccion;
import modelos.Usuario;

public class ListasNegocio {
    
    public static ResponseEntity<Object> CreateListaReproduccion(String nombre,boolean privacidad, ArrayList<String> canciones, String usermail)
    {
        try
        {
            Conexion cn = new Conexion();
            cn.abrirConexion();
            
            String INcanciones = Tools.Convert_Array_toStringComma(canciones);
            
            List<Cancion> list_canciones = cn.getListQuery("from modelos.Cancion WHERE id IN ("+INcanciones+")");

            List<Usuario> usuarios = cn.getListQuery("from modelos.Usuario WHERE mail = '"+usermail+"'");
            Usuario usuario = usuarios.get(0);
            
            ListaReproduccion new_ListaReproduccion = new ListaReproduccion(nombre,new Date(),false,privacidad,usuario);
            
            List<CancionLista> new_CancionListaReproduccion = new ArrayList<CancionLista>();
            for(Cancion c : list_canciones)
            {
                new_CancionListaReproduccion.add(new CancionLista(c, new_ListaReproduccion));
            }
            
            new_ListaReproduccion.setCancionesLista(new_CancionListaReproduccion);
            
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
            
            String INcanciones = Tools.Convert_Array_toStringComma(canciones);
            
            List<Cancion> list_canciones = cn.getListQuery("from modelos.Cancion WHERE id IN ("+INcanciones+")");
            
            List<CancionLista> new_CancionListaReproduccion = new ArrayList<CancionLista>();
            for(Cancion c : list_canciones)
                new_CancionListaReproduccion.add(new CancionLista(c, upd_ListaReproduccion));
            
            upd_ListaReproduccion.setNombre(nombre);
            upd_ListaReproduccion.setPrivacidad(privacidad);
            upd_ListaReproduccion.setCancionesLista(new_CancionListaReproduccion);
            
            cn.deleteList(cn.getListQuery("from modelos.CancionLista WHERE idCancionLista.lista.id = "+idLista));
            
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
}
