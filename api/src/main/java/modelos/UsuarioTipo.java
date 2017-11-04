package modelos;

import javax.persistence.*;

@Entity
@Table(name = "UsuarioTipos")
public class UsuarioTipo {

    @Id
    @Column(name = "idUsuarioTipo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    public UsuarioTipo() {
    }

    public UsuarioTipo(String descripcion) {
        // this.id = id;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
