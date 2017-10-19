package aplicacion;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import aplicacion.*;
import conexion.Conexion;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        // Nos aseguramos que se cree la base de datos cuando se
        // ejecuta la aplicacion.
        Conexion cn = new Conexion();
        cn.abrirConexion();
        cn.cerrarConexion();
        SpringApplication.run(Application.class, args);
    }
}
