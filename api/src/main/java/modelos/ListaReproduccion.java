package modelos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ListasReproduccion")
public class ListaReproduccion {

    @Id
    @Column(name = "idLista")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "fechaAlta", nullable = false)
    private Date fechaAlta;

    @Column(name = "estado", nullable = false)
    private boolean estado = false;

    @Column(name = "privacidad", nullable = false)
    private boolean privacidad = false; // false = publica

    // FK
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    // Relaciones
    @JsonIgnore
    @OneToMany(mappedBy = "idCancionLista.lista", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<CancionLista> cancionesLista = new ArrayList<>();

    public ListaReproduccion(String nombre, Date fechaAlta, boolean estado, boolean privacidad, Usuario usuario) {
        super();
        this.nombre = nombre;
        this.fechaAlta = fechaAlta;
        this.estado = estado;
        this.privacidad = privacidad;
        this.usuario = usuario;
    }

    public ListaReproduccion() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public boolean isPrivacidad() {
        return privacidad;
    }

    public void setPrivacidad(boolean privacidad) {
        this.privacidad = privacidad;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<CancionLista> getCancionesLista() {
        return cancionesLista;
    }

    public void setCancionesLista(List<CancionLista> cancionesLista) {
        this.cancionesLista = cancionesLista;
    }

    public int getId() {
        return id;
    }

}
