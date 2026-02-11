package objetos;

import java.io.Serializable;
import javax.persistence.Embeddable;

@Embeddable
public class ImparteId implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5336946467614369158L;
	private int profesor;      // id del profesor
    private String modulo;     // código del módulo

    public ImparteId() {
    }

    public ImparteId(int profesor, String modulo) {
        this.profesor = profesor;
        this.modulo = modulo;
    }

    public int getProfesor() {
        return profesor;
    }

    public void setProfesor(int profesor) {
        this.profesor = profesor;
    }

    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    // Obligatorio en claves compuestas
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ImparteId)) return false;

        ImparteId that = (ImparteId) o;

        if (profesor != that.profesor) return false;
        return modulo.equals(that.modulo);
    }

    @Override
    public int hashCode() {
        int result = profesor;
        result = 31 * result + modulo.hashCode();
        return result;
    }
}
