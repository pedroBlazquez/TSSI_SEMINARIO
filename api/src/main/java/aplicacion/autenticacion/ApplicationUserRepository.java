package aplicacion.autenticacion;

import java.util.List;

import org.springframework.stereotype.Repository;

import conexion.Conexion;
import modelos.Usuario;

@Repository
public class ApplicationUserRepository {

    Usuario findByUsername(String mail) {
        Conexion cn = new Conexion();

        cn.abrirConexion();
        List<Object> userRecord = cn.getListQuery("From Usuario WHERE mail ='" + mail + "'");
        Usuario user = null;

        if (!userRecord.isEmpty()) {
            user = (Usuario) userRecord.get(0);
        }

        // Si el usuario está inactivo no queremos autenticarlo
        if (!user.isEstado()) {
            user = null;
        }

        cn.cerrarConexion();
        return user;
    }
}
