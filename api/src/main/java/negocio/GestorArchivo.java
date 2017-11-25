package negocio;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class GestorArchivo {
    
    protected String basePath = "";
    
    protected HashMap<TipoContenido, String> carpetas = new HashMap();
    
    public enum TipoContenido {
        CANCION,
        DISCO_PORTADA,
        EVENTO_FOTO,
        PERFIL_FOTO,
        ALBUM_PORTADA
    }

    public GestorArchivo() throws IOException {
        carpetas.put(TipoContenido.CANCION, "/canciones/");
        carpetas.put(TipoContenido.DISCO_PORTADA, "/portadas/");
        carpetas.put(TipoContenido.EVENTO_FOTO, "/eventos/");
        carpetas.put(TipoContenido.PERFIL_FOTO, "/perfil/");
        carpetas.put(TipoContenido.ALBUM_PORTADA, "/albumes/");

        this.basePath = new File(".").getCanonicalPath();
    }
    
    public String generarPath(
            int idUsuario,
            TipoContenido tipoContenido,
            String nombreArchivo
    ) {
        long millis = System.currentTimeMillis();
        String path = basePath + "/public/MusicAppArchivos/" + idUsuario + carpetas.get(tipoContenido) + Long.toString(millis) + nombreArchivo;

        return path;
    }
    
    public void subirArchvo(MultipartFile archivo, String pathArchivo) throws IOException {

        byte[] bytes = archivo.getBytes();
        Path path = Paths.get(pathArchivo);
        File file = new File(path.getParent().toString());

        if (!file.exists()) {
            file.mkdirs();
        }

        Files.write(path, bytes);
    }
    
}
