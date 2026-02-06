package ejercicios;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import objetos.Profesor;

public class Ej10BorrarProfesor {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("UPPersonas");
        EntityManager em = emf.createEntityManager();
        Scanner sc = new Scanner(System.in);

        try {
            // 1️⃣ Mostrar profesores actuales
            List<Profesor> profesores =
                    em.createQuery("SELECT p FROM Profesor p", Profesor.class)
                      .getResultList();

            System.out.println("Profesores existentes:");
            for (Profesor p : profesores) {
                System.out.println(p.getId() + " - " + p.getNombre());
            }

            // 2️⃣ Pedir id
            System.out.print("\nIntroduce el id del profesor a borrar: ");
            int id = sc.nextInt();

            // 3️⃣ Buscar profesor
            Profesor profesor = em.find(Profesor.class, id);

            if (profesor == null) {
                System.out.println("No existe ningún profesor con ese id.");
            } else {
                // 4️⃣ Borrar con transacción
                EntityTransaction tx = em.getTransaction();
                tx.begin();

                em.remove(profesor);

                tx.commit();

                System.out.println("Profesor borrado correctamente.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            sc.close();
            em.close();
            emf.close();
        }
    }
}
