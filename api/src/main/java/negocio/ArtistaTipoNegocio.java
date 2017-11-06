package negocio;

import java.util.List;

import conexion.Conexion;
import modelos.ArtistaTipo;

public class ArtistaTipoNegocio {

    public ArtistaTipo getArtistaTipo(int id) {
        Conexion cn = new Conexion();
        
        cn.abrirConexion();
        List<Object> usuarioTipoList = cn.getListQuery("FROM ArtistaTipo WHERE id =" + id);
        ArtistaTipo at = (ArtistaTipo)usuarioTipoList.get(0);
        
        cn.cerrarConexion();
        
        return at;
    }
    
}
