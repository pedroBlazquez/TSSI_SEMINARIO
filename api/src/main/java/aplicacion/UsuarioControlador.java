package aplicacion;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import modelos.IntegranteArtista;
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
        List<IntegranteArtista> integrantesListaObjs = new ArrayList();
        List<Integer> roles = new ArrayList();

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
    

}
