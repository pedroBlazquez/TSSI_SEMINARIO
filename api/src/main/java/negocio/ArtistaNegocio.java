package negocio;

import java.util.ArrayList;
import java.util.Date;
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

    final String FECHA_INICIO_JSON_FIELD = "fechaInicio";
    
    public boolean altaArtista(JSONObject data, String mail) throws JSONException {
        Conexion cn = new Conexion();
        JSONArray generosArray = data.getJSONArray("generos");
        //List<Integer> generosLista = new ArrayList();
        GeneroArtistaNegocio generoArtistaNegocio = new GeneroArtistaNegocio();

        try {

            Date fechaInicio = Tools.DateFormatter(data.getString(FECHA_INICIO_JSON_FIELD));
            data.remove(FECHA_INICIO_JSON_FIELD);
            
            
            Artista artista = new ObjectMapper()
                    .readValue(data.toString(), Artista.class);

            cn.abrirConexion();
            artista.setFechaInicio(fechaInicio);
            Usuario usuario = UsuarioNegocio.getUsuarioByMail(cn,mail);
            artista.setUsuario(usuario);
            cn.add(artista);

            generoArtistaNegocio.altaVariosGenerosArtista(cn,generosArray);

            cn.cerrarConexion();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
    
    public boolean updArtista(JSONObject data, String usermail) throws JSONException {
        Conexion cn = new Conexion();
        JSONArray generosArray = data.getJSONArray("generos");
        GeneroArtistaNegocio generoArtistaNegocio = new GeneroArtistaNegocio();

        try {

            Date fechaInicio = Tools.DateFormatter(data.getString(FECHA_INICIO_JSON_FIELD));
            data.remove(FECHA_INICIO_JSON_FIELD);
            
            Artista artistaNuevo = new ObjectMapper()
                    .readValue(data.toString(), Artista.class);

            cn.abrirConexion();

            Usuario usuario = UsuarioNegocio.getUsuarioByMail(cn,usermail);
            Artista artista = (Artista) cn.getListQuery("from modelos.Artista WHERE usuario.id = "+usuario.getId()).get(0);
            artista.setNombreFantasia(artistaNuevo.getNombreFantasia());
            artista.setDescripcion(artistaNuevo.getDescripcion());
            artista.setFechaInicio(fechaInicio);
            
            cn.update(artista);
            
            cn.deleteList(cn.getListQuery("from modelos.GeneroArtista WHERE idGeneroArtista.artista.id = "+artista.getId()));
            
            generoArtistaNegocio.altaVariosGenerosArtista(cn,generosArray,artista);

            cn.cerrarConexion();
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
        cn.cerrarConexion();
        return a;
    }
    public static List<JSONObject> setData(Conexion cn,List<Artista> artistas,String usermail,boolean w_integrantes,boolean w_usuario) throws JsonProcessingException, JSONException
    {
        //Conexion cn = new Conexion();
        //cn.abrirConexion();
        List<JSONObject> list = new ArrayList<JSONObject>();
        for(Artista a : artistas)
        {
            int idUsuario = a.getUsuario().getId();
            if(!w_usuario)
                a.setUsuario(null);
                
            JSONObject jobj = Tools.convertObj_toJSON(a);
            
            String idArtista = String.valueOf(a.getId());
            
            List<Genero> generos = cn.getListQuery("select cd.idGeneroArtista.genero from modelos.GeneroArtista cd WHERE cd.idGeneroArtista.artista.id = "+idArtista);
            jobj.put("generos", Tools.convertList_toJSON(generos));
            
            if(w_integrantes)
            {
                List<IntegranteArtista> integrantes = cn.getListQuery("from modelos.IntegranteArtista WHERE artista.id = "+idArtista);
                jobj.put("integrantes", Tools.convertList_toJSON(integrantes));
            }
            
            jobj.put("seguidores", SeguidosNegocio.getCountSeguidores(cn,idUsuario));
            jobj.put("seguido", SeguidosNegocio.getSeguimiento(cn,idUsuario,usermail));

            jobj.put("compartido", CompartirNegocio.getCompartidoUsuario(cn,"Artista",idArtista,usermail));
            
            jobj.put("object_type", "Artista");
            
            list.add(jobj);
        }
        //cn.cerrarConexion();
        return list;
    }
}
