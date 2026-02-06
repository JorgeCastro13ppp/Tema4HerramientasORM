package ejercicios;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import objetos.Alumno;

public class Ej9AlumnosOrdenadosPorCurso {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("UPPersonas");
        EntityManager em = emf.createEntityManager();

        try {
            // JPQL con ORDER BY
            String jpql = "SELECT a FROM Alumno a ORDER BY a.curso";

            TypedQuery<Alumno> query =
                    em.createQuery(jpql, Alumno.class);

            List<Alumno> alumnos = query.getResultList();

            System.out.println("Alumnos ordenados por curso:");
            for (Alumno a : alumnos) {
                System.out.println(a.getNombre() + " - " + a.getCurso());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
