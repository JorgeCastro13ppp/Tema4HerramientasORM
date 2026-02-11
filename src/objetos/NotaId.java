package objetos;

import java.io.Serializable;
import javax.persistence.Embeddable;

@Embeddable
public class NotaId implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4224388155513530054L;
	private int alumno;   // id del alumno
    private String modulo; // código del módulo

    public NotaId() {
    }

    public NotaId(int alumno, String modulo) {
        this.alumno = alumno;
        this.modulo = modulo;
    }

    public int getAlumno() {
        return alumno;
    }

    public void setAlumno(int alumno) {
        this.alumno = alumno;
    }

    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    // Muy importante en claves compuestas
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NotaId)) return false;

        NotaId that = (NotaId) o;

        if (alumno != that.alumno) return false;
        return modulo.equals(that.modulo);
    }

    @Override
    public int hashCode() {
        int result = alumno;
        result = 31 * result + modulo.hashCode();
        return result;
    }
}
