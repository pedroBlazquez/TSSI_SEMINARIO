package modelos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "Usuarios")
public class Usuario {

    @Id
    @Column(name = "idUsuario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "mail", nullable = false)
    private String mail;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "apellido", nullable = false)
    private String apellido;

    @Column(name = "fechaAlta", nullable = false)
    private Date fechaAlta;

    @Column(name = "estado", nullable = false)
    private boolean estado;

    // FK
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idUsuarioTipo")
    private UsuarioTipo usuarioTipo;

    // Relaciones
    @JsonIgnore
    @OneToMany(mappedBy = "idSeguidos.seguidor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Seguidos> seguidos = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "idSeguidos.seguido", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Seguidos> seguidores = new ArrayList<>();

    @JsonIgnore
    @OneToOne(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Artista artista;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Compartido> compartidos = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Like> likes = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ListaReproduccion> listasreproduccion = new ArrayList<>();

    public Usuario() {

    }

    public Usuario(String mail, String contrasenia, String nombre, String apellido, Date fechaAlta, boolean estado,
            UsuarioTipo usuarioTipo) {
        super();
        this.mail = mail;
        this.password = contrasenia;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaAlta = fechaAlta;
        this.estado = estado;
        this.usuarioTipo = usuarioTipo;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getContrasenia() {
        return password;
    }

    public void setContrasenia(String password) {
        this.password = password;
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

    public UsuarioTipo getUsuarioTipo() {
        return usuarioTipo;
    }

    public void setUsuarioTipo(UsuarioTipo usuarioTipo) {
        this.usuarioTipo = usuarioTipo;
    }

    public int getId() {
        return id;
    }

    public List<Seguidos> getSeguidos() {
        return seguidos;
    }

    public void setSeguidos(List<Seguidos> seguidos) {
        this.seguidos = seguidos;
    }

    public List<Seguidos> getSeguidores() {
        return seguidores;
    }

    public void setSeguidores(List<Seguidos> seguidores) {
        this.seguidores = seguidores;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public List<Compartido> getCompartidos() {
        return compartidos;
    }

    public void setCompartidos(List<Compartido> compartidos) {
        this.compartidos = compartidos;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

    public List<ListaReproduccion> getListasreproduccion() {
        return listasreproduccion;
    }

    public void setListasreproduccion(List<ListaReproduccion> listasreproduccion) {
        this.listasreproduccion = listasreproduccion;
    }

}
