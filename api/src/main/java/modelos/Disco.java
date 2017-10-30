package modelos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="Discos")
public class Disco {

    @Id
    @Column(name = "idDisco")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "fechaPublicacion", nullable = false)
    private Date fechaPublicacion;
    
    //FK
    @ManyToOne(fetch= FetchType.LAZY, cascade=CascadeType.ALL)
  	@JoinColumn (name="idArtista") 
  	private Artista artista;
    
    //Relaciones
    @OneToMany(mappedBy = "idDiscoAlbum.disco", fetch= FetchType.LAZY, cascade=CascadeType.ALL) 
   	private List<DiscoAlbum> albumsDisco = new ArrayList<>();
    
    @OneToMany(mappedBy = "idGeneroDisco.disco", fetch= FetchType.LAZY, cascade=CascadeType.ALL) 
   	private List<GeneroDisco> generosDisco = new ArrayList<>();
    
    @OneToMany(mappedBy = "idCancionDisco.disco", fetch= FetchType.LAZY, cascade=CascadeType.ALL) 
   	private List<CancionDisco> cancionesDisco = new ArrayList<>();
    
    @OneToMany(mappedBy = "disco", fetch= FetchType.LAZY, cascade=CascadeType.ALL) 
   	private List<AccionLikeCompartir> acciones = new ArrayList<>();
    
    
    public Disco() {
     }


	public Disco(String nombre, Date fechaPublicacion, Artista artista) {
		super();
		this.nombre = nombre;
		this.fechaPublicacion = fechaPublicacion;
		this.artista = artista;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}


	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
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


	public List<DiscoAlbum> getAlbumsDisco() {
		return albumsDisco;
	}


	public void setAlbumsDisco(List<DiscoAlbum> albumsDisco) {
		this.albumsDisco = albumsDisco;
	}


	public List<GeneroDisco> getGenerosDisco() {
		return generosDisco;
	}


	public void setGenerosDisco(List<GeneroDisco> generosDisco) {
		this.generosDisco = generosDisco;
	}


	public List<CancionDisco> getCancionesDisco() {
		return cancionesDisco;
	}


	public void setCancionesDisco(List<CancionDisco> cancionesDisco) {
		this.cancionesDisco = cancionesDisco;
	}


	public List<AccionLikeCompartir> getAcciones() {
		return acciones;
	}


	public void setAcciones(List<AccionLikeCompartir> acciones) {
		this.acciones = acciones;
	}
    
    
}