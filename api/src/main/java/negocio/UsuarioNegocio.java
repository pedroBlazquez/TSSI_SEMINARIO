package negocio;

import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;

import conexion.Conexion;
import modelos.Usuario;
import modelos.UsuarioTipo;

public class UsuarioNegocio {

    final String USUARIO_TIPO_JSON_FIELD = "usuarioTipo";
    
    public boolean checkMail(String mailToCheck) {
        Conexion cn = new Conexion();
        cn.abrirConexion();
        List<Object> usuarios = cn.getListQuery("FROM Usuario WHERE mail ='" + mailToCheck +"'");
        cn.cerrarConexion();
        
        return !usuarios.isEmpty();
    }

    public boolean altaUsuario(JSONObject data) {
        Conexion cn = new Conexion();
        cn.abrirConexion();
        UsuarioTipoNegocio usuarioTipoNegocio = new UsuarioTipoNegocio();
        try {
            int usuarioTipoId = data.getInt(USUARIO_TIPO_JSON_FIELD);
            data.remove(USUARIO_TIPO_JSON_FIELD);
            UsuarioTipo usuarioTipo = usuarioTipoNegocio.getUsuarioTipo(usuarioTipoId);

            Usuario nuevoUsuario = new ObjectMapper()
                    .readValue(data.toString(), Usuario.class);
            nuevoUsuario.setFechaAlta(new Date());
            nuevoUsuario.setEstado(true);
            nuevoUsuario.setUsuarioTipo(usuarioTipo);

            cn.add(nuevoUsuario);
            cn.cerrarConexion();
        } catch (Exception e) {
            e.printStackTrace();
            cn.cerrarConexion();
            return false;
        }
        return true;
    }

    public Usuario getUsuarioByMail(String mail) {
        Conexion cn = new Conexion();
        Usuario u = null;
        cn.abrirConexion();

        List<Object> usuarioLista = cn.getListQuery("FROM Usuario WHERE mail = '" + mail + "'");

        if (!usuarioLista.isEmpty()) {
            u = (Usuario) usuarioLista.get(0);
        }

        return u;
    }
    
    public static Usuario getById(int id) {
        Conexion cn = new Conexion();
        Usuario u = null;
        cn.abrirConexion();

        List<Object> usuarioLista = cn.getListQuery("FROM Usuario WHERE id = " + id);

        if (!usuarioLista.isEmpty()) {
            u = (Usuario) usuarioLista.get(0);
        }

        return u;
    }
    
    //Esto no es una baja logica, es una eliminacion permanente del registro
    public boolean eliminarUsuario(String mail){
        Conexion cn = new Conexion();
        Usuario u = this.getUsuarioByMail(mail);

        try {
            u.setMail(mail);
            cn.abrirConexion();
            cn.delete(u);
            cn.cerrarConexion();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
    
  
    
}
