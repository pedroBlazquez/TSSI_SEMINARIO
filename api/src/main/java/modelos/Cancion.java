package modelos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "Canciones")
public class Cancion {

    @Id
    @Column(name = "idCancion")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "archivo", nullable = false)
    private String archivo;

    @Column(name = "fechaPublicacion", nullable = false)
    private Date fechaPublicacion;

    // FK
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "idArtista")
    private Artista artista;

    // Relaciones
    @OneToMany(mappedBy = "idCancionDisco.cancion", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CancionDisco> discosCancion = new ArrayList<>();

    @OneToMany(mappedBy = "idGeneroCancion.cancion", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<GeneroCancion> generosCancion = new ArrayList<>();

    @OneToMany(mappedBy = "idCancionLista.cancion", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CancionLista> listasCancion = new ArrayList<>();

    @OneToMany(mappedBy = "cancion", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<AccionLikeCompartir> acciones = new ArrayList<>();

    public Cancion() {
    }

    public Cancion(String nombre, String archivo, Date fechaPublicacion, Artista artista) {
        super();
        this.nombre = nombre;
        this.archivo = archivo;
        this.fechaPublicacion = fechaPublicacion;
        this.artista = artista;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
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

    public List<CancionDisco> getDiscosCancion() {
        return discosCancion;
    }

    public void setDiscosCancion(List<CancionDisco> discosCancion) {
        this.discosCancion = discosCancion;
    }

    public List<GeneroCancion> getGenerosCancion() {
        return generosCancion;
    }

    public void setGenerosCancion(List<GeneroCancion> generosCancion) {
        this.generosCancion = generosCancion;
    }

    public List<CancionLista> getListasCancion() {
        return listasCancion;
    }

    public void setListasCancion(List<CancionLista> listasCancion) {
        this.listasCancion = listasCancion;
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
