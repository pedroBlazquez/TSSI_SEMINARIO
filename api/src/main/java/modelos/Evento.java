package modelos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Eventos")
public class Evento {

    @Id
    @Column(name = "idEvento")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "fechaEvento", nullable = false)
    private Date fechaEvento;

    @Column(name = "direccion", nullable = false)
    private String direccion;

    @Column(name = "costo", nullable = true)
    private float costo;

    @Column(name = "fechaPublicacion", nullable = false)
    private Date fechaPublicacion;

    @Column(name = "imagen", nullable = true)
    private String imagen;

    // FK
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idArtista")
    private Artista artista;

    // Relaciones
    @JsonIgnore
    @OneToMany(mappedBy = "evento", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<AccionLikeCompartir> acciones = new ArrayList<>();

    public Evento() {
    }

    public Evento(String nombre, String descripcion, Date fechaEvento, String direccion, float costo,
            Date fechaPublicacion, Artista artista,String imagen) {
        super();
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaEvento = fechaEvento;
        this.direccion = direccion;
        this.costo = costo;
        this.fechaPublicacion = fechaPublicacion;
        this.artista = artista;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(Date fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

}
