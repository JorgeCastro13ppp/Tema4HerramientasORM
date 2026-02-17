package ejfinalbiblioteca;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQuery(name = "Prestamo.contarTodos", query = "SELECT COUNT(p) FROM Prestamo p")
// COUNT devuelve Long, por eso luego se recoge como Long

@Entity
@Table(name = "prestamo")
public class Prestamo implements Serializable {

	private static final long serialVersionUID = -9121025272650956690L;

	// Clave primaria autogenerada (correcto no incluir en constructor)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPrestamo;

	private boolean prestado;

	// Lado MANY de la relación
	// Aquí está la clave foránea real en BD
	@ManyToOne
	@JoinColumn(name = "socioDni", nullable = false)
	// nullable = false recomendable porque un préstamo no existe sin socio
	private Socio socio;

	// Segunda relación MANY
	@ManyToOne
	@JoinColumn(name = "libroIsbn", nullable = false)
	private Libro libro;

	// Constructor vacío obligatorio para JPA
	public Prestamo() {
	}

	// Constructor correcto: no incluye id autogenerado
	// Sí incluye relaciones, lo cual es correcto conceptualmente
	public Prestamo(boolean prestado, Socio socio, Libro libro) {
		this.prestado = prestado;
		this.socio = socio;
		this.libro = libro;
	}

	// Getters y Setters

	public int getIdPrestamo() {
		return idPrestamo;
	}

	public void setIdPrestamo(int idPrestamo) {
		this.idPrestamo = idPrestamo;
	}

	public boolean isPrestado() {
		return prestado;
	}

	public void setPrestado(boolean prestado) {
		this.prestado = prestado;
	}

	public Socio getSocio() {
		return socio;
	}

	public void setSocio(Socio socio) {
		this.socio = socio;
	}

	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	// No incluimos socio ni libro en toString para evitar cascadas y bucles
	@Override
	public String toString() {
		return "Prestamo [idPrestamo=" + idPrestamo + ", prestado=" + prestado + "]";
	}
}
