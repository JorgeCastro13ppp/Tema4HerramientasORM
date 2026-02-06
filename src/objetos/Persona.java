package objetos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "persona")
public class Persona implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5563649988340107708L;

	@Id
    @Column(name = "dni", length = 9)
    private String dni;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "email")
    private String email;

    @Column(name = "edad")
    private int edad;

    @Column(name = "telefono")
    private int telefono;

    public Persona() {
        // obligatorio para JPA
    }

    public Persona(String dni, String nombre, String email, int edad, int telefono) {
        this.dni = dni;
        this.nombre = nombre;
        this.email = email;
        this.edad = edad;
        this.telefono = telefono;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Persona [dni=" + dni + ", nombre=" + nombre +
               ", email=" + email + ", edad=" + edad +
               ", telefono=" + telefono + "]";
    }
}
