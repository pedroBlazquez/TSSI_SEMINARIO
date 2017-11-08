package aplicacion;

import org.springframework.web.bind.annotation.*;

import aplicacion.autenticacion.Token;
import modelos.Publicacion;
import negocio.PublicacionNegocio;
import conexion.Conexion;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static aplicacion.autenticacion.SecurityConstants.HEADER_STRING;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/publicaciones")
public class PublicacionControlador {

    @RequestMapping(value = "/{publicacion}", method = RequestMethod.GET)
    public ResponseEntity<?> getPublicacion(@PathVariable("publicacion") long idpublicacion) {
        try {
            Conexion cn = new Conexion();
            cn.abrirConexion();
            List<Publicacion> publicaciones = cn.getListQuery("from modelos.Publicacion WHERE id = "+idpublicacion);
            cn.cerrarConexion();
            if (publicaciones.isEmpty()) {
                return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
                // You many decide to return HttpStatus.NOT_FOUND
            }
            return new ResponseEntity<Publicacion>(publicaciones.get(0), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @RequestMapping(value = "/getArtista/{artista}", method = RequestMethod.GET)
    public ResponseEntity<?> getPublicacionesArtista(@PathVariable("artista") long idartista) {
        try {
            Conexion cn = new Conexion();
            cn.abrirConexion();
            List<Publicacion> publicaciones = cn.getListQuery("from modelos.Publicacion WHERE artista.id = "+idartista);
            cn.cerrarConexion();
            if (publicaciones.isEmpty()) {
                return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
                // You many decide to return HttpStatus.NOT_FOUND
            }
            return new ResponseEntity<List<Publicacion>>(publicaciones, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    // -------------------Create-------------------------------------------
    
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> createPublicacion(HttpEntity<String> httpEntity, HttpServletRequest request) throws JSONException, IOException {
        try {
            //obtiene objeto json
            JSONObject json = new JSONObject(httpEntity.getBody());    
            //busca en json los atributos
            String texto= json.getString("texto");
           
            //busca mail de usuario
            String usermail = Token.getMailFromToken(request.getHeader(HEADER_STRING));
            
            return PublicacionNegocio.CreatePublicacion(texto,usermail);
            
        } catch (Exception ex) {
            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
 
    // -------------------Update-------------------------------------------
    
    @RequestMapping(value = "/update", method = RequestMethod.POST) //utilizo POST para testear, porque el PUT me esta tirando 403 Invalid CORS request en PostMan
    public ResponseEntity<?> updatePublicacion(HttpEntity<String> httpEntity, HttpServletRequest request) throws JSONException, IOException {
        try {
            //obtiene objeto json
            JSONObject json = new JSONObject(httpEntity.getBody());    
            //busca en json los atributos
            String idPublicacion= json.getString("idPublicacion");
            String texto= json.getString("texto");
            
            return PublicacionNegocio.UpdatePublicacion(idPublicacion,texto);
        } catch (Exception ex) {
            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    // -------------------Delete-------------------------------------------
    
    @RequestMapping(value = "/delete", method = RequestMethod.POST) //utilizo POST para testear, porque el DELETE me esta tirando 403 Invalid CORS request en PostMan
    public ResponseEntity<?> deletePublicacion(HttpEntity<String> httpEntity, HttpServletRequest request) throws JSONException, IOException {
        try {
            //obtiene objeto json
            JSONObject json = new JSONObject(httpEntity.getBody());    
            //busca en json los atributos
            String idPublicacion= json.getString("idPublicacion");
            return PublicacionNegocio.DeletePublicacion(idPublicacion);
        } catch (Exception ex) {
            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
   
}