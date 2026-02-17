package ejfinalbiblioteca;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity // Marca la clase como entidad JPA
@Table(name = "socio") // Mapea con la tabla "socio"
public class Socio implements Serializable {

	private static final long serialVersionUID = 3441184393916441612L;

	// Clave primaria natural
	@Id
	private String dni;

	private String nombre;

	// Correcto como String (no int)
	private String numTelefono;

	// Relación 1:N con Prestamo
	// mappedBy debe coincidir exactamente con el atributo en Prestamo
	// CascadeType.ALL: si se elimina el socio, se eliminan sus préstamos
	@OneToMany(mappedBy = "socio", cascade = CascadeType.ALL)
	private List<Prestamo> prestamos = new ArrayList<>();
	// Recomendable inicializar para evitar NullPointerException

	// Constructor vacío obligatorio para JPA
	public Socio() {
	}

	// Constructor sin incluir la colección (correcto diseño)
	public Socio(String dni, String nombre, String numTelefono) {
		this.dni = dni;
		this.nombre = nombre;
		this.numTelefono = numTelefono;
	}

	// Getters y Setters

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

	public String getNumTelefono() {
		return numTelefono;
	}

	public void setNumTelefono(String numTelefono) {
		this.numTelefono = numTelefono;
	}

	public List<Prestamo> getPrestamos() {
		return prestamos;
	}

	public void setPrestamos(List<Prestamo> prestamos) {
		this.prestamos = prestamos;
	}

	// No incluimos la lista de préstamos para evitar bucles en relaciones
	// bidireccionales
	@Override
	public String toString() {
		return "Socio [dni=" + dni + ", nombre=" + nombre + ", numTelefono=" + numTelefono + "]";
	}
}
