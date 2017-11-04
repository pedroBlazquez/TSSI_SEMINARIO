package modelos;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "Seguidos")
public class Seguidos {

    @Embeddable
    public static class embID_Seguidos implements Serializable {
        private static final long serialVersionUID = 1L;

        // FK
        @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
        @JoinColumn(name = "idSeguidor")
        private Usuario seguidor;

        // FK
        @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
        @JoinColumn(name = "idSeguido")
        private Usuario seguido;

        public embID_Seguidos() {
            super();
        }
    }

    @EmbeddedId
    private embID_Seguidos idSeguidos = new embID_Seguidos();

    public Seguidos() {
        super();
    }

    public Seguidos(embID_Seguidos idSeguidos) {
        super();
        this.idSeguidos = idSeguidos;
    }

    public Seguidos(Usuario seguidor, Usuario seguido) {
        super();
        this.idSeguidos.seguidor = seguidor;
        this.idSeguidos.seguido = seguido;
    }

    public Usuario getSeguidor() {
        return idSeguidos.seguidor;
    }

    public void setSeguidor(Usuario seguidor) {
        this.idSeguidos.seguidor = seguidor;
    }

    public Usuario getSeguido() {
        return idSeguidos.seguido;
    }

    public void setSeguido(Usuario seguido) {
        this.idSeguidos.seguido = seguido;
    }

}
