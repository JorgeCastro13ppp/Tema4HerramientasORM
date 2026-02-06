package objetos;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "alumno")
public class Alumno implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 74197664335287344L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "curso", nullable = false)
    private String curso;

    @Column(name = "telefono")
    private int telefono;

    public Alumno() {
    }

    public Alumno(String nombre, String curso, int telefono) {
        this.nombre = nombre;
        this.curso = curso;
        this.telefono = telefono;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCurso() {
        return curso;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Alumno [id=" + id + ", nombre=" + nombre +
               ", curso=" + curso + ", telefono=" + telefono + "]";
    }
}
