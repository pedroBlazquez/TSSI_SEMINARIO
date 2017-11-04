package modelos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "Artistas")
public class Artista {

    @Id
    @Column(name = "idArtista")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombreFantasia", nullable = false)
    private String nombreFantasia;

    @Column(name = "fechaInicio", nullable = false)
    private Date fechaInicio;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    // FK
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "idArtistaTipo")
    private ArtistaTipo artistaTipo;

    // Relaciones
    @JsonIgnore
    @OneToMany(mappedBy = "artista", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<IntegranteArtista> integrantes = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "artista", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Evento> eventos = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "artista", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<AccionLikeCompartir> acciones = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "artista", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Publicacion> publicaciones = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "artista", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Disco> discos = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "idGeneroArtista.artista", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<GeneroArtista> generos = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "artista", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Cancion> canciones = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "artista", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Album> albums = new ArrayList<>();

    public Artista() {
    }

    public Artista(String nombreFantasia, Date fechaInicio, String descripcion, Usuario usuario,
            ArtistaTipo artistaTipo) {
        super();
        this.nombreFantasia = nombreFantasia;
        this.fechaInicio = fechaInicio;
        this.descripcion = descripcion;
        this.usuario = usuario;
        this.artistaTipo = artistaTipo;
    }

    public String getNombreFantasia() {
        return nombreFantasia;
    }

    public void setNombreFantasia(String nombreFantasia) {
        this.nombreFantasia = nombreFantasia;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public ArtistaTipo getArtistaTipo() {
        return artistaTipo;
    }

    public void setArtistaTipo(ArtistaTipo artistaTipo) {
        this.artistaTipo = artistaTipo;
    }

    public List<IntegranteArtista> getIntegrantes() {
        return integrantes;
    }

    public void setIntegrantes(List<IntegranteArtista> integrantes) {
        this.integrantes = integrantes;
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }

    public List<AccionLikeCompartir> getAcciones() {
        return acciones;
    }

    public void setAcciones(List<AccionLikeCompartir> acciones) {
        this.acciones = acciones;
    }

    public List<Publicacion> getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(List<Publicacion> publicaciones) {
        this.publicaciones = publicaciones;
    }

    public List<Disco> getDiscos() {
        return discos;
    }

    public void setDiscos(List<Disco> discos) {
        this.discos = discos;
    }

    public List<GeneroArtista> getGeneros() {
        return generos;
    }

    public void setGeneros(List<GeneroArtista> generos) {
        this.generos = generos;
    }

    public List<Cancion> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<Cancion> canciones) {
        this.canciones = canciones;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

}
