package ejercicios;

import java.util.Scanner;
//
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import objetos.Alumno;
import objetos.Nota;

public class Ej19GestionAlumno {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("UPPersonas");
        EntityManager em = emf.createEntityManager();
        Scanner sc = new Scanner(System.in);

        try {

            System.out.print("Introduce el ID del alumno: ");
            int id = sc.nextInt();
            sc.nextLine();

            Alumno alumno = em.find(Alumno.class, id);

            if (alumno == null) {
                System.out.println("Alumno no encontrado.");
                return;
            }

            int opcion;

            do {
                System.out.println("\n--- MENÚ ALUMNO ---");
                System.out.println("1. Mostrar alumno");
                System.out.println("2. Poner a 0 todas las notas");
                System.out.println("3. Borrar todas las notas");
                System.out.println("4. Salir");
                System.out.print("Opción: ");
                opcion = sc.nextInt();

                switch (opcion) {

                    case 1 -> mostrarAlumno(alumno);

                    case 2 -> ponerNotasACero(em, alumno);

                    case 3 -> borrarNotas(em, alumno);

                    case 4 -> System.out.println("Saliendo...");

                    default -> System.out.println("Opción no válida.");
                }

            } while (opcion != 4);

        } finally {
            em.close();
            emf.close();
            sc.close();
        }
    }

    // 1️⃣ Mostrar alumno y sus notas
    private static void mostrarAlumno(Alumno alumno) {

        System.out.println("\nAlumno: " + alumno.getNombre());

        if (alumno.getNotas().isEmpty()) {
            System.out.println("No tiene notas.");
        } else {
            for (Nota n : alumno.getNotas()) {
                System.out.println(
                        n.getModulo().getNombre() + " - " + n.getNota());
            }
        }
    }

    // 2️⃣ Poner todas las notas a 0
    private static void ponerNotasACero(EntityManager em, Alumno alumno) {

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        for (Nota n : alumno.getNotas()) {
            n.setNota(0);
        }

        tx.commit();

        System.out.println("Notas actualizadas a 0.");
    }

    // 3️⃣ Borrar todas las notas del alumno
    private static void borrarNotas(EntityManager em, Alumno alumno) {

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        alumno.getNotas().clear(); // gracias a orphanRemoval = true

        tx.commit();

        System.out.println("Notas eliminadas.");
    }
}

