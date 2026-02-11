package ejercicios;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import objetos.Imparte;
import objetos.Profesor;

/*INSERT INTO modulo (codigo, nombre) VALUES
('PROG', 'Programación'),
('BBDD', 'Bases de Datos'),
('ENT', 'Entornos de Desarrollo');

INSERT INTO imparte (profesor, modulo) VALUES
(1, 'PROG'),
(1, 'BBDD'),
(2, 'ENT');

 * */

public class Ej14ModulosPorProfesor {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("UPPersonas");
        EntityManager em = emf.createEntityManager();

        Scanner sc = new Scanner(System.in);

        try {

            System.out.print("Introduce el ID del profesor: ");
            int idProfesor = sc.nextInt();

            Profesor profesor = em.find(Profesor.class, idProfesor);

            if (profesor == null) {
                System.out.println("Profesor no encontrado.");
                return;
            }

            List<Imparte> impartes =
                em.createQuery(
                    "SELECT i FROM Imparte i WHERE i.profesor.id = :id",
                    Imparte.class)
                  .setParameter("id", idProfesor)
                  .getResultList();

            if (impartes.isEmpty()) {
                System.out.println("Este profesor no imparte módulos.");
            } else {
                System.out.println("Módulos impartidos por " +
                                   profesor.getNombre() + ":");

                for (Imparte i : impartes) {
                    System.out.println("- " +
                        i.getModulo().getNombre());
                }
            }

        } finally {
            em.close();
            emf.close();
            sc.close();
        }
    }
}

