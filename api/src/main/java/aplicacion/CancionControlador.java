package aplicacion;

import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import aplicacion.autenticacion.Token;
import modelos.Cancion;
import modelos.Disco;
import modelos.Genero;
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
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/canciones")
public class CancionControlador {

    @RequestMapping(value = "/{cancion}", method = RequestMethod.GET)
    public ResponseEntity<?> getCancion(@PathVariable("cancion") long idcancion, HttpServletRequest request) {
        try {
            Conexion cn = new Conexion();
            cn.abrirConexion();
            List<Cancion> canciones = cn.getListQuery("select c from modelos.Cancion c JOIN FETCH c.artista a WHERE c.id = "+idcancion,1);
            cn.cerrarConexion();
            
            if (canciones.isEmpty()) {
                return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
                // You many decide to return HttpStatus.NOT_FOUND
            }

            String usermail = Token.getMailFromToken(request.getHeader(HEADER_STRING));
            List<JSONObject> jobj_list = CancionNegocio.setData(canciones,usermail,true);
            
            return new ResponseEntity<Object>(jobj_list.toString(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @RequestMapping(value = "/getArtista/{artista}", method = RequestMethod.GET)
    public ResponseEntity<?> getCancionesArtista(@PathVariable("artista") long idartista, HttpServletRequest request) {
        try {
            Conexion cn = new Conexion();
            cn.abrirConexion();
            List<Cancion> canciones = cn.getListQuery("from modelos.Cancion WHERE artista.id = "+idartista);
            cn.cerrarConexion();
            if (canciones.isEmpty()) {
                cn.cerrarConexion();
                return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
                // You many decide to return HttpStatus.NOT_FOUND
            }

            String usermail = Token.getMailFromToken(request.getHeader(HEADER_STRING));
            List<JSONObject> jobj_list = CancionNegocio.setData(canciones,usermail,false);
            
            return new ResponseEntity<Object>(jobj_list.toString(), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @RequestMapping(value = "/getDiscos/{cancion}", method = RequestMethod.GET)
    public ResponseEntity<?> getDiscosCancion(@PathVariable("cancion") long idCancion) {
        try {
            Conexion cn = new Conexion();
            cn.abrirConexion();
            List<Disco> cd = cn.getListQuery("select cd.idCancionDisco.disco from modelos.CancionDisco cd WHERE cd.idCancionDisco.cancion.id = "+(int)idCancion);
            
            
            List<JSONObject> jobj_list = Tools.convertList_toListJSON(cd);
            
            cn.cerrarConexion();
            if (cd.isEmpty()) {
                return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
                // You many decide to return HttpStatus.NOT_FOUND
            }
            return new ResponseEntity<Object>(cd, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    // -------------------Create-------------------------------------------
    
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> createCancion(HttpEntity<String> httpEntity, HttpServletRequest request) throws JSONException, IOException {
        try {
            //obtiene objeto json
            JSONObject json = new JSONObject(httpEntity.getBody());    
            //busca en json los atributos
            String archivo = "path_archivo";
            String nombre= json.getString("nombre");
            String genero= json.getString("genero");
            //busca mail de usuario
            String usermail = Token.getMailFromToken(request.getHeader(HEADER_STRING));
            
            return CancionNegocio.CreateCancion(nombre,genero,archivo,usermail);
            
        } catch (Exception ex) {
            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
 
    // -------------------Update-------------------------------------------
    
    @RequestMapping(value = "/", method = RequestMethod.PUT) //utilizo POST para testear, porque el PUT me esta tirando 403 Invalid CORS request en PostMan
    public ResponseEntity<?> updateCancion(HttpEntity<String> httpEntity, HttpServletRequest request) throws JSONException, IOException {
        try {
            //obtiene objeto json
            JSONObject json = new JSONObject(httpEntity.getBody());    
            //busca en json los atributos
            String idDisco= json.getString("idCancion");
            String nombre= json.getString("nombre");
            String genero= json.getString("genero");
            
            return CancionNegocio.UpdateCancion(idDisco,nombre,genero);
        } catch (Exception ex) {
            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    // -------------------Delete-------------------------------------------
    
    @RequestMapping(value = "/", method = RequestMethod.DELETE) //utilizo POST para testear, porque el DELETE me esta tirando 403 Invalid CORS request en PostMan
    public ResponseEntity<?> deleteCancion(HttpEntity<String> httpEntity, HttpServletRequest request) throws JSONException, IOException {
        try {
            //obtiene objeto json
            JSONObject json = new JSONObject(httpEntity.getBody());    
            //busca en json los atributos
            String idCancion= json.getString("idCancion");
            return CancionNegocio.DeleteCancion(idCancion);
        } catch (Exception ex) {
            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
   
}
