package modelos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "GenerosArtista")
public class GeneroArtista {

    @Embeddable
    public static class embID_GeneroArtista implements Serializable {
        private static final long serialVersionUID = 1L;

        // FK
        @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
        @JoinColumn(name = "idGenero")
        private Genero genero;

        // FK
        @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
        @JoinColumn(name = "idArtista")
        private Artista artista;

        public embID_GeneroArtista() {
            super();
        }
    }

    @EmbeddedId
    private embID_GeneroArtista idGeneroArtista = new embID_GeneroArtista();

    public GeneroArtista() {
        super();
    }

    public GeneroArtista(embID_GeneroArtista idGeneroArtista) {
        super();
        this.idGeneroArtista = idGeneroArtista;
    }

    public GeneroArtista(Genero genero, Artista artista) {
        super();
        this.idGeneroArtista.genero = genero;
        this.idGeneroArtista.artista = artista;
    }

    public Genero getGenero() {
        return idGeneroArtista.genero;
    }

    public void setGenero(Genero genero) {
        this.idGeneroArtista.genero = genero;
    }

    public Artista getArtista() {
        return idGeneroArtista.artista;
    }

    public void setArtista(Artista artista) {
        this.idGeneroArtista.artista = artista;
    }

}
