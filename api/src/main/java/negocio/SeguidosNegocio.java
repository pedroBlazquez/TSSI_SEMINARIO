package negocio;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import conexion.Conexion;
import modelos.Seguidos;
import modelos.Usuario;

public class SeguidosNegocio {

    
    public static ResponseEntity Seguir(String idSeguido, String usermail_seguidor)
    {
        try
        {
            Conexion cn = new Conexion();
            cn.abrirConexion();
            
            List<Usuario> usuarios_seguidor = cn.getListQuery("from modelos.Usuario WHERE mail = '"+usermail_seguidor+"'");
            Usuario seguidor = usuarios_seguidor.get(0);
            List<Usuario> usuarios_seguido = cn.getListQuery("from modelos.Usuario WHERE id = "+idSeguido+"");
            Usuario seguido = usuarios_seguido.get(0);
            
            Seguidos seguimiento = new Seguidos(seguidor, seguido);
            
            List<Seguidos> seguidos = cn.getListQuery("from modelos.Seguidos WHERE idSeguidos.seguidor.id = "+seguidor.getId()+" and idSeguidos.seguido.id = "+seguido.getId());
            if(seguidos.isEmpty())//si no encuentra seguimiento, agrega uno
                cn.add(seguimiento);
            else //si encuentra seguimiento, lo borra
                cn.delete(seguimiento);
            
            cn.cerrarConexion();
            return new ResponseEntity(HttpStatus.OK);
        }catch(Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NOT_MODIFIED);
        }
    }
    
    public static ResponseEntity getSeguimiento(int idSeguido, String usermail_seguidor)
    {
        try
        {
            Conexion cn = new Conexion();
            cn.abrirConexion();
            
            List<Usuario> usuarios_seguidor = cn.getListQuery("from modelos.Usuario WHERE mail = '"+usermail_seguidor+"'");
            Usuario seguidor = usuarios_seguidor.get(0);
            
            List<Seguidos> seguidos = cn.getListQuery("from modelos.Seguidos WHERE idSeguidos.seguidor.id = "+seguidor.getId()+" and idSeguidos.seguido.id = "+idSeguido);
            
            ResponseEntity re;
            if(seguidos.isEmpty())
            {
                re = new ResponseEntity(HttpStatus.NO_CONTENT);
            }else 
            {
                re = new ResponseEntity(HttpStatus.OK);
            }
            
            cn.cerrarConexion();
            return re;
        }catch(Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    public static List<Usuario> getSeguidores(String usermail_seguido)
    {
        Conexion cn = new Conexion();
        cn.abrirConexion();
        
        List<Usuario> usuarios_seguido = cn.getListQuery("from modelos.Usuario WHERE mail = '"+usermail_seguido+"'");
        Usuario seguido = usuarios_seguido.get(0);
        
        List<Usuario> seguidores = cn.getListQuery("select s.idSeguidos.seguidor from modelos.Seguidos s WHERE s.idSeguidos.seguido.id = "+seguido.getId());
       
        cn.cerrarConexion();
        return seguidores;
    }
    
    public static List<Usuario> getSeguidos(String usermail_seguidor)
    {
        Conexion cn = new Conexion();
        cn.abrirConexion();
        
        List<Usuario> usuarios_seguidor = cn.getListQuery("from modelos.Usuario WHERE mail = '"+usermail_seguidor+"'");
        Usuario seguidor = usuarios_seguidor.get(0);
        
        List<Usuario> seguidos = cn.getListQuery("select s.idSeguidos.seguido from modelos.Seguidos s WHERE s.idSeguidos.seguidor.id = "+seguidor.getId());
       
        cn.cerrarConexion();
        return seguidos;
    }
    
    public static List<Usuario> getSeguidores(int idUsuario_seguido)
    {
        Conexion cn = new Conexion();
        cn.abrirConexion();
        
        List<Usuario> seguidores = cn.getListQuery("select s.idSeguidos.seguidor from modelos.Seguidos s WHERE s.idSeguidos.seguido.id = "+idUsuario_seguido);
       
        cn.cerrarConexion();
        return seguidores;
    }
    
    public static List<Usuario> getSeguidos(int idUsuario_seguidor)
    {
        Conexion cn = new Conexion();
        cn.abrirConexion();
        
        List<Usuario> seguidos = cn.getListQuery("select s.idSeguidos.seguido from modelos.Seguidos s WHERE s.idSeguidos.seguidor.id = "+idUsuario_seguidor);
       
        cn.cerrarConexion();
        return seguidos;
    }
    
  
    
}
