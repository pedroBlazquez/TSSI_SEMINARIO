package aplicacion;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioControlador {

    @RequestMapping(value = "/registro", method = RequestMethod.POST)
    public void login(HttpServletRequest request, HttpServletResponse response) {
        
    }
}
