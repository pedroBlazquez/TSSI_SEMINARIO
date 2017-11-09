package aplicacion;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import negocio.GestorArchivo;
import negocio.GestorArchivo.TipoContenido;

@RestController
@RequestMapping("/cancion")
public class ArchivoControlador {

    protected final String UPLOADED_FOLDER = "C:/canciones";

    @RequestMapping(value = "/subir", method = RequestMethod.POST)
    public void subirCancion(
            HttpEntity<String> httpEntity,
            HttpServletResponse response,
            @RequestParam("file") MultipartFile archivo
    ) throws IOException {
        GestorArchivo gestor = new GestorArchivo();
        
        if (archivo.isEmpty()) {
            response.sendError(HttpStatus.OK.value(), "El archivo está vacio");
        }
        String path = gestor.generarPath(1, 12, TipoContenido.EVENTO_FOTO, archivo.getOriginalFilename());
        gestor.subirArchvo(archivo, path);
        
    }
    
}
