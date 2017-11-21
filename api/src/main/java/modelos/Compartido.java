package modelos;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "Compartidos")
public class Compartido {

    @Id
    @Column(name = "idCompartido")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // FK
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    //relaciones
    @OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name = "idAccionLikeCompartir")
    private AccionLikeCompartir accion;

    public Compartido() {
    }

    public Compartido(Usuario usuario, AccionLikeCompartir accion) {
        super();
        this.usuario = usuario;
        this.accion = accion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public AccionLikeCompartir getAccion() {
        return accion;
    }

    public void setAcciones(AccionLikeCompartir accion) {
        this.accion = accion;
    }

    public int getId() {
        return id;
    }

}
