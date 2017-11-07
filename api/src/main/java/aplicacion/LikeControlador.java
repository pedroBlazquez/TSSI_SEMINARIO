package aplicacion;

import static aplicacion.autenticacion.SecurityConstants.HEADER_STRING;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import aplicacion.autenticacion.Token;
import modelos.Usuario;
import negocio.LikeNegocio;
import negocio.SeguidosNegocio;

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
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @RequestMapping(value = "/getLikes", method = RequestMethod.POST) //para saber si sigo a determinado usuario
    public ResponseEntity<?> getLikes(HttpEntity<String> httpEntity, HttpServletRequest request) throws JSONException, IOException {
        try {
            JSONObject json = new JSONObject(httpEntity.getBody());  
            String id= json.getString("id");
            String tipo= json.getString("tipo");
            return LikeNegocio.getLikes(tipo,id);
        } catch (Exception ex) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @RequestMapping(value = "/getUserLike", method = RequestMethod.POST) //para saber si sigo a determinado usuario
    public ResponseEntity<?> getUserLike(HttpEntity<String> httpEntity, HttpServletRequest request) throws JSONException, IOException {
        try {
            String usermail = Token.getMailFromToken(request.getHeader(HEADER_STRING));
            JSONObject json = new JSONObject(httpEntity.getBody());  
            String id= json.getString("id");
            String tipo= json.getString("tipo");
            return LikeNegocio.getUserLike(tipo,id,usermail);
            
        } catch (Exception ex) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
  

}
