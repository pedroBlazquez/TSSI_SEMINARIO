package modelos;

import javax.persistence.*;

@Entity
@Table(name = "IntegranteRoles")
public class IntegranteRol {

    @Id
    @Column(name = "idRol")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    public IntegranteRol() {
    }

    public IntegranteRol(String descripcion) {
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
