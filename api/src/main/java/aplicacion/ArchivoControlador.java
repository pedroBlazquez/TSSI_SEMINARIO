package aplicacion;

import static aplicacion.autenticacion.SecurityConstants.HEADER_STRING;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import aplicacion.autenticacion.Token;
import conexion.Conexion;
import modelos.Usuario;
import negocio.GestorArchivo;
import negocio.GestorArchivo.TipoContenido;
import negocio.UsuarioNegocio;

@RestController
@RequestMapping("/archivo")
public class ArchivoControlador {

	protected String domain = "http://localhost:8080";

    @RequestMapping(value = "/subirCancion", method = RequestMethod.POST)
    public String subirCancion(
		HttpServletRequest request,
        HttpServletResponse response,
        @RequestParam("file") MultipartFile archivo
    ) throws IOException {

    	if (archivo.isEmpty()) {
            response.sendError(HttpStatus.OK.value(), "El archivo está vacio");
        }

    	String token = request.getHeader(HEADER_STRING);
        String pathFinal = this.subirArchivo(archivo, TipoContenido.CANCION, token);

        return pathFinal;
    }

    @RequestMapping(value = "/subirEventoFoto", method = RequestMethod.POST)
    public String subirEvento(
		HttpServletRequest request,
        HttpServletResponse response,
        @RequestParam("file") MultipartFile archivo
    ) throws IOException {

    	if (archivo.isEmpty()) {
            response.sendError(HttpStatus.OK.value(), "El archivo está vacio");
        }

    	String token = request.getHeader(HEADER_STRING);
        String pathFinal = this.subirArchivo(archivo, TipoContenido.EVENTO_FOTO, token);

        return pathFinal;
    }

    @RequestMapping(value = "/subirDiscoPortada", method = RequestMethod.POST)
    public String subirDiscoPortada(
		HttpServletRequest request,
        HttpServletResponse response,
        @RequestParam("file") MultipartFile archivo
    ) throws IOException {

    	if (archivo.isEmpty()) {
            response.sendError(HttpStatus.OK.value(), "El archivo está vacio");
        }

    	String token = request.getHeader(HEADER_STRING);
        String pathFinal = this.subirArchivo(archivo, TipoContenido.DISCO_PORTADA, token);

        return pathFinal;
    }

    @RequestMapping(value = "/subirAlbumPortada", method = RequestMethod.POST)
    public String subirAlbumPortada(
		HttpServletRequest request,
        HttpServletResponse response,
        @RequestParam("file") MultipartFile archivo
    ) throws IOException {

        if (archivo.isEmpty()) {
            response.sendError(HttpStatus.OK.value(), "El archivo está vacio");
        }

        String token = request.getHeader(HEADER_STRING);
        String pathFinal = this.subirArchivo(archivo, TipoContenido.ALBUM_PORTADA, token);

        return pathFinal;
    }

    @RequestMapping(value = "/subirPerfilFoto", method = RequestMethod.POST)
    public String subirPerfilFoto(
		HttpServletRequest request,
        HttpServletResponse response,
        @RequestParam("file") MultipartFile archivo
    ) throws IOException {

    	if (archivo.isEmpty()) {
            response.sendError(HttpStatus.OK.value(), "El archivo está vacio");
        }

    	String token = request.getHeader(HEADER_STRING);
        String pathFinal = this.subirArchivo(archivo, TipoContenido.PERFIL_FOTO, token);

        return pathFinal;
    }

    protected String subirArchivo(
		MultipartFile archivo,
		TipoContenido tipoContenido,
		String token
    ) throws IOException {

    	GestorArchivo gestor = new GestorArchivo();

        String userMail = Token.getMailFromToken(token);
        
        Conexion cn = new Conexion();
        cn.abrirConexion();
        Usuario user = UsuarioNegocio.getUsuarioByMail(cn,userMail);
        cn.cerrarConexion();
        String path = gestor.generarPath(user.getId(), tipoContenido, archivo.getOriginalFilename());
        gestor.subirArchvo(archivo, path);

        String[] pathParts = path.split("/public");
        return this.domain + Arrays.asList(pathParts).get(1);
    }
    
}
