package aplicacion;


import static aplicacion.autenticacion.SecurityConstants.HEADER_STRING;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import aplicacion.autenticacion.Token;
import conexion.Conexion;
import modelos.IntegranteArtista;
import modelos.Usuario;
import negocio.ArtistaNegocio;
import negocio.IntegranteArtistaNegocio;
import negocio.UsuarioNegocio;

@RestController
@RequestMapping("/usuario")
public class UsuarioControlador {

    @RequestMapping(value = "/registro", method = RequestMethod.POST)
    public void login(HttpEntity<String> httpEntity, HttpServletResponse response) throws JSONException, IOException {
        JSONObject json = new JSONObject(httpEntity.getBody());
        JSONObject usuarioForm = json.getJSONObject("usuarioForm");
        int usuarioTipoId = usuarioForm.getInt("usuarioTipo");
        UsuarioNegocio usuarioNegocio = new UsuarioNegocio();
        ArtistaNegocio artistaNegocio = new ArtistaNegocio();
        boolean creacionExitosa = usuarioNegocio.altaUsuario(usuarioForm);

        if (!creacionExitosa) {
            response.sendError(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Error al crear el usuario"
            );
        }

        if (usuarioTipoId == 1) {
            return;
        }

        String mail = usuarioForm.getString("mail");
        JSONObject artistaForm = json.getJSONObject("artistaForm");
        boolean altaArtistaExitoso = artistaNegocio.altaArtista(
            artistaForm,
            usuarioTipoId,
            mail
        );

        if (!altaArtistaExitoso) {
            response.sendError(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Error al crear el artista"
            );
            usuarioNegocio.eliminarUsuario(mail);
            return;
        }

        if (usuarioTipoId == 2) {
            return;
        }

        JSONArray integrantesLista = json.getJSONArray("integrantesLista");
        IntegranteArtistaNegocio intArtNegocio = new IntegranteArtistaNegocio();
        List<IntegranteArtista> integrantesListaObjs = new ArrayList<IntegranteArtista>();
        List<Integer> roles = new ArrayList<Integer>();

        for (int i = 0; i < integrantesLista.length(); i++) {
            JSONObject obj = integrantesLista.getJSONObject(i);
            roles.add(obj.getInt("rol"));
            obj.remove("rol");
            integrantesListaObjs.add(new ObjectMapper()
                    .readValue(obj.toString(), IntegranteArtista.class));
        }

        intArtNegocio.altaListaIntegrantes(integrantesListaObjs, roles);
    }

    @RequestMapping(value = "/registro/checkMail", method = RequestMethod.POST)
    public boolean checkMail(HttpEntity<String> httpEntity, HttpServletResponse response) throws JSONException {
        JSONObject json = new JSONObject(httpEntity.getBody());
        UsuarioNegocio usuarioNegocio = new UsuarioNegocio();
        return usuarioNegocio.checkMail(json.getString("mail"));
    }
    
    @RequestMapping(value = "/{idusuario}", method = RequestMethod.GET)
    public ResponseEntity<?> getUsuario(@PathVariable("idusuario") long idusuario, HttpServletRequest request) {
        try {
            String usermail = Token.getMailFromToken(request.getHeader(HEADER_STRING));
            Usuario u = new Usuario();
            if(idusuario == 0)
            {
                Conexion cn = new Conexion();
                cn.abrirConexion();
                List<Usuario> usuarios = cn.getListQuery("from modelos.Usuario WHERE mail = '"+usermail+"'");
                cn.cerrarConexion();
                u = usuarios.get(0);
            }
            else
                u = UsuarioNegocio.getById((int)idusuario);
                
            if (u == null) {
                return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
                // You many decide to return HttpStatus.NOT_FOUND
            }
            List<Usuario> lu = new ArrayList<Usuario>();
            lu.add(u);
            List<JSONObject> jobj_list = UsuarioNegocio.setData(lu, usermail);
            
            return new ResponseEntity<Object>(jobj_list.get(0).toString(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

}
