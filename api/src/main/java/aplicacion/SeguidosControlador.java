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
import negocio.SeguidosNegocio;

@RestController
@RequestMapping("/usuario")
public class SeguidosControlador {

    
    @RequestMapping(value = "/seguir", method = RequestMethod.POST)
    public ResponseEntity<?> Seguir(HttpEntity<String> httpEntity, HttpServletRequest request) throws JSONException, IOException {
        try {
            //obtiene objeto json
            JSONObject json = new JSONObject(httpEntity.getBody());    
            //busca en json los atributos
            String idUsuario= json.getString("idUsuario");
            
            String usermail = Token.getMailFromToken(request.getHeader(HEADER_STRING));
            
            return SeguidosNegocio.Seguir(idUsuario, usermail);
            
        } catch (Exception ex) {
            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @RequestMapping(value = "/getSeguimiento/{idUsuario}", method = RequestMethod.GET) //para saber si sigo a determinado usuario
    public ResponseEntity<?> Seguir(@PathVariable("idUsuario") long idUsuario, HttpServletRequest request) throws JSONException, IOException {
        try {
            String usermail = Token.getMailFromToken(request.getHeader(HEADER_STRING));
            
            return SeguidosNegocio.getSeguimiento((int)idUsuario, usermail);
            
        } catch (Exception ex) {
            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @RequestMapping(value = "/getSeguidores/{idUsuario}", method = RequestMethod.GET)//get seguidores de un usuario o mios
    public ResponseEntity<?> getSeguidores(@PathVariable("idUsuario") long idUsuario, HttpServletRequest request) throws JSONException, IOException {
        try {
            String usermail = Token.getMailFromToken(request.getHeader(HEADER_STRING));
            List<Usuario> seguidores = new ArrayList<Usuario>();
            if((int)idUsuario == 0)
                seguidores = SeguidosNegocio.getSeguidores(usermail);
            else
                seguidores = SeguidosNegocio.getSeguidores((int)idUsuario);
                
            return new ResponseEntity<Object>(seguidores,HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @RequestMapping(value = "/getSeguidos/{idUsuario}", method = RequestMethod.GET)//get seguidos por un usuario o por mi
    public ResponseEntity<?> getSeguidos(@PathVariable("idUsuario") long idUsuario, HttpServletRequest request) throws JSONException, IOException {
        try {
            String usermail = Token.getMailFromToken(request.getHeader(HEADER_STRING));
            
            List<Usuario> seguidos = new ArrayList<Usuario>();
            if((int)idUsuario == 0)
                seguidos = SeguidosNegocio.getSeguidos(usermail);
            else
                seguidos = SeguidosNegocio.getSeguidos((int)idUsuario);
                
            return new ResponseEntity<Object>(seguidos,HttpStatus.OK);
            
        } catch (Exception ex) {
            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
