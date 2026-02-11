package ejercicios;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import objetos.Modulo;

public class Ej15ModificarModulo {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("UPPersonas");
        EntityManager em = emf.createEntityManager();

        Scanner sc = new Scanner(System.in);

        try {

            System.out.print("Introduce el código del módulo: ");
            String codigo = sc.nextLine();

            Modulo modulo = em.find(Modulo.class, codigo);

            if (modulo == null) {
                System.out.println("No existe ningún módulo con ese código.");
                return;
            }

            System.out.println("Nombre actual: " + modulo.getNombre());

            System.out.print("Nuevo nombre: ");
            String nuevoNombre = sc.nextLine();

            EntityTransaction tx = em.getTransaction();
            tx.begin();

            modulo.setNombre(nuevoNombre);

            tx.commit();

            System.out.println("Módulo actualizado correctamente.");

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
