package aplicacion;

import static aplicacion.autenticacion.SecurityConstants.BAD_CREDENTIALS_MSG;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import negocio.UsuarioNegocio;

@RestController
@RequestMapping("/usuario")
public class UsuarioControlador {

    @RequestMapping(value = "/registro", method = RequestMethod.POST)
    public void login(HttpEntity<String> httpEntity, HttpServletResponse response) throws JSONException, IOException {
        JSONObject json = new JSONObject(httpEntity.getBody());
        JSONObject mainForm = json.getJSONObject("mainForm");
        int usuarioTipoId = mainForm.getInt("usuarioTipo");
        UsuarioNegocio usuarioNegocio = new UsuarioNegocio();
        boolean creacionExitosa = usuarioNegocio.altaUsuario(mainForm);

        if (!creacionExitosa) {
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        switch (usuarioTipoId) {
            case 1:
                break;
            case 2:
                JSONObject bandaForm = json.getJSONObject("secondForm");
                break;
            case 3:
                JSONObject artistaForm = json.getJSONObject("secondForm");
                break;
        }

    }

    @RequestMapping(value = "/registro/checkMail", method = RequestMethod.POST)
    public boolean checkMail(HttpEntity<String> httpEntity, HttpServletResponse response) throws JSONException {
        JSONObject json = new JSONObject(httpEntity.getBody());
        UsuarioNegocio usuarioNegocio = new UsuarioNegocio();
        return usuarioNegocio.checkMail(json.getString("mail"));
    }

}
