package modelos;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Likes")
public class Like {

    @Id
    @Column(name = "idLike")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // FK
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    
    @OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name = "idAccionLikeCompartir")
    private AccionLikeCompartir accion;

    public Like() {
    }

    public Like(Usuario usuario, AccionLikeCompartir accion) {
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
