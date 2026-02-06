package ejercicios;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import objetos.Profesor;

public class Ej7InsertarProfesor {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("UPPersonas");
        EntityManager em = emf.createEntityManager();

        Scanner sc = new Scanner(System.in);

        try {
            // 1️⃣ Pedir datos
            System.out.print("Introduce el nombre del profesor: ");
            String nombre = sc.nextLine();

            // 2️⃣ Crear objeto (estado TRANSIENT)
            Profesor profesor = new Profesor(nombre);

            // 3️⃣ Iniciar transacción
            EntityTransaction tx = em.getTransaction();
            tx.begin();

            // 4️⃣ Persistir (pasa a MANAGED)
            em.persist(profesor);

            // 5️⃣ Confirmar cambios
            tx.commit();

            System.out.println("Profesor insertado correctamente.\n");

            // 6️⃣ Listar profesores
            List<Profesor> profesores =
                    em.createQuery("SELECT p FROM Profesor p", Profesor.class)
                      .getResultList();

            System.out.println("Listado de profesores:");
            for (Profesor p : profesores) {
                System.out.println(p);
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
