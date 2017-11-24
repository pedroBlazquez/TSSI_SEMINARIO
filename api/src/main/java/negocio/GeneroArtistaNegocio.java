package negocio;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import conexion.Conexion;
import modelos.Artista;
import modelos.Genero;
import modelos.GeneroArtista;

public class GeneroArtistaNegocio {

    public void altaVariosGenerosArtista(Conexion cn, JSONArray ids) throws JSONException {
        ArtistaNegocio artistaNegocio = new ArtistaNegocio();
        Artista artista = artistaNegocio.getLastAdded();
        
        for (int i = 0; i < ids.length(); i++) {
            this.altaGeneroArtista(cn,ids.getInt(i), artista);
        }
    }
    public void altaVariosGenerosArtista(Conexion cn, JSONArray ids,Artista artista) throws JSONException {
        
        for (int i = 0; i < ids.length(); i++) {
            this.altaGeneroArtista(cn,ids.getInt(i), artista);
        }
    }
    
    public void altaGeneroArtista(Conexion cn,int id, Artista artista) {
        //Conexion cn = new Conexion();
        GeneroNegocio generoNegocio = new GeneroNegocio();
        Genero genero = generoNegocio.getGenero(id);
        GeneroArtista generoArtista = new GeneroArtista(genero, artista);
        
        //cn.abrirConexion();
        cn.add(generoArtista);
        //cn.cerrarConexion();
    }
    
}
