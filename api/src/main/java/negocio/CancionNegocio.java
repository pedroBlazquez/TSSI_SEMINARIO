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
import modelos.Cancion;
import modelos.Disco;
import modelos.Genero;
import modelos.GeneroCancion;

public class CancionNegocio {
    
    public static ResponseEntity<Object> CreateCancion(String nombre,String genero,String archivo, String usermail)
    {
        try
        {
            Conexion cn = new Conexion();
            cn.abrirConexion();
            
            List<Genero> list_generos = cn.getListQuery("from modelos.Genero WHERE descripcion = '"+genero+"'");
            
            List<Artista> list_artistas = cn.getListQuery("from modelos.Artista WHERE usuario.mail = '"+usermail+"'");
            
            Cancion new_Cancion = new Cancion(nombre,archivo,new Date(),list_artistas.get(0));
            
            List<GeneroCancion> new_GeneroCancion = new ArrayList<GeneroCancion>();
            for(Genero g : list_generos)
                new_GeneroCancion.add(new GeneroCancion(g,new_Cancion));
            
            new_Cancion.setGenerosCancion(new_GeneroCancion);
            
            cn.add(new_Cancion);
            
            cn.cerrarConexion();
            return new ResponseEntity<Object>(HttpStatus.CREATED);
        }catch(Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity<Object>(HttpStatus.NOT_MODIFIED);
        }
    }
    public static ResponseEntity<Object> UpdateCancion(String idCancion,String nombre,String genero)
    {
        try
        {
            Conexion cn = new Conexion();
            cn.abrirConexion();
            
            Cancion upd_Cancion = (Cancion) cn.ReadOne_simpleid(Cancion.class, Integer.parseInt(idCancion));
            
            List<Genero> list_generos = cn.getListQuery("from modelos.Genero WHERE descripcion = '"+genero+"'");
            
            List<GeneroCancion> new_GeneroCancion = new ArrayList<GeneroCancion>();
            for(Genero g : list_generos)
                new_GeneroCancion.add(new GeneroCancion(g,upd_Cancion));
            
            upd_Cancion.setNombre(nombre);
            upd_Cancion.setFechaPublicacion(new Date());
            upd_Cancion.setGenerosCancion(new_GeneroCancion);
            
            cn.deleteList(cn.getListQuery("from modelos.GeneroCancion WHERE idGeneroCancion.cancion.id = "+idCancion));
            
            cn.update(upd_Cancion);
            
            cn.cerrarConexion();
            return new ResponseEntity<Object>(HttpStatus.ACCEPTED);
        }catch(Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity<Object>(HttpStatus.NOT_MODIFIED);
        }
    }
    public static ResponseEntity<Object> DeleteCancion(String idCancion)
    {
        try
        {
            Conexion cn = new Conexion();
            cn.abrirConexion();
            
            Cancion del_Cancion = (Cancion) cn.ReadOne_simpleid(Cancion.class, Integer.parseInt(idCancion));
            cn.delete(del_Cancion);
            cn.cerrarConexion();
            return new ResponseEntity<Object>(HttpStatus.OK);
        }catch(Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity<Object>(HttpStatus.NOT_MODIFIED);
        }
    }

    public static List<JSONObject> setGenero(Conexion cn,List<Cancion> canciones) throws JsonProcessingException, JSONException
    {
        //Conexion cn = new Conexion();
        //cn.abrirConexion();
        List<JSONObject> list = new ArrayList<JSONObject>();
        for(Cancion c : canciones)
        {
            List<Genero> generos = cn.getListQuery("select gc.idGeneroCancion.genero from modelos.GeneroCancion gc WHERE gc.idGeneroCancion.cancion.id = "+c.getId());
            JSONObject jobj = Tools.convertObj_toJSON(c);
            jobj.put("genero", Tools.convertObj_toJSON(generos.get(0)));
            list.add(jobj);
        }
        //cn.cerrarConexion();
        return list;
    }
    public static List<JSONObject> setData(Conexion cn,List<Cancion> canciones,String usermail,boolean w_artista,boolean w_discos) throws JsonProcessingException, JSONException
    {
        //Conexion cn = new Conexion();
        //cn.abrirConexion();
        List<JSONObject> list = new ArrayList<JSONObject>();
        for(Cancion a : canciones)
        {
            JSONObject jobj = Tools.convertObj_toJSON(a);
            
            String idc = String.valueOf(a.getId());
            
            List<Genero> generos = cn.getListQuery("select gc.idGeneroCancion.genero from modelos.GeneroCancion gc WHERE gc.idGeneroCancion.cancion.id = "+idc);
            jobj.put("genero", Tools.convertObj_toJSON(generos.get(0)));
            
            if(w_artista)
                jobj.put("artista", Tools.convertObj_toJSON(a.getArtista()));
            
            if(w_discos)
                jobj.put("discos", Tools.convertList_toJSON(cn.getListQuery("select cd.idCancionDisco.disco from modelos.CancionDisco cd WHERE cd.idCancionDisco.cancion.id = "+idc+ " order by fechaPublicacion desc")));
            
            
            
            jobj.put("likes", LikeNegocio.getLikeCount(cn,"Cancion",idc));
            
            jobj.put("liked", LikeNegocio.getUserLike(cn,"Cancion",idc,usermail));
            
            jobj.put("compartido", CompartirNegocio.getCompartidoUsuario(cn,"Cancion",idc,usermail));
            
            jobj.put("object_type", "Cancion");
            
            list.add(jobj);
        }
        //cn.cerrarConexion();
        return list;
    }
    
}
