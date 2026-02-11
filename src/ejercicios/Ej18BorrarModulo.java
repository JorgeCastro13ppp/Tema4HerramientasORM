package ejercicios;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import objetos.Modulo;

public class Ej18BorrarModulo {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("UPPersonas");
        EntityManager em = emf.createEntityManager();
        Scanner sc = new Scanner(System.in);

        try {

            System.out.print("Introduce el código del módulo a borrar: ");
            String codigo = sc.nextLine();

            Modulo modulo = em.find(Modulo.class, codigo);

            if (modulo == null) {
                System.out.println("No existe ningún módulo con ese código.");
                return;
            }

            EntityTransaction tx = em.getTransaction();
            tx.begin();

            // Gracias al cascade, esto borra:
            // - notas asociadas
            // - imparticiones
            // - y luego el módulo
            em.remove(modulo);

            tx.commit();

            System.out.println("Módulo eliminado correctamente.");

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


