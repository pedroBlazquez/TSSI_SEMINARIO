package negocio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import conexion.Conexion;
import modelos.Artista;
import modelos.Usuario;

public class ArtistaNegocio {
    
    public boolean altaArtista(JSONObject data, int artistaTipoId, String mail) throws JSONException {
        Conexion cn = new Conexion();
        JSONArray generosArray = data.getJSONArray("generos");
        List<Integer> generosLista = new ArrayList();
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
    
}
