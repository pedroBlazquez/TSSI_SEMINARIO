package aplicacion;

import org.springframework.web.bind.annotation.*;

import aplicacion.autenticacion.Token;
import modelos.Artista;
import modelos.Cancion;
import modelos.CancionDisco;
import modelos.Disco;
import conexion.Conexion;

import org.hibernate.Session;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static aplicacion.autenticacion.SecurityConstants.HEADER_STRING;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/discos")
public class DiscosControlador {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<?> getDiscos() {
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
    
    @RequestMapping(value = "/canciones/{disco}/", method = RequestMethod.GET)
    public ResponseEntity<?> getCancionesDisco(@PathVariable("disco") long iddisco) {
        try {
            Conexion cn = new Conexion();
            cn.abrirConexion();
            //Disco d = (Disco) cn.ReadOne_simpleid(Disco.class, (int)iddisco);
            List<CancionDisco> cd = cn.getListQuery("from modelos.CancionDisco WHERE idCancionDisco.disco.id = "+(int)iddisco);
            //List<CancionDisco> cd = d.getCancionesDisco();
            
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
    
    // -------------------Create-------------------------------------------
    
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> crearDisco(HttpServletRequest request,
            @RequestBody Object obj ) {
        try {
        HashMap<String,Object> result = (HashMap<String,Object>)obj;
        
        String nombre= (String)result.get("nombre");
        
        DateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        Date fechaPublicacion= format.parse((String)result.get("fechaPublicacion"));
        
        List<Integer> canciones = (ArrayList<Integer>)result.get("canciones");
        
        //convierte lista de canciones en query
        String INcanciones = "";
        if (!canciones.isEmpty()) {
            StringBuilder cBuilder = new StringBuilder();
            for (Integer c : canciones) {
                cBuilder.append(c).append(",");
            }
            cBuilder.deleteCharAt(cBuilder.length() - 1);
            INcanciones = cBuilder.toString();
        }
        
        //busca mail de usuario
        String token = request.getHeader(HEADER_STRING);
        String user = "";
        user = Token.getMailFromToken(token);
        
        Conexion cn = new Conexion();
        cn.abrirConexion();
            //busca canciones para agregar al disco
            List<Cancion> list_canciones = cn.getListQuery("from modelos.Cancion WHERE id IN ("+INcanciones+")");
            //busca el artista correspondiente al usuario logueado
            List<Artista> list_artistas = cn.getListQuery("from modelos.Artista WHERE usuario.mail = '"+user+"'");
            //crea el nuevo disco
            Disco new_Disco = new Disco(nombre,fechaPublicacion,list_artistas.get(0));
            //crea la nueva conexion muchos a muchos CancionDisco
            List<CancionDisco> new_CancionDisco = new ArrayList<CancionDisco>();
            for(Cancion c : list_canciones)
            {
                new_CancionDisco.add(new CancionDisco(c, new_Disco));
            }
            
            cn.add(new_Disco);
            cn.addList(new_CancionDisco);
        cn.cerrarConexion();
        return new ResponseEntity(HttpStatus.CREATED);
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
