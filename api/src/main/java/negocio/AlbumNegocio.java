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
import modelos.Cancion;
import modelos.Disco;
import modelos.DiscoAlbum;
import modelos.Genero;

public class AlbumNegocio {
    
    public static ResponseEntity<Object> CreateAlbum(String nombre,  ArrayList<String> discos, String usermail,String portada)
    {
        try
        {
            Conexion cn = new Conexion();
            //cn.abrirConexion();
            String INdiscos = Tools.Convert_Array_toStringComma(discos);
          
            List<Disco> list_discos = cn.getListQuery("from modelos.Disco WHERE id IN ("+INdiscos+")");
            
            List<Artista> list_artistas = cn.getListQuery("from modelos.Artista WHERE usuario.mail = '"+usermail+"'");
            
            Album new_Album = new Album(nombre,new Date(),list_artistas.get(0),portada);
            
            List<DiscoAlbum> new_DiscoAlbum = new ArrayList<DiscoAlbum>();
            for(Disco c : list_discos)
            {
                new_DiscoAlbum.add(new DiscoAlbum(c, new_Album));
            }
            new_Album.setDiscosAlbum(new_DiscoAlbum);

            cn.add(new_Album);
            
            cn.cerrarConexion();
            return new ResponseEntity<Object>(HttpStatus.CREATED);
        }catch(Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity<Object>(HttpStatus.NOT_MODIFIED);
        }
    }
    public static ResponseEntity<Object> UpdateAlbum(String idAlbum,String nombre,  ArrayList<String> discos,String portada)
    {
        try
        {
            Conexion cn = new Conexion();
            cn.abrirConexion();
            
            Album upd_Album = (Album) cn.ReadOne_simpleid(Album.class, Integer.parseInt(idAlbum));

            String INdiscos = Tools.Convert_Array_toStringComma(discos);

            List<Disco> list_discos = cn.getListQuery("from modelos.Disco WHERE id IN ("+INdiscos+")");

            List<DiscoAlbum> new_DiscoAlbum = new ArrayList<DiscoAlbum>();
            for(Disco c : list_discos)
                new_DiscoAlbum.add(new DiscoAlbum(c, upd_Album));
            
            upd_Album.setNombre(nombre);
            //upd_Album.setFechaPublicacion(new Date());
            upd_Album.setPortada(portada);

            upd_Album.setDiscosAlbum(new_DiscoAlbum);
            
            cn.deleteList(cn.getListQuery("from modelos.DiscoAlbum WHERE idDiscoAlbum.album.id = "+idAlbum));
            
            cn.update(upd_Album);
            
            cn.cerrarConexion();
            return new ResponseEntity<Object>(HttpStatus.ACCEPTED);
        }catch(Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity<Object>(HttpStatus.NOT_MODIFIED);
        }
    }
    public static ResponseEntity<Object> DeleteAlbum(String idAlbum)
    {
        try
        {
            Conexion cn = new Conexion();
            cn.abrirConexion();
            //get Album
            Album del_Album = (Album) cn.ReadOne_simpleid(Album.class, Integer.parseInt(idAlbum));
            cn.delete(del_Album);
            cn.cerrarConexion();
            return new ResponseEntity<Object>(HttpStatus.OK);
        }catch(Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity<Object>(HttpStatus.NOT_MODIFIED);
        }
    }
    /*public static List<JSONObject> setDiscos(List<Album> albums) throws JsonProcessingException, JSONException
    {
        Conexion cn = new Conexion();
        cn.abrirConexion();
        List<JSONObject> list = new ArrayList<JSONObject>();
        for(Album a : albums)
        {
            List<Disco> discos = cn.getListQuery("select cd.idDiscoAlbum.disco from modelos.DiscoAlbum cd WHERE cd.idDiscoAlbum.album.id = "+a.getId());
            JSONObject jobj = Tools.convertObj_toJSON(a);
            jobj.put("discos", Tools.convertList_toJSON(discos));
            list.add(jobj);
        }
        cn.cerrarConexion();
        return list;
    }*/
    public static List<JSONObject> setData(Conexion cn,List<Album> albums,String usermail,boolean w_generodisco,boolean w_artista) throws JsonProcessingException, JSONException
    {
        //Conexion cn = new Conexion();
        //cn.abrirConexion();
        List<JSONObject> list = new ArrayList<JSONObject>();
        for(Album a : albums)
        {
            JSONObject jobj = Tools.convertObj_toJSON(a);
            
            String idAlbum = String.valueOf(a.getId());
            
            List<Disco> discos = cn.getListQuery("select cd.idDiscoAlbum.disco from modelos.DiscoAlbum cd WHERE cd.idDiscoAlbum.album.id = "+a.getId());
            JSONArray discos_w_genero = null;
            if(w_generodisco)
                discos_w_genero = new JSONArray(DiscoNegocio.setGenero(cn,discos));
            else
                discos_w_genero = Tools.convertList_toJSON(discos);
                
            
            if(w_artista)
                jobj.put("artista", Tools.convertObj_toJSON(a.getArtista()));
            
            jobj.put("discos", discos_w_genero);
                
            jobj.put("likes", LikeNegocio.getLikeCount(cn,"Album",idAlbum));
            
            jobj.put("liked", LikeNegocio.getUserLike(cn,"Album",idAlbum,usermail));
            
            jobj.put("compartido", CompartirNegocio.getCompartidoUsuario(cn,"Album",idAlbum,usermail));

            jobj.put("object_type", "Album");
            
            list.add(jobj);
        }
        //cn.cerrarConexion();
        return list;
    }
}
