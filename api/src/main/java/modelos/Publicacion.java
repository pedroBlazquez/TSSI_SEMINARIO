package modelos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Publicaciones")
public class Publicacion {

    @Id
    @Column(name = "idPublicacion")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "texto", nullable = false)
    private String texto;

    @Column(name = "fechaPublicacion", nullable = false)
    private Date fechaPublicacion;

    // FK
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idArtista")
    private Artista artista;

    // Relaciones
    @JsonIgnore
    @OneToMany(mappedBy = "publicacion", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<AccionLikeCompartir> acciones = new ArrayList<>();

    public Publicacion() {
    }

    public Publicacion(String texto, Date fechaPublicacion, Artista artista) {
        super();
        this.texto = texto;
        this.fechaPublicacion = fechaPublicacion;
        this.artista = artista;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
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

    public List<AccionLikeCompartir> getAcciones() {
        return acciones;
    }

    public void setAcciones(List<AccionLikeCompartir> acciones) {
        this.acciones = acciones;
    }

    public int getId() {
        return id;
    }

}
