package modelos;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="AccionesLikeCompartir")
public class AccionLikeCompartir {

    @Id
    @Column(name = "idAccionLikeCompartir")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "fechaAccion", nullable = false)
    private Date fechaAccion;
    
    //FK
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCancion")
    private Cancion cancion;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idDisco")
    private Disco disco;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idArtista")
    private Artista artista;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAlbum")
    private Album album;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPublicacion")
    private Publicacion publicacion;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEvento")
    private Evento evento;
    
    
    public AccionLikeCompartir() {
     }


	public AccionLikeCompartir(Date fechaAccion) {
		super();
		this.fechaAccion = fechaAccion;
	}


	public Date getfechaAccion() {
		return fechaAccion;
	}


	public void setfechaAccion(Date fechaAccion) {
		this.fechaAccion = fechaAccion;
	}


	public Cancion getCancion() {
		return cancion;
	}


	public void setCancion(Cancion cancion) {
		this.cancion = cancion;
	}


	public Disco getDisco() {
		return disco;
	}


	public void setDisco(Disco disco) {
		this.disco = disco;
	}


	public Artista getArtista() {
		return artista;
	}


	public void setArtista(Artista artista) {
		this.artista = artista;
	}


	public Album getAlbum() {
		return album;
	}


	public void setAlbum(Album album) {
		this.album = album;
	}


	public Publicacion getPublicacion() {
		return publicacion;
	}


	public void setPublicacion(Publicacion publicacion) {
		this.publicacion = publicacion;
	}


	public Evento getEvento() {
		return evento;
	}


	public void setEvento(Evento evento) {
		this.evento = evento;
	}


	public int getId() {
		return id;
	}

}
