package negocio;

import java.util.List;

import conexion.Conexion;
import modelos.IntegranteRol;

public class IntegranteRolNegocio {

    public IntegranteRol getIntegranteRol(int id) {
        Conexion cn = new Conexion();
        IntegranteRol ir = null;
        
        cn.abrirConexion();
        List<Object> rol = cn.getListQuery("FROM IntegranteRol WHERE id = " + id);
        
        if (!rol.isEmpty()) {
            ir = (IntegranteRol) rol.get(0);
        }
        cn.cerrarConexion();
        return ir;
    }
    
}
