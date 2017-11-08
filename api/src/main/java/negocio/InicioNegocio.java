package negocio;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import aplicacion.Tools;
import conexion.Conexion;
import modelos.Artista;
import modelos.Cancion;
import modelos.CancionDisco;
import modelos.Disco;
import modelos.Genero;
import modelos.GeneroDisco;
import modelos.Usuario;

public class InicioNegocio {
    
    public static List<Object> getNovedades(String usermail)
    {
        try
        {
            Conexion cn = new Conexion();
            cn.abrirConexion();
            
            List<Usuario> usuarios = cn.getListQuery("from modelos.Usuario WHERE mail = '"+usermail+"'");
            Usuario usuario = usuarios.get(0);
            
            List<Object> list = new ArrayList<Object>();
            
            //obtiene artistas seguidos
            List<Artista> artistas_seguidos = cn.getListQuery(
                "select s.idSeguidos.seguido.artista from modelos.Seguidos s " + 
                "WHERE s.idSeguidos.seguidor.id = "+usuario.getId()+
                " and s.idSeguidos.seguido.usuarioTipo.id != " + 2); //CAMBIAR NUMERO ACORDE AL USUARIO TIPO OYENTE
            
            //Novedades de artistas seguidos
            String date_novedades = Tools.DateFormatter(Tools.GetDateDifference(2));
            for (Artista seguido : artistas_seguidos)
            {
                list.addAll(cn.getListQuery("from Cancion WHERE artista.id = "+seguido.getId()+" and fechaPublicacion > '"+date_novedades+"'"));
                list.addAll(cn.getListQuery("from Disco WHERE artista.id = "+seguido.getId()+" and fechaPublicacion > '"+date_novedades+"'"));
                list.addAll(cn.getListQuery("from Album WHERE artista.id = "+seguido.getId()+" and fechaPublicacion > '"+date_novedades+"'"));
                list.addAll(cn.getListQuery("from Publicacion WHERE artista.id = "+seguido.getId()+" and fechaPublicacion > '"+date_novedades+"'"));
                list.addAll(cn.getListQuery("from Evento WHERE artista.id = "+seguido.getId()+" and fechaPublicacion > '"+date_novedades+"'"));
            }

            //obtiene generos de estos artistas
            Set<Genero> generos_set = new TreeSet<Genero>(new Comparator<Genero>() {
                @Override
                public int compare(Genero o1, Genero o2) {
                    if(o1.getId() == o2.getId())
                        return 0;
                    else
                        return 1;
                }
            });
            for (Artista seguido : artistas_seguidos)
            {
                generos_set.addAll(cn.getListQuery("select ga.idGeneroArtista.genero from GeneroArtista ga WHERE ga.idGeneroArtista.artista.id = "+seguido.getId()+" order by ga.idGeneroArtista.genero.id"));
            }
            
            
            //obtener artistas seguidos
            // -> de estos obtener canciones, discos + lista, albums + lista, eventos, publicaciones, de los ultimos 2 dias
            // -> obtener generos de artistas (generos que me gustan)
            // ---->ULTIMO obtener canciones, discos + lista, albums + lista publicados hace 2 dias
            // ---->SUGERENCIA obtener canciones, discos + lista, albums + lista con mayor cantidad de likes, 2 de cada uno
            // ---->ULTIMO/SUGERENCIA obtener eventos publicados hace 2 dias, o que falte menos de 1 mes y sea del genero que me gusta
            // SUGERENCIA artistas del genero que me gusta, 
            //
            
            
            
            cn.cerrarConexion();
            return list;
        }catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
  
}
