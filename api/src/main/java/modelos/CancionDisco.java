package modelos;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "CancionesDisco")
public class CancionDisco {

    @Embeddable
    public static class embID_CancionDisco implements Serializable {
        private static final long serialVersionUID = 1L;

        // FK
        @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
        @JoinColumn(name = "idCancion")
        private Cancion cancion;

        // FK
        @JsonIgnore
        @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
        @JoinColumn(name = "idDisco")
        private Disco disco;

        public embID_CancionDisco() {
            super();
        }
    }

    @EmbeddedId
    private embID_CancionDisco idCancionDisco = new embID_CancionDisco();

    public CancionDisco() {
        super();
    }

    public CancionDisco(embID_CancionDisco idCancionDisco) {
        super();
        this.idCancionDisco = idCancionDisco;
    }

    public CancionDisco(Cancion cancion, Disco disco) {
        super();
        this.idCancionDisco.cancion = cancion;
        this.idCancionDisco.disco = disco;
    }
    
    
    public Cancion getCancion() {
        return idCancionDisco.cancion;
    }

    
    public void setCancion(Cancion cancion) {
        this.idCancionDisco.cancion = cancion;
    }
    
    @JsonIgnore
    public Disco getDisco() {
        return idCancionDisco.disco;
    }
    @JsonProperty
    public void setDisco(Disco disco) {
        this.idCancionDisco.disco = disco;
    }

}
