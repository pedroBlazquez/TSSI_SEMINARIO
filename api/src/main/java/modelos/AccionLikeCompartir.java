package modelos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

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
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idCancion")
    private Cancion cancion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idDisco")
    private Disco disco;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idArtista")
    private Artista artista;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idAlbum")
    private Album album;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idPublicacion")
    private Publicacion publicacion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idEvento")
    private Evento evento;
    
    //relaciones
    @JsonIgnore
    @OneToOne(mappedBy = "accion", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private Like like;
    
    @JsonIgnore
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
    @JsonIgnore
    public Like getLike() {
        return like;
    }
    @JsonProperty
    public void setLike(Like like) {
        this.like = like;
    }
    @JsonIgnore
    public Compartido getCompartido() {
        return compartido;
    }

    @JsonProperty
    public void setCompartido(Compartido compartido) {
        this.compartido = compartido;
    }

}
