package negocio;

import java.util.List;

import conexion.Conexion;
import modelos.Genero;

public class GeneroNegocio {

    public Genero getGenero(int id) {
        Conexion cn = new Conexion();
        Genero genero = null;
        
        cn.abrirConexion();
        List<Object> generoResultado = cn.getListQuery("FROM Genero WHERE id = '" + id +"'");
        
        if (generoResultado.size() == 1) {
            genero = (Genero) generoResultado.get(0);
        }
        
        return genero;        
    }
    
}
