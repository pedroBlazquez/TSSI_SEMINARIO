package modelos;

import java.io.Serializable;

import javax.persistence.*;


@Entity
@Table(name="CancionesLista")
public class CancionLista {

	@Embeddable
	public static class embID_CancionLista implements Serializable { 
		private static final long serialVersionUID = 1L;

		//FK
		@ManyToOne(fetch= FetchType.LAZY, cascade=CascadeType.ALL)
		@JoinColumn (name="idCancion") 
		private Cancion cancion;
		
		//FK
		@ManyToOne(fetch= FetchType.LAZY, cascade=CascadeType.ALL)
		@JoinColumn (name="idLista") 
		private ListaReproduccion lista;

		public embID_CancionLista() {
			super();
		}
	}
	
	@EmbeddedId
	private embID_CancionLista idCancionLista =  new embID_CancionLista();

	public CancionLista() {
		super();
	}
	
	public CancionLista(embID_CancionLista idCancionLista) {
		super();
		this.idCancionLista = idCancionLista;
	}
	
	public CancionLista(Cancion cancion, ListaReproduccion lista) {
		super();
		this.idCancionLista.cancion = cancion;
		this.idCancionLista.lista = lista;
	}
	
	public Cancion getCancion() {
		return idCancionLista.cancion;
	}

	public void setCancion(Cancion cancion) {
		this.idCancionLista.cancion = cancion;
	}

	public ListaReproduccion getLista() {
		return idCancionLista.lista;
	}

	public void setLista(ListaReproduccion lista) {
		this.idCancionLista.lista = lista;
	}

	
}
