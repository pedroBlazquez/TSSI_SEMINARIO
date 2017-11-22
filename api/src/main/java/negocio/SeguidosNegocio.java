package negocio;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import conexion.Conexion;
import modelos.Seguidos;
import modelos.Usuario;

public class SeguidosNegocio {

    
    public static ResponseEntity<Object> Seguir(String idSeguido, String usermail_seguidor)
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
            return new ResponseEntity<Object>(HttpStatus.OK);
        }catch(Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity<Object>(HttpStatus.NOT_MODIFIED);
        }
    }
    
    public static boolean getSeguimiento(int idSeguido, String usermail_seguidor)
    {
        Conexion cn = new Conexion();
        cn.abrirConexion();
        
        List<Integer> usuarios_seguidor = cn.getListQuery("select u.id from modelos.Usuario u WHERE u.mail = '"+usermail_seguidor+"'",1);
        Integer seguidor = usuarios_seguidor.get(0);
        
        List<Seguidos> seguidos = cn.getListQuery("from modelos.Seguidos WHERE idSeguidos.seguidor.id = "+seguidor+" and idSeguidos.seguido.id = "+idSeguido);
        
        cn.cerrarConexion();
        
        if(seguidos.isEmpty())
            return false;
        else 
            return true;
    }
    
    public static List<Usuario> getSeguidores(String usermail_seguido)
    {
        Conexion cn = new Conexion();
        cn.abrirConexion();
        List<Integer> usuarios_seguido = cn.getListQuery("select u.id from modelos.Usuario u WHERE u.mail = '"+usermail_seguido+"'",1);
        Integer seguido = usuarios_seguido.get(0);
        cn.cerrarConexion();
        
        return getSeguidores_query(seguido);
    }
    
    public static List<Usuario> getSeguidos(String usermail_seguidor)
    {
        Conexion cn = new Conexion();
        cn.abrirConexion();
        List<Integer> usuarios_seguidor = cn.getListQuery("select u.id from modelos.Usuario u WHERE u.mail = '"+usermail_seguidor+"'",1);
        Integer seguidor = usuarios_seguidor.get(0);
        cn.cerrarConexion();
        
        return getSeguidos_query(seguidor);
    }
    
    public static List<Usuario> getSeguidores(int idUsuario_seguido)
    {
        return getSeguidores_query(idUsuario_seguido);
    }
    
    public static List<Usuario> getSeguidos(int idUsuario_seguidor)
    {
        return getSeguidos_query(idUsuario_seguidor);
    }
    
    private static List<Usuario> getSeguidores_query(int idUsuario_seguido)
    {
        Conexion cn = new Conexion();
        cn.abrirConexion();
        List<Usuario> seguidores = cn.getListQuery("select s.idSeguidos.seguidor from modelos.Seguidos s WHERE s.idSeguidos.seguido.id = "+idUsuario_seguido);
        cn.cerrarConexion();
        return seguidores;
    }
  
    private static List<Usuario> getSeguidos_query(int idUsuario_seguidor)
    {
        Conexion cn = new Conexion();
        cn.abrirConexion();
        List<Usuario> seguidos = cn.getListQuery("select s.idSeguidos.seguido from modelos.Seguidos s WHERE s.idSeguidos.seguidor.id = "+idUsuario_seguidor);
        cn.cerrarConexion();
        return seguidos;
    }
    
}
