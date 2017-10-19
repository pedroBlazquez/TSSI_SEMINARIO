package aplicacion;

import org.springframework.web.bind.annotation.*;

@RestController
public class InicioControlador {
    
    @RequestMapping("/")
    public String inicio() {
        return "Hola";
    }
}
