package ejercicios;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import objetos.Profesor;

public class Ej16ModificarProfesor {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("UPPersonas");
        EntityManager em = emf.createEntityManager();

        Scanner sc = new Scanner(System.in);

        try {

            System.out.print("Introduce el ID del profesor: ");
            int id = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            Profesor profesor = em.find(Profesor.class, id);

            if (profesor == null) {
                System.out.println("No existe ningún profesor con ese ID.");
                return;
            }

            System.out.println("Nombre actual: " + profesor.getNombre());

            System.out.print("Nuevo nombre: ");
            String nuevoNombre = sc.nextLine();

            EntityTransaction tx = em.getTransaction();
            tx.begin();

            profesor.setNombre(nuevoNombre);

            tx.commit();

            System.out.println("Profesor actualizado correctamente.");

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

