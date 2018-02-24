package aplicacion;

import org.springframework.web.bind.annotation.*;

import aplicacion.autenticacion.Token;
import modelos.Artista;
import modelos.Publicacion;
import negocio.CompartirNegocio;
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
    public ResponseEntity<?> getPublicacion(@PathVariable("publicacion") long idpublicacion, HttpServletRequest request) {
        try {
            Conexion cn = new Conexion();
            cn.abrirConexion();
            List<Publicacion> publicaciones = cn.getListQuery("from modelos.Publicacion p JOIN FETCH p.artista a WHERE p.id = "+idpublicacion);
            
            if (publicaciones.isEmpty()) {
                cn.cerrarConexion();
                return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
                // You many decide to return HttpStatus.NOT_FOUND
            }

            String usermail = Token.getMailFromToken(request.getHeader(HEADER_STRING));
            List<JSONObject> jobj_list = PublicacionNegocio.setData(cn,publicaciones, usermail,true);
            cn.cerrarConexion();
            return new ResponseEntity<Object>(jobj_list.toString(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @RequestMapping(value = "/getArtista/{artista}", method = RequestMethod.GET)
    public ResponseEntity<?> getPublicacionesArtista(@PathVariable("artista") long idartista, HttpServletRequest request) {
        try {
            Conexion cn = new Conexion();
            cn.abrirConexion();
            List<Publicacion> publicaciones = cn.getListQuery("from modelos.Publicacion WHERE artista.id = "+idartista + " order by fechaPublicacion desc");
            
            if (publicaciones.isEmpty()) {
                cn.cerrarConexion();
                return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
                // You many decide to return HttpStatus.NOT_FOUND
            }
            
            String usermail = Token.getMailFromToken(request.getHeader(HEADER_STRING));
            List<JSONObject> jobj_list = PublicacionNegocio.setData(cn,publicaciones, usermail,false);
            cn.cerrarConexion();
            return new ResponseEntity<Object>(jobj_list.toString(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /*@RequestMapping(value = "/getArtista/{artista}", method = RequestMethod.GET)
    public ResponseEntity<?> getPublicacionesArtista(@PathVariable("artista") long idartista, HttpServletRequest request) {
        try {
            String usermail = Token.getMailFromToken(request.getHeader(HEADER_STRING));

            Conexion cn = new Conexion();
            cn.abrirConexion();
            
            int idUsuario = 0;
            List<Artista> artistas = cn.getListQuery("from modelos.Artista WHERE id = "+idartista);
            if (artistas.isEmpty()) {
                idUsuario = artistas.get(0).getUsuario().getId();
            }
            
            ResponseEntity<Object> response = CompartirNegocio.getCompartidosUsuario(cn,(int)idUsuario,usermail);
            cn.cerrarConexion();
            return response;
        } catch (Exception ex) {
            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/
    
    
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
    
    @RequestMapping(value = "/", method = RequestMethod.PUT) //utilizo POST para testear, porque el PUT me esta tirando 403 Invalid CORS request en PostMan
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
    
    @RequestMapping(value = "/", method = RequestMethod.DELETE) //utilizo POST para testear, porque el DELETE me esta tirando 403 Invalid CORS request en PostMan
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
