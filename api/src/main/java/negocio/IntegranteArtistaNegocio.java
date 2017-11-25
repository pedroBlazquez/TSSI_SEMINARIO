package negocio;

import java.util.ArrayList;
import java.util.List;

import conexion.Conexion;
import modelos.Artista;
import modelos.IntegranteArtista;
import modelos.IntegranteRol;
import modelos.Usuario;

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
    public boolean altaListaIntegrantes(List<IntegranteArtista> integrantesListaObjs, List<Integer> roles,String usermail) {
        Conexion cn = new Conexion();
        
        try {
        	cn.abrirConexion();
            IntegranteRolNegocio integranteRolNegocio = new IntegranteRolNegocio();
            Usuario usuario = UsuarioNegocio.getUsuarioByMail(cn,usermail);
            Artista art = (Artista) cn.getListQuery("from modelos.Artista WHERE usuario.id = "+usuario.getId()).get(0);
            List<Object> lista = new ArrayList<Object>();
            
            for (int i = 0; i < integrantesListaObjs.size(); i++) {
                IntegranteRol integRol = integranteRolNegocio.getIntegranteRol(roles.get(i));
                integrantesListaObjs.get(i).setRol(integRol);
                integrantesListaObjs.get(i).setArtista(art);
            }
            lista.addAll(integrantesListaObjs);

            cn.deleteList(cn.getListQuery("from modelos.IntegranteArtista WHERE artista.id = "+art.getId()));
            cn.addSeveral(lista);
            cn.cerrarConexion();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
