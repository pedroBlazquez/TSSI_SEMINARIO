package aplicacion;

import org.springframework.web.bind.annotation.*;

import aplicacion.autenticacion.Token;
import modelos.Cancion;
import modelos.Disco;
import negocio.DiscoNegocio;
import conexion.Conexion;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static aplicacion.autenticacion.SecurityConstants.HEADER_STRING;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/discos")
public class DiscoControlador {

    @RequestMapping(value = "/{disco}", method = RequestMethod.GET)
    public ResponseEntity<?> getCancion(@PathVariable("disco") long iddisco) {
        try {
            Conexion cn = new Conexion();
            cn.abrirConexion();
            List<Disco> discos = cn.getListQuery("from modelos.Disco WHERE id = "+iddisco);
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
    
    @RequestMapping(value = "/getArtista/{artista}", method = RequestMethod.GET)
    public ResponseEntity<?> getDiscoArtista(@PathVariable("artista") long idartista) {
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
    
    @RequestMapping(value = "/getCanciones/{disco}", method = RequestMethod.GET)
    public ResponseEntity<?> getCancionesDisco(@PathVariable("disco") long iddisco) {
        try {
            Conexion cn = new Conexion();
            cn.abrirConexion();
            List<Cancion> cd = cn.getListQuery("select cd.idCancionDisco.cancion from modelos.CancionDisco cd WHERE cd.idCancionDisco.disco.id = "+(int)iddisco);
           
            cn.cerrarConexion();
            if (cd.isEmpty()) {
                return new ResponseEntity(HttpStatus.NO_CONTENT);
                // You many decide to return HttpStatus.NOT_FOUND
            }
            return new ResponseEntity<List<Cancion>>(cd, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    // -------------------Create-------------------------------------------
    
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> createDisco(HttpEntity<String> httpEntity, HttpServletRequest request) throws JSONException, IOException {
        try {
            //obtiene objeto json
            JSONObject json = new JSONObject(httpEntity.getBody());    
            //busca en json los atributos
            String nombre= json.getString("nombre");
            String genero= json.getString("genero");
            ArrayList<String> canciones = Tools.Convert_jsonArray_toArrayString(json.getJSONArray("canciones"));
            //busca mail de usuario
            String usermail = Token.getMailFromToken(request.getHeader(HEADER_STRING));
            
            return DiscoNegocio.CreateDisco(nombre,genero,  canciones, usermail);
            
        } catch (Exception ex) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
 
    // -------------------Update-------------------------------------------
    
    @RequestMapping(value = "/update", method = RequestMethod.POST) //utilizo POST para testear, porque el PUT me esta tirando 403 Invalid CORS request en PostMan
    public ResponseEntity<?> updateDisco(HttpEntity<String> httpEntity, HttpServletRequest request) throws JSONException, IOException {
        try {
            //obtiene objeto json
            JSONObject json = new JSONObject(httpEntity.getBody());    
            //busca en json los atributos
            String idDisco= json.getString("idDisco");
            String nombre= json.getString("nombre");
            String genero= json.getString("genero");
            ArrayList<String> canciones = Tools.Convert_jsonArray_toArrayString(json.getJSONArray("canciones"));
            
            return DiscoNegocio.UpdateDisco(idDisco,nombre,genero,canciones);
        } catch (Exception ex) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    // -------------------Delete-------------------------------------------
    
    @RequestMapping(value = "/delete", method = RequestMethod.POST) //utilizo POST para testear, porque el DELETE me esta tirando 403 Invalid CORS request en PostMan
    public ResponseEntity<?> deleteDisco(HttpEntity<String> httpEntity, HttpServletRequest request) throws JSONException, IOException {
        try {
            //obtiene objeto json
            JSONObject json = new JSONObject(httpEntity.getBody());    
            //busca en json los atributos
            String idDisco= json.getString("idDisco");
            return DiscoNegocio.DeleteDisco(idDisco);
        } catch (Exception ex) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    // -------------------Update------------------------------------------------
 
   /* @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        logger.info("Updating User with id {}", id);
 
        User currentUser = userService.findById(id);
 
        if (currentUser == null) {
            logger.error("Unable to update. User with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to upate. User with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
 
        currentUser.setName(user.getName());
        currentUser.setAge(user.getAge());
        currentUser.setSalary(user.getSalary());
 
        userService.updateUser(currentUser);
        return new ResponseEntity<User>(currentUser, HttpStatus.OK);
    }
 
    // -------------------Delete-----------------------------------------
 
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
        logger.info("Fetching & Deleting User with id {}", id);
 
        User user = userService.findById(id);
        if (user == null) {
            logger.error("Unable to delete. User with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to delete. User with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        userService.deleteUserById(id);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }
    
    */
   
}
