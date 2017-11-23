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
            
            Usuario seguidor = UsuarioNegocio.getUsuarioByMail(cn, usermail_seguidor);
            Usuario seguido = UsuarioNegocio.getById(cn, Integer.parseInt(idSeguido));
            
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
    
    public static boolean getSeguimiento(Conexion cn,int idSeguido, String usermail_seguidor)
    {
        //Conexion cn = new Conexion();
        //cn.abrirConexion();
        
        Integer seguidor = UsuarioNegocio.getIdUsuarioByMail(cn, usermail_seguidor);
        
        List<Seguidos> seguidos = cn.getListQuery("from modelos.Seguidos WHERE idSeguidos.seguidor.id = "+seguidor+" and idSeguidos.seguido.id = "+idSeguido);
        
        //cn.cerrarConexion();
        
        if(seguidos.isEmpty())
            return false;
        else 
            return true;
    }
    
    public static List<Usuario> getSeguidores(Conexion cn,String usermail_seguido)
    {
        //Conexion cn = new Conexion();
        //cn.abrirConexion();
        Integer seguido = UsuarioNegocio.getIdUsuarioByMail(cn, usermail_seguido);
        //cn.cerrarConexion();
        
        return getSeguidores_query(cn,seguido);
    }
    
    public static List<Usuario> getSeguidos(Conexion cn,String usermail_seguidor)
    {
        //Conexion cn = new Conexion();
        //cn.abrirConexion();
        Integer seguidor = UsuarioNegocio.getIdUsuarioByMail(cn, usermail_seguidor);
        //cn.cerrarConexion();
        
        return getSeguidos_query(cn,seguidor);
    }
    
    public static List<Usuario> getSeguidores(Conexion cn, int idUsuario_seguido)
    {
        return getSeguidores_query(cn,idUsuario_seguido);
    }
    public static Integer getCountSeguidores(Conexion cn, int idUsuario_seguido)
    {
        return getCountSeguidores_query(cn,idUsuario_seguido);
    }
    
    public static List<Usuario> getSeguidos(Conexion cn,int idUsuario_seguidor)
    {
        return getSeguidos_query(cn,idUsuario_seguidor);
    }
    
    private static List<Usuario> getSeguidores_query(Conexion cn,int idUsuario_seguido)
    {
        //Conexion cn = new Conexion();
        //cn.abrirConexion();
        List<Usuario> seguidores = cn.getListQuery("select s.idSeguidos.seguidor from modelos.Seguidos s WHERE s.idSeguidos.seguido.id = "+idUsuario_seguido);
        //cn.cerrarConexion();
        return seguidores;
    }
    private static Integer getCountSeguidores_query(Conexion cn,int idUsuario_seguido)
    {
        //Conexion cn = new Conexion();
        //cn.abrirConexion();
        List<Long> seguidores = cn.getListQuery("select count(s.idSeguidos.seguidor.id) from modelos.Seguidos s WHERE s.idSeguidos.seguido.id = "+idUsuario_seguido);
        //cn.cerrarConexion();
        Long count = seguidores.get(0);
        return count != null ? count.intValue() : null;
    }
  
    private static List<Usuario> getSeguidos_query(Conexion cn,int idUsuario_seguidor)
    {
        //Conexion cn = new Conexion();
        //cn.abrirConexion();
        List<Usuario> seguidos = cn.getListQuery("select s.idSeguidos.seguido from modelos.Seguidos s WHERE s.idSeguidos.seguidor.id = "+idUsuario_seguidor);
        //cn.cerrarConexion();
        return seguidos;
    }
    
}
