package aplicacion.autenticacion;

import java.util.List;

import org.springframework.stereotype.Repository;

import conexion.Conexion;

@Repository
public class ApplicationUserRepository {
    ApplicationUser findByUsername(String username) {
        Conexion cn = new Conexion();

        cn.abrirConexion();
        List<Object> userObject = cn.getListQuery("From ApplicationUser WHERE username ='" + username + "'");
        ApplicationUser user = null;

        if (!userObject.isEmpty()) {
            user = (ApplicationUser) userObject.get(0);
        }

        cn.cerrarConexion();
        return user;
    }
}
