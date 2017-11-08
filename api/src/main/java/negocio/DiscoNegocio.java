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
import modelos.Genero;
import modelos.GeneroDisco;

public class DiscoNegocio {
    
    public static ResponseEntity<Object> CreateDisco(String nombre,String genero, ArrayList<String> canciones, String usermail)
    {
        try
        {
            Conexion cn = new Conexion();
            cn.abrirConexion();
            //convierte lista de canciones en string con comas para query
            String INcanciones = Tools.Convert_Array_toStringComma(canciones);
            //busca genero para agregar al disco
            List<Genero> list_generos = cn.getListQuery("from modelos.Genero WHERE descripcion = '"+genero+"'");
            //busca canciones para agregar al disco
            List<Cancion> list_canciones = cn.getListQuery("from modelos.Cancion WHERE id IN ("+INcanciones+")");
            //busca el artista correspondiente al usuario logueado
            List<Artista> list_artistas = cn.getListQuery("from modelos.Artista WHERE usuario.mail = '"+usermail+"'");
            //crea el nuevo disco
            Disco new_Disco = new Disco(nombre,new Date(),list_artistas.get(0));
            //crea la nueva conexion muchos a muchos CancionDisco
            List<CancionDisco> new_CancionDisco = new ArrayList<CancionDisco>();
            for(Cancion c : list_canciones)
            {
                new_CancionDisco.add(new CancionDisco(c, new_Disco));
            }
            //crea GeneroDisco
            List<GeneroDisco> new_GeneroDisco = new ArrayList<GeneroDisco>();
            for(Genero g : list_generos)
            {
                new_GeneroDisco.add(new GeneroDisco(g,new_Disco));
            }
            //agrego Generos y Canciones al objeto Disco
            new_Disco.setGenerosDisco(new_GeneroDisco);
            new_Disco.setCancionesDisco(new_CancionDisco);
            //al agregar el disco, se agrega junto con sus generos y canciones
            cn.add(new_Disco);
            
            cn.cerrarConexion();
            return new ResponseEntity<Object>(HttpStatus.CREATED);
        }catch(Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity<Object>(HttpStatus.NOT_MODIFIED);
        }
    }
    public static ResponseEntity<Object> UpdateDisco(String idDisco,String nombre,String genero,  ArrayList<String> canciones)
    {
        try
        {
            Conexion cn = new Conexion();
            cn.abrirConexion();
            //get Disco
            Disco upd_Disco = (Disco) cn.ReadOne_simpleid(Disco.class, Integer.parseInt(idDisco));
            //convierte lista de canciones en string con comas para query
            String INcanciones = Tools.Convert_Array_toStringComma(canciones);
            //busca genero para agregar al disco
            List<Genero> list_generos = cn.getListQuery("from modelos.Genero WHERE descripcion = '"+genero+"'");
            //busca canciones para agregar al disco
            List<Cancion> list_canciones = cn.getListQuery("from modelos.Cancion WHERE id IN ("+INcanciones+")");
            //crea la nueva conexion muchos a muchos CancionDisco
            List<CancionDisco> new_CancionDisco = new ArrayList<CancionDisco>();
            for(Cancion c : list_canciones)
                new_CancionDisco.add(new CancionDisco(c, upd_Disco));
            //crea GeneroDisco
            List<GeneroDisco> new_GeneroDisco = new ArrayList<GeneroDisco>();
            for(Genero g : list_generos)
                new_GeneroDisco.add(new GeneroDisco(g,upd_Disco));
            
            upd_Disco.setNombre(nombre);
            upd_Disco.setFechaPublicacion(new Date());
            //agrego Generos y Canciones nuevas al objeto Disco
            upd_Disco.setGenerosDisco(new_GeneroDisco);
            upd_Disco.setCancionesDisco(new_CancionDisco);
            
            //borro Generos y Canciones actuales
            cn.deleteList(cn.getListQuery("from modelos.CancionDisco WHERE idCancionDisco.disco.id = "+idDisco));
            cn.deleteList(cn.getListQuery("from modelos.GeneroDisco WHERE idGeneroDisco.disco.id = "+idDisco));
            
            //al actualizar el disco, se agrega junto con sus generos y canciones nuevas
            cn.update(upd_Disco);
            
            cn.cerrarConexion();
            return new ResponseEntity<Object>(HttpStatus.ACCEPTED);
        }catch(Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity<Object>(HttpStatus.NOT_MODIFIED);
        }
    }
    public static ResponseEntity<Object> DeleteDisco(String idDisco)
    {
        try
        {
            Conexion cn = new Conexion();
            cn.abrirConexion();
            //get Disco
            Disco del_Disco = (Disco) cn.ReadOne_simpleid(Disco.class, Integer.parseInt(idDisco));
            cn.delete(del_Disco);
            cn.cerrarConexion();
            return new ResponseEntity<Object>(HttpStatus.OK);
        }catch(Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity<Object>(HttpStatus.NOT_MODIFIED);
        }
    }
}
