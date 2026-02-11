package ejercicios;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import objetos.Alumno;

public class Ej17BorrarAlumno {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("UPPersonas");
        EntityManager em = emf.createEntityManager();

        Scanner sc = new Scanner(System.in);

        try {

            // 1️⃣ Mostrar alumnos actuales
            System.out.println("Listado de alumnos:");
            List<Alumno> alumnos =
                em.createQuery("SELECT a FROM Alumno a", Alumno.class)
                  .getResultList();

            if (alumnos.isEmpty()) {
                System.out.println("No hay alumnos registrados.");
                return;
            }

            for (Alumno a : alumnos) {
                System.out.println(a.getId() + " - " + a.getNombre());
            }

            // 2️⃣ Pedir ID
            System.out.print("\nIntroduce el ID del alumno a borrar: ");
            int id = sc.nextInt();

            // 3️⃣ Buscar alumno
            Alumno alumno = em.find(Alumno.class, id);

            if (alumno == null) {
                System.out.println("No existe ningún alumno con ese ID.");
                return;
            }

            // 4️⃣ Borrar en transacción
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.remove(alumno);
            tx.commit();

            System.out.println("Alumno eliminado correctamente.\n");

            // 5️⃣ Mostrar listado actualizado
            List<Alumno> alumnosActualizados =
                em.createQuery("SELECT a FROM Alumno a", Alumno.class)
                  .getResultList();

            System.out.println("Listado actualizado:");
            for (Alumno a : alumnosActualizados) {
                System.out.println(a.getId() + " - " + a.getNombre());
            }

        } catch (Exception e) {

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();

        } finally {
            em.close();
            emf.close();
            sc.close();
        }
    }
}
