package aplicacion;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import aplicacion.autenticacion.Token;
import conexion.Conexion;
import modelos.Disco;
import negocio.InicioNegocio;

import static aplicacion.autenticacion.SecurityConstants.HEADER_STRING;

import java.util.ArrayList;
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
    
    @RequestMapping(value = "/Inicio", method = RequestMethod.GET)
    public ResponseEntity<Object> getNovedades(HttpServletRequest request) {
        try {
            Conexion cn = new Conexion();
            cn.abrirConexion();
            
            String usermail = Token.getMailFromToken(request.getHeader(HEADER_STRING));
            List<Object> list = InicioNegocio.getNovedades(usermail);
            
            cn.cerrarConexion();
            if (list.isEmpty()) {
                return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
                // You many decide to return HttpStatus.NOT_FOUND
            }
            return new ResponseEntity<Object>(list, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
