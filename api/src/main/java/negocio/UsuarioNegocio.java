package negocio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import aplicacion.Tools;
import conexion.Conexion;
import modelos.Artista;
import modelos.Like;
import modelos.Usuario;
import modelos.UsuarioTipo;

public class UsuarioNegocio {

    final String USUARIO_TIPO_JSON_FIELD = "usuarioTipo";
    final String FECHA_NACIMIENTO_JSON_FIELD = "fechaNacimiento";
    
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

            Date fechaNacimiento = Tools.DateFormatter(data.getString(FECHA_NACIMIENTO_JSON_FIELD));
            data.remove(FECHA_NACIMIENTO_JSON_FIELD);
            
            Usuario nuevoUsuario = new ObjectMapper()
                    .readValue(data.toString(), Usuario.class);
            nuevoUsuario.setFechaAlta(new Date());
            nuevoUsuario.setFechaNacimiento(fechaNacimiento);
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
    public boolean updUsuario(JSONObject data,String usermail) {
        Conexion cn = new Conexion();
        cn.abrirConexion();
        try {
            Usuario usuario = getUsuarioByMail(cn, usermail);
            
            data.remove(USUARIO_TIPO_JSON_FIELD);
            
            Date fechaNacimiento = Tools.DateFormatter(data.getString(FECHA_NACIMIENTO_JSON_FIELD));
            data.remove(FECHA_NACIMIENTO_JSON_FIELD);
            
            Usuario nuevoUsuario = new ObjectMapper()
                    .readValue(data.toString(), Usuario.class);
            
            usuario.setNombre(nuevoUsuario.getNombre());
            usuario.setApellido(nuevoUsuario.getApellido());
            usuario.setFechaNacimiento(fechaNacimiento);
            //usuario.setImagen(nuevoUsuario.getImagen());

            cn.update(usuario);
            cn.cerrarConexion();
        } catch (Exception e) {
            e.printStackTrace();
            cn.cerrarConexion();
            return false;
        }
        return true;
    }

    
    public boolean updUsuario_pathimagen(String path_imagen,String usermail) {
        Conexion cn = new Conexion();
        cn.abrirConexion();
        try {
            Usuario usuario = getUsuarioByMail(cn, usermail);
            
            usuario.setImagen(path_imagen);
            
            cn.update(usuario);
            cn.cerrarConexion();
        } catch (Exception e) {
            e.printStackTrace();
            cn.cerrarConexion();
            return false;
        }
        return true;
    }
    
    public static Usuario getUsuarioByMail(Conexion cn,String mail) {
        //Conexion cn = new Conexion();
        Usuario u = null;
        //cn.abrirConexion();

        List<Object> usuarioLista = cn.getListQuery("FROM Usuario WHERE mail = '" + mail + "'");

        if (!usuarioLista.isEmpty()) {
            u = (Usuario) usuarioLista.get(0);
        }

        return u;
    }
    
    public static Integer getIdUsuarioByMail(Conexion cn,String mail) {
        //Conexion cn = new Conexion();
        Integer u = null;
        //cn.abrirConexion();

        List<Integer> usuarioLista = cn.getListQuery("select u.id FROM Usuario u WHERE u.mail = '" + mail + "'");

        if (!usuarioLista.isEmpty()) {
            u = (Integer) usuarioLista.get(0);
        }

        return u;
    }
    
    public static Usuario getById(Conexion cn,int id) {
        //Conexion cn = new Conexion();
        Usuario u = null;
        //cn.abrirConexion();

        List<Object> usuarioLista = cn.getListQuery("FROM Usuario WHERE id = " + id);

        if (!usuarioLista.isEmpty()) {
            u = (Usuario) usuarioLista.get(0);
        }

        return u;
    }
    
