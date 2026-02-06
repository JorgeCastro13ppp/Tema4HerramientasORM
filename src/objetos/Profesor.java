package objetos;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "profesor")
public class Profesor implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2915861954816911919L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    public Profesor() {
    }

    public Profesor(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Profesor [id=" + id + ", nombre=" + nombre + "]";
    }
}
