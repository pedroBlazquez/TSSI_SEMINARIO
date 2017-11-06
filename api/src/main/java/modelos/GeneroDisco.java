package modelos;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "GenerosDisco")
public class GeneroDisco {

    @Embeddable
    public static class embID_GeneroDisco implements Serializable {
        private static final long serialVersionUID = 1L;

        // FK
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "idGenero")
        private Genero genero;

        // FK
        @JsonIgnore
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "idDisco")
        private Disco disco;

        public embID_GeneroDisco() {
            super();
        }
    }

    @EmbeddedId
    private embID_GeneroDisco idGeneroDisco = new embID_GeneroDisco();

    public GeneroDisco() {
        super();
    }

    public GeneroDisco(embID_GeneroDisco idGeneroDisco) {
        super();
        this.idGeneroDisco = idGeneroDisco;
    }

    public GeneroDisco(Genero genero, Disco disco) {
        super();
        this.idGeneroDisco.genero = genero;
        this.idGeneroDisco.disco = disco;
    }

    public Genero getGenero() {
        return idGeneroDisco.genero;
    }

    public void setGenero(Genero genero) {
        this.idGeneroDisco.genero = genero;
    }

    public Disco getDisco() {
        return idGeneroDisco.disco;
    }

    public void setDisco(Disco disco) {
        this.idGeneroDisco.disco = disco;
    }

}
