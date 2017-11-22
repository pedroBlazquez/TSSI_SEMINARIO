package aplicacion;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import aplicacion.autenticacion.Token;
import negocio.InicioNegocio;

import static aplicacion.autenticacion.SecurityConstants.HEADER_STRING;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
public class InicioControlador {

    @RequestMapping("/")
    public String login(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        String user = "no anduvo";

        user = Token.getMailFromToken(token);

        return user;
    }
    
    @RequestMapping(value = "/inicio", method = RequestMethod.GET)
    public ResponseEntity<Object> getNovedades(HttpServletRequest request) {
        try {
            String usermail = Token.getMailFromToken(request.getHeader(HEADER_STRING));
            List<JSONObject> list = InicioNegocio.getNovedades(usermail);
            
            if (list.isEmpty()) {
                return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
                // You many decide to return HttpStatus.NOT_FOUND
            }
            return new ResponseEntity<Object>(list.toString(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @RequestMapping(value = "/inicio/buscar", method = RequestMethod.POST)
    public ResponseEntity<?> buscar(HttpEntity<String> httpEntity, HttpServletRequest request) throws JSONException, IOException {
        try {
            //obtiene objeto json
            JSONObject json = new JSONObject(httpEntity.getBody());    
            //busca en json los atributos
            String busqueda = json.getString("busqueda");
            String genero = json.getString("genero");
            String artista = json.getString("nombre_artista");
            String direccion = json.getString("direccion");
            String desdehasta = json.getString("desdehasta");
            Date fecha = Tools.DateFormatter(json.getString("fecha"));
           
            //busca mail de usuario
            String usermail = Token.getMailFromToken(request.getHeader(HEADER_STRING));
            
            return InicioNegocio.Buscar(busqueda,genero,artista,direccion,desdehasta,fecha,usermail);
            
        } catch (Exception ex) {
            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
