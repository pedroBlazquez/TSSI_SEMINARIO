package modelos;

import java.io.Serializable;

import javax.persistence.*;


@Entity
@Table(name="GenerosCancion")
public class GeneroCancion {

	@Embeddable
	public static class embID_GeneroCancion implements Serializable { 
		private static final long serialVersionUID = 1L;

		//FK
		@ManyToOne(fetch= FetchType.LAZY, cascade=CascadeType.ALL)
		@JoinColumn (name="idGenero") 
		private Genero genero;
		
		//FK
		@ManyToOne(fetch= FetchType.LAZY, cascade=CascadeType.ALL)
		@JoinColumn (name="idCancion") 
		private Cancion cancion;

		public embID_GeneroCancion() {
			super();
		}
	}
	
	@EmbeddedId
	private embID_GeneroCancion idGeneroCancion =  new embID_GeneroCancion();

	public GeneroCancion() {
		super();
	}
	
	public GeneroCancion(embID_GeneroCancion idGeneroCancion) {
		super();
		this.idGeneroCancion = idGeneroCancion;
	}
	
	public GeneroCancion(Genero genero, Cancion cancion) {
		super();
		this.idGeneroCancion.genero = genero;
		this.idGeneroCancion.cancion = cancion;
	}
	
	public Genero getGenero() {
		return idGeneroCancion.genero;
	}

	public void setGenero(Genero genero) {
		this.idGeneroCancion.genero = genero;
	}

	public Cancion getCancion() {
		return idGeneroCancion.cancion;
	}

	public void setCancion(Cancion cancion) {
		this.idGeneroCancion.cancion = cancion;
	}

	
}
