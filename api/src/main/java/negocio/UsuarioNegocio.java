package negocio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import aplicacion.Tools;
import conexion.Conexion;
import modelos.Artista;
import modelos.Cancion;
import modelos.CancionDisco;
import modelos.Disco;
import modelos.Genero;
import modelos.GeneroDisco;
import modelos.Seguidos;
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
    
    public static ResponseEntity Seguir(String idSeguido, String usermail_seguidor)
    {
        try
        {
            Conexion cn = new Conexion();
            cn.abrirConexion();
            
            List<Usuario> usuarios_seguidor = cn.getListQuery("from modelos.Usuario WHERE mail = '"+usermail_seguidor+"'");
            Usuario seguidor = usuarios_seguidor.get(0);
            List<Usuario> usuarios_seguido = cn.getListQuery("from modelos.Usuario WHERE id = "+idSeguido+"");
            Usuario seguido = usuarios_seguido.get(0);
            
            Seguidos seguimiento = new Seguidos(seguidor, seguido);
            
            List<Seguidos> seguidos = cn.getListQuery("from modelos.Seguidos WHERE idSeguidos.seguidor.id = "+seguidor.getId()+" and idSeguidos.seguido.id = "+seguido.getId());
            if(seguidos.isEmpty())//si no encuentra seguimiento, agrega uno
                cn.add(seguimiento);
            else //si encuentra seguimiento, lo borra
                cn.delete(seguimiento);
            
            cn.cerrarConexion();
            return new ResponseEntity(HttpStatus.OK);
        }catch(Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NOT_MODIFIED);
        }
    }
    
}
