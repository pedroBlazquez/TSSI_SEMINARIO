package aplicacion;

import static aplicacion.autenticacion.SecurityConstants.HEADER_STRING;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import aplicacion.autenticacion.Token;
import conexion.Conexion;
import modelos.Usuario;
import negocio.SeguidosNegocio;
import negocio.UsuarioNegocio;

@RestController
@RequestMapping("/usuario")
public class SeguidosControlador {

    
    @RequestMapping(value = "/seguir", method = RequestMethod.POST)
    public ResponseEntity<?> Seguir(HttpEntity<String> httpEntity, HttpServletRequest request) throws JSONException, IOException {
        try {
            //obtiene objeto json
            JSONObject json = new JSONObject(httpEntity.getBody());    
            //busca en json los atributos
            String idUsuario= json.getString("idUsuario");
            
            String usermail = Token.getMailFromToken(request.getHeader(HEADER_STRING));
            
            return SeguidosNegocio.Seguir(idUsuario, usermail);
            
        } catch (Exception ex) {
            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @RequestMapping(value = "/getSeguimiento/{idUsuario}", method = RequestMethod.GET) //para saber si sigo a determinado usuario
    public ResponseEntity<?> Seguir(@PathVariable("idUsuario") long idUsuario, HttpServletRequest request) throws JSONException, IOException {
        try {
            String usermail = Token.getMailFromToken(request.getHeader(HEADER_STRING));
            Conexion cn = new Conexion();
            cn.abrirConexion();
            boolean seguido = SeguidosNegocio.getSeguimiento(cn,(int)idUsuario, usermail);
            cn.cerrarConexion();
            if(!seguido)
                return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
            else 
                return new ResponseEntity<Object>(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @RequestMapping(value = "/getSeguidores/{idUsuario}", method = RequestMethod.GET)//get seguidores de un usuario o mios
    public ResponseEntity<?> getSeguidores(@PathVariable("idUsuario") long idUsuario, HttpServletRequest request) throws JSONException, IOException {
        try {
            String usermail = Token.getMailFromToken(request.getHeader(HEADER_STRING));
            List<Usuario> seguidores = new ArrayList<Usuario>();
            Conexion cn = new Conexion();
            cn.abrirConexion();
            if((int)idUsuario == 0)
                seguidores = SeguidosNegocio.getSeguidores(cn,usermail);
            else
                seguidores = SeguidosNegocio.getSeguidores(cn,(int)idUsuario);
                
            List<JSONObject> jobj_list = UsuarioNegocio.setData(cn,seguidores, usermail);
            cn.cerrarConexion();
            return new ResponseEntity<Object>(jobj_list.toString(),HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @RequestMapping(value = "/getSeguidos/{idUsuario}", method = RequestMethod.GET)//get seguidos por un usuario o por mi
    public ResponseEntity<?> getSeguidos(@PathVariable("idUsuario") long idUsuario, HttpServletRequest request) throws JSONException, IOException {
        try {
            String usermail = Token.getMailFromToken(request.getHeader(HEADER_STRING));
            List<Usuario> seguidos = new ArrayList<Usuario>();
            Conexion cn = new Conexion();
            cn.abrirConexion();
            if((int)idUsuario == 0)
                seguidos = SeguidosNegocio.getSeguidos(cn,usermail);
            else
                seguidos = SeguidosNegocio.getSeguidos(cn,(int)idUsuario);
            
            List<JSONObject> jobj_list = UsuarioNegocio.setData(cn,seguidos, usermail);
            cn.cerrarConexion();
            return new ResponseEntity<Object>(jobj_list.toString(),HttpStatus.OK);
            
        } catch (Exception ex) {
            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
