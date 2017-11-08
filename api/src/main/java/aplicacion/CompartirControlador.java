package aplicacion;

import static aplicacion.autenticacion.SecurityConstants.HEADER_STRING;

import java.io.IOException;

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
import negocio.CompartirNegocio;

@RestController
@RequestMapping("/compartir")
public class CompartirControlador {

    
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Object> Compartir(HttpEntity<String> httpEntity, HttpServletRequest request) throws JSONException, IOException {
        try {
            //obtiene objeto json
            JSONObject json = new JSONObject(httpEntity.getBody());    
            //busca en json los atributos
            String id= json.getString("id");
            String tipo= json.getString("tipo");
            
            String usermail = Token.getMailFromToken(request.getHeader(HEADER_STRING));
            
            return CompartirNegocio.Compartir(tipo, id, usermail);
        } catch (Exception ex) {
            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @RequestMapping(value = "/getCompartidos/{idUsuario}", method = RequestMethod.GET) //obtener objetos compartidos por un Usuario determinado
    public ResponseEntity<?> getCompartido(@PathVariable("idUsuario") long idUsuario, HttpServletRequest request) throws JSONException, IOException {
        try {

            String usermail = Token.getMailFromToken(request.getHeader(HEADER_STRING));
            
            
            return CompartirNegocio.getCompartidosUsuario((int)idUsuario,usermail);
            
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @RequestMapping(value = "/getCompartidoUsuario", method = RequestMethod.POST) //compartido del usuario a un objeto especifico
    public ResponseEntity<?> getUserLike(HttpEntity<String> httpEntity, HttpServletRequest request) throws JSONException, IOException {
        try {
            String usermail = Token.getMailFromToken(request.getHeader(HEADER_STRING));
            JSONObject json = new JSONObject(httpEntity.getBody());  
            String id= json.getString("id");
            String tipo= json.getString("tipo");
            return CompartirNegocio.getCompartidoUsuario(tipo,id,usermail);
            
        } catch (Exception ex) {
            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
  

}
