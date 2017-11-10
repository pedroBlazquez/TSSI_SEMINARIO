package negocio;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import aplicacion.Tools;
import conexion.Conexion;
import modelos.Artista;
import modelos.Genero;
import modelos.IntegranteArtista;
import modelos.Usuario;

public class ArtistaNegocio {
    
    public boolean altaArtista(JSONObject data, int artistaTipoId, String mail) throws JSONException {
        Conexion cn = new Conexion();
        JSONArray generosArray = data.getJSONArray("generos");
        //List<Integer> generosLista = new ArrayList();
        UsuarioNegocio usuarioNegocio = new UsuarioNegocio();
        GeneroArtistaNegocio generoArtistaNegocio = new GeneroArtistaNegocio();

        try {
            Artista artista = new ObjectMapper()
                    .readValue(data.toString(), Artista.class);

            cn.abrirConexion();

            Usuario usuario = usuarioNegocio.getUsuarioByMail(mail);
            artista.setUsuario(usuario);
            cn.add(artista);

            cn.cerrarConexion();
            generoArtistaNegocio.altaVariosGenerosArtista(generosArray);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
    
    public Artista getLastAdded() {
        Conexion cn = new Conexion();
        Artista a = null;
        
        cn.abrirConexion();
        List<Object> artistaLista = cn.getListQuery("FROM Artista ORDER BY id DESC", 1);
        if (!artistaLista.isEmpty()) {
            a = (Artista) artistaLista.get(0);
        }
        
        return a;
    }
    public static List<JSONObject> setData(List<Artista> artistas,String usermail) throws JsonProcessingException, JSONException
    {
        Conexion cn = new Conexion();
        cn.abrirConexion();
        List<JSONObject> list = new ArrayList<JSONObject>();
        for(Artista a : artistas)
        {
            JSONObject jobj = Tools.convertObj_toJSON(a);
            
            String idArtista = String.valueOf(a.getId());
            
            List<Genero> generos = cn.getListQuery("select cd.idGeneroArtista.genero from modelos.GeneroArtista cd WHERE cd.idGeneroArtista.artista.id = "+idArtista);
            jobj.put("generos", Tools.convertList_toJSON(generos));
            
            List<IntegranteArtista> integrantes = cn.getListQuery("from modelos.IntegranteArtista WHERE artista.id = "+idArtista);
            jobj.put("integrantes", Tools.convertList_toJSON(integrantes));
            
            jobj.put("seguidores", SeguidosNegocio.getSeguidores(a.getUsuario().getId()).size());
            jobj.put("seguido", SeguidosNegocio.getSeguimiento(a.getUsuario().getId(),usermail));
            
            list.add(jobj);
        }
        cn.cerrarConexion();
        return list;
    }
}
