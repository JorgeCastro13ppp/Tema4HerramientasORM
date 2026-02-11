package objetos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "nota")
public class Nota implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6952810584490237115L;

	@EmbeddedId
    private NotaId id;

    @ManyToOne
    @MapsId("alumno")  // conecta con el campo alumno de NotaId
    @JoinColumn(name = "alumno")
    private Alumno alumno;

    @ManyToOne
    @MapsId("modulo")  // conecta con el campo modulo de NotaId
    @JoinColumn(name = "modulo")
    private Modulo modulo;

    @Column(name = "nota")
    private double nota;

    public Nota() {
    }

    public Nota(Alumno alumno, Modulo modulo, double nota) {
        this.alumno = alumno;
        this.modulo = modulo;
        this.id = new NotaId(alumno.getId(), modulo.getCodigo());
        this.nota = nota;
    }

    public double getNota() {
        return nota;
    }
    

    public void setNota(double nota) {
		this.nota = nota;
	}

	public Alumno getAlumno() {
        return alumno;
    }

    public Modulo getModulo() {
        return modulo;
    }

    @Override
    public String toString() {
        return alumno.getNombre() + " - " +
               modulo.getNombre() + " - " +
               nota;
    }
}
