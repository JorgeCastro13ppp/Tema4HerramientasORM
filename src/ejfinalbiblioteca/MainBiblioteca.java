package ejfinalbiblioteca;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class MainBiblioteca {

	public static void main(String[] args) {

		EntityManagerFactory emf =
				Persistence.createEntityManagerFactory("UPBiblioteca");

		EntityManager em = emf.createEntityManager();
		Scanner sc = new Scanner(System.in);

		int opcion;

		do {

			System.out.println("\n--- MENU ---");
			System.out.println("1. Crear socio");
			System.out.println("2. Crear libro");
			System.out.println("3. Crear préstamo");
			System.out.println("4. Insertar datos de prueba");
			System.out.println("5. Actualizar teléfono");
			System.out.println("6. Consulta dinámica");
			System.out.println("7. Consulta estática");
			System.out.println("0. Salir");

			// Lectura segura del entero
			opcion = leerEnteroSeguro(sc);

			switch (opcion) {

				case 1:
					crearSocio(em, sc);
					break;

				case 2:
					crearLibro(em, sc);
					break;

				case 3:
					crearPrestamo(em, sc);
					break;

				case 4:
					insertarDatosPrueba(em);
					break;

				case 5:
					actualizarTelefono(em, sc);
					break;

				case 6:
					consultaDinamica(em, sc);
					break;

				case 7:
					consultaEstatica(em);
					break;

				case 0:
					System.out.println("Saliendo...");
					break;

				default:
					System.out.println("Opción no válida.");
			}

		} while (opcion != 0);

		em.close();
		emf.close();
		sc.close();
	}

	// MÉTODO AUXILIAR: lectura segura de enteros

	private static int leerEnteroSeguro(Scanner sc) {
		while (true) {
			try {
				return Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Debe introducir un número válido.");
			}
		}
	}

	// NORMALIZADORES

	private static String normalizarDni(String dni) {
		return dni.trim().toUpperCase();
	}

	private static String normalizarIsbn(String isbn) {
		return isbn.trim().toUpperCase();
	}

	// CONSULTA ESTÁTICA

	private static void consultaEstatica(EntityManager em) {

		Long total = em.createNamedQuery("Prestamo.contarTodos", Long.class)
				.getSingleResult();

		System.out.println("Total de préstamos: " + total);
	}

	// CONSULTA DINÁMICA

	private static void consultaDinamica(EntityManager em, Scanner sc) {

		System.out.println("Introduce ISBN del libro:");
		String isbn = normalizarIsbn(sc.nextLine());

		String jpql = "SELECT p.socio FROM Prestamo p WHERE UPPER(p.libro.isbn) = :isbn";

		List<Socio> socios = em.createQuery(jpql, Socio.class)
				.setParameter("isbn", isbn)
				.getResultList();

		if (socios.isEmpty()) {
			System.out.println("No hay préstamos para ese libro.");
		} else {
			System.out.println("Socios que han sacado el libro:");
			for (Socio s : socios) {
				System.out.println(s);
			}
		}
	}

	// CREAR PRÉSTAMO

	private static void crearPrestamo(EntityManager em, Scanner sc) {

		System.out.println("DNI del socio:");
		String dni = normalizarDni(sc.nextLine());

		Socio socio = em.find(Socio.class, dni);

		if (socio == null) {
			System.out.println("El socio no existe.");
			return;
		}

		System.out.println("ISBN del libro:");
		String isbn = normalizarIsbn(sc.nextLine());

		Libro libro = em.find(Libro.class, isbn);

		if (libro == null) {
			System.out.println("El libro no existe.");
			return;
		}

		Prestamo prestamo = new Prestamo(true, socio, libro);

		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			em.persist(prestamo);
			tx.commit();
			System.out.println("Préstamo creado correctamente.");
		} catch (Exception e) {
			tx.rollback();
			System.out.println("Error al crear el préstamo.");
		}
	}

	// CREAR LIBRO

	private static void crearLibro(EntityManager em, Scanner sc) {

		System.out.println("ISBN:");
		String isbn = normalizarIsbn(sc.nextLine());

		System.out.println("Título:");
		String titulo = sc.nextLine().trim();

		System.out.println("Autor:");
		String autor = sc.nextLine().trim();

		System.out.println("Número de ejemplares:");
		int numEjemplar = leerEnteroSeguro(sc);

		if (numEjemplar < 0) {
			System.out.println("El número de ejemplares no puede ser negativo.");
			return;
		}

		Libro libro = new Libro(isbn, numEjemplar, titulo, autor);

		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			em.persist(libro);
			tx.commit();
			System.out.println("Libro creado correctamente.");
		} catch (Exception e) {
			tx.rollback();
			System.out.println("Error al crear el libro.");
		}
	}

	// CREAR SOCIO

	private static void crearSocio(EntityManager em, Scanner sc) {

		System.out.println("DNI:");
		String dni = normalizarDni(sc.nextLine());

		System.out.println("Nombre:");
		String nombre = sc.nextLine().trim();

		System.out.println("Teléfono:");
		String telefono = sc.nextLine().trim();

		Socio socio = new Socio(dni, nombre, telefono);

		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			em.persist(socio);
			tx.commit();
			System.out.println("Socio creado correctamente.");
		} catch (Exception e) {
			tx.rollback();
			System.out.println("Error al crear el socio.");
		}
	}

	// DATOS DE PRUEBA

	public static void insertarDatosPrueba(EntityManager em) {

		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();

			Socio s1 = new Socio("111A", "Juan", "666111111");
			Socio s2 = new Socio("222B", "Ana", "666222222");
			Socio s3 = new Socio("333C", "Luis", "666333333");

			Libro l1 = new Libro("ISBN1", 5, "Hibernate", "Autor1");
			Libro l2 = new Libro("ISBN2", 3, "JPA", "Autor2");
			Libro l3 = new Libro("ISBN3", 7, "MySQL", "Autor3");

			em.persist(s1);
			em.persist(s2);
			em.persist(s3);

			em.persist(l1);
			em.persist(l2);
			em.persist(l3);

			em.persist(new Prestamo(true, s1, l1));
			em.persist(new Prestamo(true, s2, l2));
			em.persist(new Prestamo(true, s3, l3));

			tx.commit();
			System.out.println("Datos insertados correctamente.");

		} catch (Exception e) {
			tx.rollback();
			System.out.println("Error al insertar datos.");
		}
	}

	// ACTUALIZAR TELÉFONO

	public static void actualizarTelefono(EntityManager em, Scanner sc) {

		System.out.println("DNI del socio:");
		String dni = normalizarDni(sc.nextLine());

		Socio socio = em.find(Socio.class, dni);

		if (socio == null) {
			System.out.println("Socio no encontrado.");
			return;
		}

		System.out.println("Nuevo teléfono:");
		String nuevoTelefono = sc.nextLine().trim();

		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			socio.setNumTelefono(nuevoTelefono);
			tx.commit();
			System.out.println("Teléfono actualizado correctamente.");
		} catch (Exception e) {
			tx.rollback();
			System.out.println("Error al actualizar.");
		}
	}
}
