package aplicacion;

import org.springframework.web.bind.annotation.*;

import aplicacion.autenticacion.Token;
import modelos.Album;
import modelos.Disco;
import negocio.AlbumNegocio;
import conexion.Conexion;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static aplicacion.autenticacion.SecurityConstants.HEADER_STRING;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/albums")
public class AlbumControlador {

    @RequestMapping(value = "/{album}", method = RequestMethod.GET)
    public ResponseEntity<?> getAlbum(@PathVariable("album") long idalbum) {
        try {
            Conexion cn = new Conexion();
            cn.abrirConexion();
            List<Album> albums = cn.getListQuery("from modelos.Album WHERE id = "+idalbum);
            cn.cerrarConexion();
            if (albums.isEmpty()) {
                return new ResponseEntity(HttpStatus.NO_CONTENT);
                // You many decide to return HttpStatus.NOT_FOUND
            }
            return new ResponseEntity<Album>(albums.get(0), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @RequestMapping(value = "/getArtista/{artista}", method = RequestMethod.GET)
    public ResponseEntity<?> getAlbumArtista(@PathVariable("artista") long idartista) {
        try {
            Conexion cn = new Conexion();
            cn.abrirConexion();
            List<Album> albums = cn.getListQuery("from modelos.Album WHERE artista.id = "+idartista);
            cn.cerrarConexion();
            if (albums.isEmpty()) {
                return new ResponseEntity(HttpStatus.NO_CONTENT);
                // You many decide to return HttpStatus.NOT_FOUND
            }
            return new ResponseEntity<Album>(albums.get(0), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
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
                return new ResponseEntity(HttpStatus.NO_CONTENT);
                // You many decide to return HttpStatus.NOT_FOUND
            }
            return new ResponseEntity<List<Disco>>(cd, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
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
            Date fechaPublicacion= Tools.DateFormatter(json.getString("fechaPublicacion"));
            ArrayList<String> discos = Tools.Convert_jsonArray_toArrayString(json.getJSONArray("discos"));
            //busca mail de usuario
            String usermail = Token.getMailFromToken(request.getHeader(HEADER_STRING));
            
            return AlbumNegocio.CreateAlbum(nombre, fechaPublicacion, discos, usermail);
            
        } catch (Exception ex) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
 
    // -------------------Update-------------------------------------------
    
    @RequestMapping(value = "/update", method = RequestMethod.POST) //utilizo POST para testear, porque el PUT me esta tirando 403 Invalid CORS request en PostMan
    public ResponseEntity<?> updateAlbum(HttpEntity<String> httpEntity, HttpServletRequest request) throws JSONException, IOException {
        try {
            //obtiene objeto json
            JSONObject json = new JSONObject(httpEntity.getBody());    
            //busca en json los atributos
            String idAlbum= json.getString("idAlbum");
            String nombre= json.getString("nombre");
            Date fechaPublicacion= Tools.DateFormatter(json.getString("fechaPublicacion"));
            ArrayList<String> discos = Tools.Convert_jsonArray_toArrayString(json.getJSONArray("discos"));
            
            return AlbumNegocio.UpdateAlbum(idAlbum,nombre, fechaPublicacion, discos);
        } catch (Exception ex) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    // -------------------Delete-------------------------------------------
    
    @RequestMapping(value = "/delete", method = RequestMethod.POST) //utilizo POST para testear, porque el DELETE me esta tirando 403 Invalid CORS request en PostMan
    public ResponseEntity<?> deleteAlbum(HttpEntity<String> httpEntity, HttpServletRequest request) throws JSONException, IOException {
        try {
            //obtiene objeto json
            JSONObject json = new JSONObject(httpEntity.getBody());    
            //busca en json los atributos
            String idAlbum= json.getString("idAlbum");
            return AlbumNegocio.DeleteAlbum(idAlbum);
        } catch (Exception ex) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
  
   
}
