package ejfinalbiblioteca;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity // Indica que esta clase es una entidad JPA
@Table(name = "libro") // Mapea con la tabla "libro" en la BD
public class Libro implements Serializable {

	private static final long serialVersionUID = 8181515319452397463L;

	// ISBN es clave primaria natural (correctamente String)
	@Id
	private String isbn;

	// Cantidad numérica -> correcto como int
	private int numEjemplar;

	private String titulo;
	private String autor;

	// Relación 1:N con Prestamo
	// mappedBy = "libro" -> debe coincidir EXACTAMENTE con el atributo en Prestamo
	// cascade = ALL -> si borras libro se borran sus préstamos (correcto para este
	// ejercicio)
	@OneToMany(mappedBy = "libro", cascade = CascadeType.ALL)
	private List<Prestamo> prestamos = new ArrayList<>();
	// Mejor inicializar la lista para evitar NullPointerException

	// Constructor vacío obligatorio para JPA
	public Libro() {
	}

	// Constructor recomendado (sin incluir lista de préstamos, correcto)
	public Libro(String isbn, int numEjemplar, String titulo, String autor) {
		this.isbn = isbn;
		this.numEjemplar = numEjemplar;
		this.titulo = titulo;
		this.autor = autor;
	}

	// Getters y Setters

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public int getNumEjemplar() {
		return numEjemplar;
	}

	public void setNumEjemplar(int numEjemplar) {
		this.numEjemplar = numEjemplar;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public List<Prestamo> getPrestamos() {
		return prestamos;
	}

	public void setPrestamos(List<Prestamo> prestamos) {
		this.prestamos = prestamos;
	}

	// No incluimos la lista de préstamos para evitar bucles infinitos
	@Override
	public String toString() {
		return "Libro [isbn=" + isbn + ", numEjemplar=" + numEjemplar + ", titulo=" + titulo + ", autor=" + autor + "]";
	}
}
