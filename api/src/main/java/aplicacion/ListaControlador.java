package aplicacion;

import org.springframework.web.bind.annotation.*;

import aplicacion.autenticacion.Token;
import modelos.Cancion;
import modelos.ListaReproduccion;
import modelos.Usuario;
import negocio.DiscoNegocio;
import negocio.ListasNegocio;
import negocio.UsuarioNegocio;
import conexion.Conexion;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static aplicacion.autenticacion.SecurityConstants.HEADER_STRING;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/listas")
public class ListaControlador {

    @RequestMapping(value = "/{lista}", method = RequestMethod.GET) //obtener lista
    public ResponseEntity<?> getCancion(@PathVariable("lista") long idlista, HttpServletRequest request) {
        try {
            Conexion cn = new Conexion();
            cn.abrirConexion();
            List<ListaReproduccion> listas = cn.getListQuery("from modelos.ListaReproduccion WHERE id = "+idlista);
            
            if (listas.isEmpty()) {
                cn.cerrarConexion();
                return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
                // You many decide to return HttpStatus.NOT_FOUND
            }
            
            String usermail = Token.getMailFromToken(request.getHeader(HEADER_STRING));
            List<JSONObject> jobj_list = ListasNegocio.setData(cn,listas, usermail);
            cn.cerrarConexion();
            return new ResponseEntity<Object>(jobj_list.toString(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @RequestMapping(value = "/getUsuario", method = RequestMethod.GET) //obtener listas propias publicas y privadas
    public ResponseEntity<?> getListaReproduccionArtista(HttpServletRequest request) {
        try {
            Conexion cn = new Conexion();
            cn.abrirConexion();
            String usermail = Token.getMailFromToken(request.getHeader(HEADER_STRING));
            List<Usuario> usuarios = cn.getListQuery("from modelos.Usuario WHERE mail = '"+usermail+"'");
            Usuario usuario = usuarios.get(0);
            
            List<ListaReproduccion> listas = cn.getListQuery("from modelos.ListaReproduccion WHERE usuario.id = "+usuario.getId() + " order by fechaAlta desc");
            
            if (listas.isEmpty()) {
                cn.cerrarConexion();
                return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
                // You many decide to return HttpStatus.NOT_FOUND
            }
            
            List<JSONObject> jobj_list = ListasNegocio.setData(cn,listas, usermail);
            cn.cerrarConexion();
            return new ResponseEntity<Object>(jobj_list.toString(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @RequestMapping(value = "/getUsuario/{usuario}", method = RequestMethod.GET) //obtener listas publicas de un usuario especifico
    public ResponseEntity<?> getListaReproduccionArtista(@PathVariable("usuario") long idusuario, HttpServletRequest request) {
        try {

            Conexion cn = new Conexion();
            cn.abrirConexion();

            String usermail = Token.getMailFromToken(request.getHeader(HEADER_STRING));
            Integer idlogueado = UsuarioNegocio.getIdUsuarioByMail(cn, usermail);
            
            String filtro = "";
            if(idusuario != idlogueado)
                filtro = " privacidad = false and ";
            
            List<ListaReproduccion> listas = cn.getListQuery("from modelos.ListaReproduccion WHERE "+filtro+" usuario.id = "+idusuario + " order by fechaAlta desc");
            
            if (listas.isEmpty()) {
                cn.cerrarConexion();
                return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
                // You many decide to return HttpStatus.NOT_FOUND
            }
            
            List<JSONObject> jobj_list = ListasNegocio.setData(cn,listas, usermail);
            cn.cerrarConexion();
            return new ResponseEntity<Object>(jobj_list.toString(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @RequestMapping(value = "/getCanciones/{lista}", method = RequestMethod.GET) //obtener canciones lista
    public ResponseEntity<?> getCancionesListaReproduccion(@PathVariable("lista") long idlista) {
        try {
            Conexion cn = new Conexion();
            cn.abrirConexion();
            List<Cancion> cd = cn.getListQuery("select cd.idCancionLista.cancion from modelos.CancionLista cd WHERE cd.idCancionLista.lista.id = "+(int)idlista);
           
            cn.cerrarConexion();
            if (cd.isEmpty()) {
                return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
                // You many decide to return HttpStatus.NOT_FOUND
            }
            return new ResponseEntity<List<Cancion>>(cd, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    // -------------------Create-------------------------------------------
    
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> createListaReproduccion(HttpEntity<String> httpEntity, HttpServletRequest request) throws JSONException, IOException {
        try {
            //obtiene objeto json
            JSONObject json = new JSONObject(httpEntity.getBody());    
            //busca en json los atributos
            String nombre= json.getString("nombre");
            boolean privacidad= json.getBoolean("privacidad");
            ArrayList<String> canciones = Tools.Convert_jsonArray_toArrayString(json.getJSONArray("canciones"));
            //busca mail de usuario [] 
            String usermail = Token.getMailFromToken(request.getHeader(HEADER_STRING));
            
            return ListasNegocio.CreateListaReproduccion(nombre,privacidad,  canciones, usermail);
            
        } catch (Exception ex) {
            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
 
    // -------------------Update-------------------------------------------
    
    @RequestMapping(value = "/", method = RequestMethod.PUT) 
    public ResponseEntity<?> updateListaReproduccion(HttpEntity<String> httpEntity, HttpServletRequest request) throws JSONException, IOException {
        try {
            //obtiene objeto json
            JSONObject json = new JSONObject(httpEntity.getBody());    
            //busca en json los atributos
            String idListaReproduccion= json.getString("idListaReproduccion");
            String nombre= json.getString("nombre");
            boolean privacidad= json.getBoolean("privacidad");
            ArrayList<String> canciones = Tools.Convert_jsonArray_toArrayString(json.getJSONArray("canciones"));
            
            return ListasNegocio.UpdateListaReproduccion(idListaReproduccion,nombre,privacidad,canciones);
        } catch (Exception ex) {
            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    // -------------------Update (agregar cancion)-------------------------------------------
    
    @RequestMapping(value = "/agregarCancion", method = RequestMethod.PUT) 
    public ResponseEntity<?> updateListaReproduccion_agregarCancion(HttpEntity<String> httpEntity, HttpServletRequest request) throws JSONException, IOException {
        try {
            //obtiene objeto json
            JSONObject json = new JSONObject(httpEntity.getBody());    
            //busca en json los atributos
            String idListaReproduccion= json.getString("idListaReproduccion");
            String idCancion= json.getString("idCancion");
            
            return ListasNegocio.UpdateListaReproduccion_agregarCancion(idListaReproduccion,idCancion);
        } catch (Exception ex) {
            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    // -------------------Delete-------------------------------------------
    
    @RequestMapping(value = "/", method = RequestMethod.DELETE) 
    public ResponseEntity<?> deleteListaReproduccion(HttpEntity<String> httpEntity, HttpServletRequest request) throws JSONException, IOException {
        try {
            //obtiene objeto json
            JSONObject json = new JSONObject(httpEntity.getBody());    
            //busca en json los atributos
            String idListaReproduccion= json.getString("idListaReproduccion");
            return ListasNegocio.DeleteListaReproduccion(idListaReproduccion);
        } catch (Exception ex) {
            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
   
}
