package aplicacion;

import org.springframework.web.bind.annotation.*;

import aplicacion.autenticacion.Token;
import modelos.Artista;
import modelos.Evento;
import negocio.EventoNegocio;
import conexion.Conexion;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static aplicacion.autenticacion.SecurityConstants.HEADER_STRING;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
