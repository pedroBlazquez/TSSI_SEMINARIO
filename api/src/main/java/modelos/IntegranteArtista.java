package modelos;

import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "IntegrantesArtista")
public class IntegranteArtista {

    @Id
    @Column(name = "idIntegrante")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombres", nullable = false)
    private String nombre;

    @Column(name = "apellido", nullable = false)
    private String apellido;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Buenos_Aires")
    @Column(name = "fechaNacimiento", nullable = false)
    private Date fechaNacimiento;

    // FK
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idRol")
    private IntegranteRol rol;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idArtista")
    private Artista artista;

    public IntegranteArtista() {
    }

    public IntegranteArtista(String nombre, String apellido, Date fechaNacimiento, IntegranteRol rol, Artista artista) {
        super();
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.rol = rol;
        this.artista = artista;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public IntegranteRol getRol() {
        return rol;
    }

    public void setRol(IntegranteRol rol) {
        this.rol = rol;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public int getId() {
        return id;
    }

}
