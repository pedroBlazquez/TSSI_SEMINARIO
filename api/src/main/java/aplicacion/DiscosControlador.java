package aplicacion;

import org.springframework.web.bind.annotation.*;

import modelos.Cancion;
import modelos.CancionDisco;
import modelos.Disco;
import conexion.Conexion;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("/discos")
public class DiscosControlador {

    @RequestMapping(value = "/", method = RequestMethod.GET)
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
    
    @RequestMapping(value = "/{artista}/", method = RequestMethod.GET)
    public ResponseEntity<Disco> getDiscoArtista(@PathVariable("artista") long idartista) {
        try {
            Conexion cn = new Conexion();
            cn.abrirConexion();
            List<Disco> discos = cn.getListQuery("from modelos.Disco WHERE artista.id = "+idartista);
            cn.cerrarConexion();
            if (discos.isEmpty()) {
                return new ResponseEntity(HttpStatus.NO_CONTENT);
                // You many decide to return HttpStatus.NOT_FOUND
            }
            return new ResponseEntity<Disco>(discos.get(0), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @RequestMapping(value = "/canciones/{disco}/", method = RequestMethod.GET)
    public ResponseEntity<List<CancionDisco>> getCancionesDisco(@PathVariable("disco") long iddisco) {
        try {
            Conexion cn = new Conexion();
            cn.abrirConexion();
            Disco d = (Disco) cn.ReadOne_simpleid(Disco.class, (int)iddisco);
            List<CancionDisco> cd = d.getCancionesDisco();
            /*List<Cancion> canciones = null;
            for(CancionDisco cd_item : cd)
            {
                canciones.add(cd_item.getCancion());
            }
            */
            cn.cerrarConexion();
            if (cd.isEmpty()) {
                return new ResponseEntity(HttpStatus.NO_CONTENT);
                // You many decide to return HttpStatus.NOT_FOUND
            }
            return new ResponseEntity<List<CancionDisco>>(cd, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
   
}
