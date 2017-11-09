package aplicacion;

import org.springframework.web.bind.annotation.*;

import modelos.Artista;
import conexion.Conexion;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/artistas")
public class ArtistaControlador {

    @RequestMapping(value = "/{idartista}", method = RequestMethod.GET)
    public ResponseEntity<?> getArtista(@PathVariable("idartista") long idartista) {
        try {
            Conexion cn = new Conexion();
            cn.abrirConexion();
            List<Artista> artistas = cn.getListQuery("from modelos.Artista WHERE id = "+idartista);
            cn.cerrarConexion();
            if (artistas.isEmpty()) {
                return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
                // You many decide to return HttpStatus.NOT_FOUND
            }
            return new ResponseEntity<Object>(artistas.get(0), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @RequestMapping(value = "/getbyUsuario/{idusuario}", method = RequestMethod.GET)
    public ResponseEntity<?> getArtista_byUid(@PathVariable("idusuario") long idusuario) {
        try {
            Conexion cn = new Conexion();
            cn.abrirConexion();
            List<Artista> artistas = cn.getListQuery("from modelos.Artista WHERE usuario.id = "+idusuario);
            cn.cerrarConexion();
            if (artistas.isEmpty()) {
                return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
                // You many decide to return HttpStatus.NOT_FOUND
            }
            return new ResponseEntity<Object>(artistas.get(0), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
   
}
