package aplicacion;

import org.springframework.web.bind.annotation.*;
import modelos.Disco;
import conexion.Conexion;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("/discos")
public class DiscosControlador {

    @RequestMapping(value = "/getAll/", method = RequestMethod.GET)
    public ResponseEntity<List<Disco>> getDiscos() {
        try {
            Conexion cn = new Conexion();
            cn.abrirConexion();
            List<Disco> discos = cn.getListQuery("from modelos.Disco");
            cn.cerrarConexion();
            if (discos.isEmpty()) {
                return new ResponseEntity(HttpStatus.NO_CONTENT);
                // You many decide to return HttpStatus.NOT_FOUND
            }
            return new ResponseEntity<List<Disco>>(discos, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
