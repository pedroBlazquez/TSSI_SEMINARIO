package modelos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "AccionesLikeCompartir")
public class AccionLikeCompartir {

    @Id
    @Column(name = "idAccionLikeCompartir")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "fechaAccion", nullable = false)
    private Date fechaAccion;

    // FK
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idCancion")
    private Cancion cancion;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idDisco")
    private Disco disco;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idArtista")
    private Artista artista;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idAlbum")
    private Album album;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idPublicacion")
    private Publicacion publicacion;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idEvento")
    private Evento evento;
    
    //relaciones
    @OneToOne(mappedBy = "accion", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private Like like;
    
    @OneToOne(mappedBy = "accion", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private Compartido compartido;
    
    public AccionLikeCompartir() {
    }

    public AccionLikeCompartir(Date fechaAccion) {
        super();
        this.fechaAccion = fechaAccion;
    }

    public Cancion getCancion() {
        return cancion;
    }

    public void setCancion(Cancion cancion) {
        this.cancion = cancion;
    }

    public Disco getDisco() {
        return disco;
    }

    public void setDisco(Disco disco) {
        this.disco = disco;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Publicacion getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Publicacion publicacion) {
        this.publicacion = publicacion;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public int getId() {
        return id;
    }

    public Date getFechaAccion() {
        return fechaAccion;
    }

    public void setFechaAccion(Date fechaAccion) {
        this.fechaAccion = fechaAccion;
    }

    public Like getLike() {
        return like;
    }

    public void setLike(Like like) {
        this.like = like;
    }

    public Compartido getCompartido() {
        return compartido;
    }

    public void setCompartido(Compartido compartido) {
        this.compartido = compartido;
    }

}
