package negocio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import aplicacion.Tools;
import conexion.Conexion;
import modelos.Artista;
import modelos.Cancion;
import modelos.CancionDisco;
import modelos.Disco;

public class DiscoNegocio {
    
    public static boolean AltaDisco(String nombre,Date fechaPublicacion,  ArrayList<String> canciones, String usermail)
    {
        try
        {
            Conexion cn = new Conexion();
            cn.abrirConexion();
            //convierte lista de canciones en string con comas para query
            String INcanciones = Tools.Convert_Array_toStringComma(canciones);
            //busca canciones para agregar al disco
            List<Cancion> list_canciones = cn.getListQuery("from modelos.Cancion WHERE id IN ("+INcanciones+")");
            //busca el artista correspondiente al usuario logueado
            List<Artista> list_artistas = cn.getListQuery("from modelos.Artista WHERE usuario.mail = '"+usermail+"'");
            //crea el nuevo disco
            Disco new_Disco = new Disco(nombre,fechaPublicacion,list_artistas.get(0));
            //crea la nueva conexion muchos a muchos CancionDisco
            List<CancionDisco> new_CancionDisco = new ArrayList<CancionDisco>();
            for(Cancion c : list_canciones)
            {
                new_CancionDisco.add(new CancionDisco(c, new_Disco));
            }
            cn.add(new_Disco);
            cn.addList(new_CancionDisco);
            cn.cerrarConexion();
            return true;
        }catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
