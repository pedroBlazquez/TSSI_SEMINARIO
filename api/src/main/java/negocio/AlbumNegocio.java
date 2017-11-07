package negocio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import aplicacion.Tools;
import conexion.Conexion;
import modelos.Album;
import modelos.Artista;
import modelos.Cancion;
import modelos.CancionDisco;
import modelos.Disco;
import modelos.DiscoAlbum;
import modelos.Genero;
import modelos.GeneroDisco;

public class AlbumNegocio {
    
    public static ResponseEntity CreateAlbum(String nombre,  ArrayList<String> discos, String usermail)
    {
        try
        {
            Conexion cn = new Conexion();
            cn.abrirConexion();
            String INdiscos = Tools.Convert_Array_toStringComma(discos);
            
            List<Disco> list_discos = cn.getListQuery("from modelos.Disco WHERE id IN ("+INdiscos+")");
            
            List<Artista> list_artistas = cn.getListQuery("from modelos.Artista WHERE usuario.mail = '"+usermail+"'");
            
            Album new_Album = new Album(nombre,new Date(),list_artistas.get(0));
            
            List<DiscoAlbum> new_DiscoAlbum = new ArrayList<DiscoAlbum>();
            for(Disco c : list_discos)
            {
                new_DiscoAlbum.add(new DiscoAlbum(c, new_Album));
            }
            new_Album.setDiscosAlbum(new_DiscoAlbum);

            cn.add(new_Album);
            
            cn.cerrarConexion();
            return new ResponseEntity(HttpStatus.CREATED);
        }catch(Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NOT_MODIFIED);
        }
    }
    public static ResponseEntity UpdateAlbum(String idAlbum,String nombre,  ArrayList<String> discos)
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
            upd_Album.setFechaPublicacion(new Date());

            upd_Album.setDiscosAlbum(new_DiscoAlbum);
            
            cn.deleteList(cn.getListQuery("from modelos.DiscoAlbum WHERE idDiscoAlbum.album.id = "+idAlbum));
            
            cn.update(upd_Album);
            
            cn.cerrarConexion();
            return new ResponseEntity(HttpStatus.ACCEPTED);
        }catch(Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NOT_MODIFIED);
        }
    }
    public static ResponseEntity DeleteAlbum(String idAlbum)
    {
        try
        {
            Conexion cn = new Conexion();
            cn.abrirConexion();
            //get Album
            Album del_Album = (Album) cn.ReadOne_simpleid(Album.class, Integer.parseInt(idAlbum));
            cn.delete(del_Album);
            cn.cerrarConexion();
            return new ResponseEntity(HttpStatus.OK);
        }catch(Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NOT_MODIFIED);
        }
    }
}
