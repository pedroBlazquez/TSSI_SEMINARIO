package modelos;

import javax.persistence.*;

@Entity
@Table(name = "Generos")
public class Genero {

    @Id
    @Column(name = "idGenero")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    public Genero() {
    }

    public Genero(String descripcion) {
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
