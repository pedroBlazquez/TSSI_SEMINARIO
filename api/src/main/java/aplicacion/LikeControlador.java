package aplicacion;

import static aplicacion.autenticacion.SecurityConstants.HEADER_STRING;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import aplicacion.autenticacion.Token;
import conexion.Conexion;
import negocio.LikeNegocio;

@RestController
@RequestMapping("/like")
public class LikeControlador {

    
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> Like(HttpEntity<String> httpEntity, HttpServletRequest request) throws JSONException, IOException {
        try {
            //obtiene objeto json
            JSONObject json = new JSONObject(httpEntity.getBody());    
            //busca en json los atributos
            String id= json.getString("id");
            String tipo= json.getString("tipo");
            
            String usermail = Token.getMailFromToken(request.getHeader(HEADER_STRING));
            
            return LikeNegocio.Like(tipo, id, usermail);
        } catch (Exception ex) {
            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @RequestMapping(value = "/getLikeCount", method = RequestMethod.POST) //cantidad de likes de un objeto
    public ResponseEntity<?> getLikeCount(HttpEntity<String> httpEntity, HttpServletRequest request) throws JSONException, IOException {
        try {
            JSONObject json = new JSONObject(httpEntity.getBody());  
            String id= json.getString("id");
            String tipo= json.getString("tipo");
            Conexion cn = new Conexion();
            cn.abrirConexion();
            int cant_likes = LikeNegocio.getLikeCount(cn,tipo,id);
            cn.cerrarConexion();
            if(cant_likes == 0)
                return new ResponseEntity<Object>(cant_likes,HttpStatus.NO_CONTENT);
            else
                return new ResponseEntity<Object>(cant_likes,HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @RequestMapping(value = "/getUserLike", method = RequestMethod.POST) //like del usuario a un objeto especifico
    public ResponseEntity<?> getUserLike(HttpEntity<String> httpEntity, HttpServletRequest request) throws JSONException, IOException {
        try {
            String usermail = Token.getMailFromToken(request.getHeader(HEADER_STRING));
            JSONObject json = new JSONObject(httpEntity.getBody());  
            String id= json.getString("id");
            String tipo= json.getString("tipo");
            Conexion cn = new Conexion();
            cn.abrirConexion();
            boolean exists = LikeNegocio.getUserLike(cn,tipo,id,usermail);
            cn.cerrarConexion();
            if(!exists)
                return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
            else
                return new ResponseEntity<Object>(HttpStatus.OK);
            
        } catch (Exception ex) {
            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/getUserLikes", method = RequestMethod.GET) //todos los likes del usuario
    public ResponseEntity<?> getUserLike( HttpServletRequest request) throws JSONException, IOException {
        try {
            String usermail = Token.getMailFromToken(request.getHeader(HEADER_STRING));
            return LikeNegocio.getUserLikes(usermail);
            
        } catch (Exception ex) {
            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
  

}
