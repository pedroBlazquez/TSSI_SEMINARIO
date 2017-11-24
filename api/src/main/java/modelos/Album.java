package modelos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name = "Albums")
public class Album {

    @Id
    @Column(name = "idAlbum")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "portada", nullable = true)
    private String portada;
    
    @Column(name = "fechaPublicacion", nullable = false)
    private Date fechaPublicacion;

    // FK
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idArtista")
    private Artista artista;

    //Relaciones
    @OneToMany(mappedBy = "idDiscoAlbum.album", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private List<DiscoAlbum> discosAlbum = new ArrayList<>();
    
    @JsonIgnore
    @OneToMany(mappedBy = "album", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<AccionLikeCompartir> acciones = new ArrayList<>();

    public Album() {
    }

    public Album(String nombre,Date fechaPublicacion, Artista artista,String portada) {
        super();
        this.nombre = nombre;
        this.fechaPublicacion = fechaPublicacion;
        this.artista = artista;
        this.portada = portada;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public List<DiscoAlbum> getDiscosAlbum() {
        return discosAlbum;
    }

    public void setDiscosAlbum(List<DiscoAlbum> discosAlbum) {
        this.discosAlbum = discosAlbum;
    }

    public List<AccionLikeCompartir> getAcciones() {
        return acciones;
    }

    public void setAcciones(List<AccionLikeCompartir> acciones) {
        this.acciones = acciones;
    }

    public int getId() {
        return id;
    }

    public String getPortada() {
        return portada;
    }

    public void setPortada(String portada) {
        this.portada = portada;
    }

    

}
