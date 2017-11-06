package modelos;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "DiscosAlbum")
public class DiscoAlbum {

    @Embeddable
    public static class embID_DiscoAlbum implements Serializable {
        private static final long serialVersionUID = 1L;

        // FK
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "idAlbum")
        private Album album;

        // FK
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "idDisco")
        private Disco disco;

        public embID_DiscoAlbum() {
            super();
        }
    }

    @EmbeddedId
    private embID_DiscoAlbum idDiscoAlbum = new embID_DiscoAlbum();

    public DiscoAlbum() {
        super();
    }

    public DiscoAlbum(embID_DiscoAlbum idDiscoAlbum) {
        super();
        this.idDiscoAlbum = idDiscoAlbum;
    }

    public DiscoAlbum(Album album, Disco disco) {
        super();
        this.idDiscoAlbum.album = album;
        this.idDiscoAlbum.disco = disco;
    }

    public Album getAlbum() {
        return idDiscoAlbum.album;
    }

    public void setAlbum(Album album) {
        this.idDiscoAlbum.album = album;
    }

    public Disco getDisco() {
        return idDiscoAlbum.disco;
    }

    public void setDisco(Disco disco) {
        this.idDiscoAlbum.disco = disco;
    }

}
