package objetos;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "imparte")
public class Imparte implements Serializable {

    @EmbeddedId
    private ImparteId id;

    @ManyToOne
    @MapsId("profesor")
    @JoinColumn(name = "profesor")
    private Profesor profesor;

    @ManyToOne
    @MapsId("modulo")
    @JoinColumn(name = "modulo")
    private Modulo modulo;

    public Imparte() {
    }

    public Imparte(Profesor profesor, Modulo modulo) {
        this.profesor = profesor;
        this.modulo = modulo;
        this.id = new ImparteId(profesor.getId(), modulo.getCodigo());
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public Modulo getModulo() {
        return modulo;
    }
}


