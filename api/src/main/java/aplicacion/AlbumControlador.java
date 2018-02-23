package aplicacion;

import org.springframework.web.bind.annotation.*;

import aplicacion.autenticacion.Token;
import modelos.Album;
import modelos.Cancion;
import modelos.Disco;
import modelos.Genero;
import negocio.AlbumNegocio;
import negocio.CancionNegocio;
import conexion.Conexion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static aplicacion.autenticacion.SecurityConstants.HEADER_STRING;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/albums")
public class AlbumControlador {

    @RequestMapping(value = "/{album}", method = RequestMethod.GET)
    public ResponseEntity<?> getAlbum(@PathVariable("album") long idalbum, HttpServletRequest request) {
        try {
            Conexion cn = new Conexion();
            cn.abrirConexion();
            List<Album> albums = cn.getListQuery("from modelos.Album a JOIN FETCH a.artista ar WHERE a.id = "+idalbum);
            
            
            if (albums.isEmpty()) {
                cn.cerrarConexion();
                return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
                // You many decide to return HttpStatus.NOT_FOUND
            }

            String usermail = Token.getMailFromToken(request.getHeader(HEADER_STRING));
            List<JSONObject> jobj_list = AlbumNegocio.setData(cn,albums,usermail,true,true);
            
            cn.cerrarConexion();
            return new ResponseEntity<Object>(jobj_list.toString(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @RequestMapping(value = "/getArtista/{artista}", method = RequestMethod.GET)
    public ResponseEntity<?> getAlbumArtista(@PathVariable("artista") long idartista, HttpServletRequest request) {
        try {
            Conexion cn = new Conexion();
            cn.abrirConexion();
            List<Album> albums = cn.getListQuery("from modelos.Album WHERE artista.id = "+idartista+ " order by fechaPublicacion desc");
            
            if (albums.isEmpty()) {
                cn.cerrarConexion();
                return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
                // You many decide to return HttpStatus.NOT_FOUND
            }

            String usermail = Token.getMailFromToken(request.getHeader(HEADER_STRING));
            List<JSONObject> jobj_list = AlbumNegocio.setData(cn,albums,usermail,true,false);
            cn.cerrarConexion();
            return new ResponseEntity<Object>(jobj_list.toString(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @RequestMapping(value = "/getDiscos/{album}", method = RequestMethod.GET)
    public ResponseEntity<?> getDiscosAlbum(@PathVariable("album") long idalbum) {
        try {
            Conexion cn = new Conexion();
            cn.abrirConexion();
            List<Disco> cd = cn.getListQuery("select cd.idDiscoAlbum.disco from modelos.DiscoAlbum cd WHERE cd.idDiscoAlbum.album.id = "+(int)idalbum);
           
            cn.cerrarConexion();
            if (cd.isEmpty()) {
                return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
                // You many decide to return HttpStatus.NOT_FOUND
            }
            return new ResponseEntity<List<Disco>>(cd, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    // -------------------Create-------------------------------------------
    
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> createAlbum(HttpEntity<String> httpEntity, HttpServletRequest request) throws JSONException, IOException {
        try {
            //obtiene objeto json
            JSONObject json = new JSONObject(httpEntity.getBody());    
            //busca en json los atributos
            String nombre= json.getString("nombre");
            String portada= "";
            try { portada = json.getString("portada");}catch (JSONException x){}
            
            ArrayList<String> discos = Tools.Convert_jsonArray_toArrayString(json.getJSONArray("discos"));
            //busca mail de usuario
            String usermail = Token.getMailFromToken(request.getHeader(HEADER_STRING));
            
            return AlbumNegocio.CreateAlbum(nombre, discos, usermail,portada);
            
        } catch (Exception ex) {
            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
 
    // -------------------Update-------------------------------------------
    
    @RequestMapping(value = "/", method = RequestMethod.PUT) //utilizo POST para testear, porque el PUT me esta tirando 403 Invalid CORS request en PostMan
    public ResponseEntity<?> updateAlbum(HttpEntity<String> httpEntity, HttpServletRequest request) throws JSONException, IOException {
        try {
            //obtiene objeto json
            JSONObject json = new JSONObject(httpEntity.getBody());    
            //busca en json los atributos
            String idAlbum= json.getString("idAlbum");
            String nombre= json.getString("nombre");
            String portada= "";
            try { portada = json.getString("portada");}catch (JSONException x){}
            ArrayList<String> discos = Tools.Convert_jsonArray_toArrayString(json.getJSONArray("discos"));
            
            return AlbumNegocio.UpdateAlbum(idAlbum,nombre,discos,portada);
        } catch (Exception ex) {
            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    // -------------------Delete-------------------------------------------
    
    @RequestMapping(value = "/", method = RequestMethod.DELETE) //utilizo POST para testear, porque el DELETE me esta tirando 403 Invalid CORS request en PostMan
    public ResponseEntity<?> deleteAlbum(HttpEntity<String> httpEntity, HttpServletRequest request) throws JSONException, IOException {
        try {
            //obtiene objeto json
            JSONObject json = new JSONObject(httpEntity.getBody());    
            //busca en json los atributos
            String idAlbum= json.getString("idAlbum");
            return AlbumNegocio.DeleteAlbum(idAlbum);
        } catch (Exception ex) {
            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
  
   
}
