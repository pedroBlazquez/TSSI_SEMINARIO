package negocio;

import java.util.List;

import conexion.Conexion;
import modelos.UsuarioTipo;

public class UsuarioTipoNegocio {

    public UsuarioTipo getUsuarioTipo(int id) {
        Conexion cn = new Conexion();
        cn.abrirConexion();
        List<Object> usuarioTipoList = cn.getListQuery("FROM UsuarioTipo WHERE id =" + id);
        
        UsuarioTipo ut = (UsuarioTipo)usuarioTipoList.get(0);
        
        cn.cerrarConexion();
        
        return ut;
    }
    
}
