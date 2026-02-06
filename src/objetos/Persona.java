package objetos;

public class Persona {

	private String dni;
	private String nombre;
	private String email;
	private int edad;
	private int telefono;
	
	public Persona() {
		
	}

	public Persona(String dni, String nombre, String email,int edad,int telefono) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.email = email;
		this.telefono=telefono;
		this.edad=edad;
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
		return "Persona [dni=" + dni + ", nombre=" + nombre + ", email=" + email + ", edad=" + edad + ", telefono="
				+ telefono + "]";
	}
	
	
	
}
