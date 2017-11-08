package negocio;

import java.util.ArrayList;
import java.util.List;

import conexion.Conexion;
import modelos.Artista;
import modelos.IntegranteArtista;
import modelos.IntegranteRol;

public class IntegranteArtistaNegocio {

    public boolean altaListaIntegrantes(List<IntegranteArtista> integrantesListaObjs, List<Integer> roles) {
        Conexion cn = new Conexion();
        ArtistaNegocio artistaNegocio = new ArtistaNegocio();
        IntegranteRolNegocio integranteRolNegocio = new IntegranteRolNegocio();
        Artista art = artistaNegocio.getLastAdded();
        List<Object> lista = new ArrayList<Object>();
        
        try {
            for (int i = 0; i < integrantesListaObjs.size(); i++) {
                IntegranteRol integRol = integranteRolNegocio.getIntegranteRol(roles.get(i));
                integrantesListaObjs.get(i).setRol(integRol);
                integrantesListaObjs.get(i).setArtista(art);
            }
            lista.addAll(integrantesListaObjs);
            cn.abrirConexion();
            cn.addSeveral(lista);
            cn.cerrarConexion();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
}
