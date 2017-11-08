package negocio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import conexion.Conexion;
import modelos.Artista;
import modelos.Cancion;
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
}
