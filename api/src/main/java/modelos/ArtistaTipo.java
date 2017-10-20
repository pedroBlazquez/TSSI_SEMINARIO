package modelos;

import javax.persistence.*;

@Entity
@Table(name="ArtistaTipos")
public class ArtistaTipo {

    @Id
    @Column(name = "idArtistaTipo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "descripcion", nullable = false)
    private String descripcion;
    
    public ArtistaTipo() {
     }
    
    public ArtistaTipo(String descripcion) {
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