    //Esto no es una baja logica, es una eliminacion permanente del registro
    public boolean eliminarUsuario(String mail){
        Conexion cn = new Conexion();
        cn.abrirConexion();
        
        Usuario u = getUsuarioByMail(cn,mail);
        
        List<modelos.Like> list_likes = new ArrayList<Like>();
        list_likes = cn.getListQuery("from modelos.Like WHERE usuario.id = "+u.getId());
        List<modelos.Compartido> list_compartidos = new ArrayList<modelos.Compartido>();
        list_compartidos = cn.getListQuery("from modelos.Compartido WHERE usuario.id = "+u.getId());
        
        
        //u = getUsuarioByMail(cn,mail);
        try {
            cn.deleteList(list_likes);
            cn.deleteList(list_compartidos);
            cn.delete(u);
        } catch (Exception e) {
            e.printStackTrace();

            cn.cerrarConexion();
            return false;
        }

        cn.cerrarConexion();
        return true;
    }
    
    /*public static List<JSONObject> setData(Usuario usuario,String usermail) throws JsonProcessingException, JSONException
    {
        Conexion cn = new Conexion();
        cn.abrirConexion();
        List<JSONObject> list = new ArrayList<JSONObject>();
        
        JSONObject jobj = Tools.convertObj_toJSON(usuario);
        
        int idUsuario = usuario.getId();
        
        List<Artista> artista = cn.getListQuery("from modelos.Artista WHERE usuario.id = "+idUsuario);
        if(!artista.isEmpty())
        {
            jobj.put("artista", new JSONArray(ArtistaNegocio.setData(artista,usermail,true)));
        }
        else
        {
            jobj.put("seguidores", SeguidosNegocio.getSeguidores(idUsuario).size());
            jobj.put("seguido", SeguidosNegocio.getSeguimiento(idUsuario,usermail));
        }

        jobj.put("object_type", "Usuario");
        
        list.add(jobj);
        
        cn.cerrarConexion();
        return list;
    }*/
    
    public static List<JSONObject> setData(Conexion cn,List<Usuario> usuarios,String usermail,boolean w_integrantes) throws JsonProcessingException, JSONException
    {
        //Conexion cn = new Conexion();
        //cn.abrirConexion();
        List<JSONObject> list = new ArrayList<JSONObject>();
        
        
        
        for(Usuario usuario : usuarios)
        {
            JSONObject jobj = Tools.convertObj_toJSON(usuario);
            int idUsuario = usuario.getId();
            if(usuario.getUsuarioTipo().getId() != 1)
            {
                List<Artista> artista = cn.getListQuery("from modelos.Artista WHERE usuario.id = "+idUsuario);
                if(!artista.isEmpty()) //es artista
                {
                    jobj.put("artista", new JSONArray(ArtistaNegocio.setData(cn,artista,usermail,w_integrantes,false)));
                }
            }
            else
            {
                jobj.put("seguidores", SeguidosNegocio.getCountSeguidores(cn,idUsuario));
                jobj.put("seguido", SeguidosNegocio.getSeguimiento(cn,idUsuario,usermail));
            }
            jobj.put("object_type", "Usuario");
            list.add(jobj);
    
            
        }
        //cn.cerrarConexion();
        return list;
    }
    
  
    /*public static List<JSONObject> setData(List<Usuario> usuarios,String usermail,boolean only_oyente) throws JsonProcessingException, JSONException
    {
        Conexion cn = new Conexion();
        cn.abrirConexion();
        List<JSONObject> list = new ArrayList<JSONObject>();
        
        
        
        for(Usuario usuario : usuarios)
        {
            JSONObject jobj = Tools.convertObj_toJSON(usuario);
            int idUsuario = usuario.getId();
            
            List<Artista> artista = cn.getListQuery("from modelos.Artista WHERE usuario.id = "+idUsuario);
             
            boolean agregar = true;
            if(!artista.isEmpty()) //es artista
            {
                if(!only_oyente)
                {
                    jobj.put("artista", new JSONArray(ArtistaNegocio.setData(artista,usermail,false)));
                }
                else
                    agregar = false;
            }
            else
            {
                jobj.put("seguidores", SeguidosNegocio.getSeguidores(idUsuario).size());
                jobj.put("seguido", SeguidosNegocio.getSeguimiento(idUsuario,usermail));
            }
            
            if(agregar)
            {
                jobj.put("object_type", "Usuario");
                list.add(jobj);
            }
    
            
        }
        cn.cerrarConexion();
        return list;
    }*/
    
}
