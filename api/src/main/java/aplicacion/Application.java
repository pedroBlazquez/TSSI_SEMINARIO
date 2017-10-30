package aplicacion;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import aplicacion.*;
import conexion.Conexion;

@SpringBootApplication
public class Application {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    public static void main(String[] args) {
        // Nos aseguramos que se cree la base de datos cuando se
        // ejecuta la aplicacion.
        //Conexion cn = new Conexion();
        //cn.abrirConexion();
        //cn.cerrarConexion();
        SpringApplication.run(Application.class, args);
    }
}
