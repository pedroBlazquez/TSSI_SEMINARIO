package modelos;

import javax.persistence.*;

@Entity
@Table(name="UsuariosTipos")
public class UsuarioTipo {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "descripcion", nullable = false)
    private String descripcion;
    
    public UsuarioTipo(int id, String Descripcion) {
        this.id = id;
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
