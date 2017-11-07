package aplicacion;

import org.springframework.web.bind.annotation.*;

import aplicacion.autenticacion.Token;
import modelos.Cancion;
import modelos.Disco;
import modelos.Evento;
import negocio.CancionNegocio;
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
@RequestMapping("/eventos")
public class EventoControlador {

    @RequestMapping(value = "/{evento}", method = RequestMethod.GET)
    public ResponseEntity<?> getEvento(@PathVariable("evento") long idevento) {
        try {
            Conexion cn = new Conexion();
            cn.abrirConexion();
            List<Evento> eventos = cn.getListQuery("from modelos.Evento WHERE id = "+idevento);
            cn.cerrarConexion();
            if (eventos.isEmpty()) {
                return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
                // You many decide to return HttpStatus.NOT_FOUND
            }
            return new ResponseEntity<Evento>(eventos.get(0), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @RequestMapping(value = "/getArtista/{artista}", method = RequestMethod.GET)
    public ResponseEntity<?> getEventosArtista(@PathVariable("artista") long idartista) {
        try {
            Conexion cn = new Conexion();
            cn.abrirConexion();
            List<Evento> eventos = cn.getListQuery("from modelos.Evento WHERE artista.id = "+idartista);
            cn.cerrarConexion();
            if (eventos.isEmpty()) {
                return new ResponseEntity(HttpStatus.NO_CONTENT);
                // You many decide to return HttpStatus.NOT_FOUND
            }
            return new ResponseEntity<List<Evento>>(eventos, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    // -------------------Create-------------------------------------------
    
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> createEvento(HttpEntity<String> httpEntity, HttpServletRequest request) throws JSONException, IOException {
        try {
            //obtiene objeto json
            JSONObject json = new JSONObject(httpEntity.getBody());    
            //busca en json los atributos
            String nombre= json.getString("nombre");
            String descripcion= json.getString("descripcion");
            String direccion= json.getString("direccion");
            int costo = json.getInt("costo");
            Date fechaEvento = Tools.DateFormatter(json.getString("fechaEvento"));
           
            //busca mail de usuario
            String usermail = Token.getMailFromToken(request.getHeader(HEADER_STRING));
            
            return EventoNegocio.CreateEvento(nombre,descripcion,direccion,fechaEvento,costo,usermail);
            
        } catch (Exception ex) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
 
    // -------------------Update-------------------------------------------
    
    @RequestMapping(value = "/update", method = RequestMethod.POST) //utilizo POST para testear, porque el PUT me esta tirando 403 Invalid CORS request en PostMan
    public ResponseEntity<?> updateEvento(HttpEntity<String> httpEntity, HttpServletRequest request) throws JSONException, IOException {
        try {
            //obtiene objeto json
            JSONObject json = new JSONObject(httpEntity.getBody());    
            //busca en json los atributos
            String idEvento= json.getString("idEvento");
            String nombre= json.getString("nombre");
            String descripcion= json.getString("descripcion");
            String direccion= json.getString("direccion");
            int costo =  json.getInt("costo");
            Date fechaEvento = Tools.DateFormatter(json.getString("fechaEvento"));
            
            return EventoNegocio.UpdateEvento(idEvento,nombre,descripcion,direccion,fechaEvento,costo);
        } catch (Exception ex) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    // -------------------Delete-------------------------------------------
    
    @RequestMapping(value = "/delete", method = RequestMethod.POST) //utilizo POST para testear, porque el DELETE me esta tirando 403 Invalid CORS request en PostMan
    public ResponseEntity<?> deleteEvento(HttpEntity<String> httpEntity, HttpServletRequest request) throws JSONException, IOException {
        try {
            //obtiene objeto json
            JSONObject json = new JSONObject(httpEntity.getBody());    
            //busca en json los atributos
            String idEvento= json.getString("idEvento");
            return EventoNegocio.DeleteEvento(idEvento);
        } catch (Exception ex) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
   
}
