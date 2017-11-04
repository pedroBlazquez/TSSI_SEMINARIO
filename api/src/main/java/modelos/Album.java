package modelos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "Albums")
public class Album {

    @Id
    @Column(name = "idAlbum")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    // FK
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "idArtista")
    private Artista artista;

    // Relaciones
    @OneToMany(mappedBy = "idDiscoAlbum.album", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<DiscoAlbum> discosAlbum = new ArrayList<>();

    @OneToMany(mappedBy = "album", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<AccionLikeCompartir> acciones = new ArrayList<>();

    public Album() {
    }

    public Album(String nombre, Artista artista) {
        super();
        this.nombre = nombre;
        this.artista = artista;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public List<DiscoAlbum> getDiscos() {
        return discosAlbum;
    }

    public void setDiscos(List<DiscoAlbum> discosAlbum) {
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

}
