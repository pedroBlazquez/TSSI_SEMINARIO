package aplicacion;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.*;

import aplicacion.autenticacion.Token;

import static aplicacion.autenticacion.SecurityConstants.HEADER_STRING;

@RestController
public class InicioControlador {

    @RequestMapping("/")
    public String inicio(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        String user = "no anduvo";

        user = Token.getMailFromToken(token);

        return user;
    }
}
