package ejercicios;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import objetos.Alumno;

public class Ej8Alumnos2DAM {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("UPPersonas");
        EntityManager em = emf.createEntityManager();

        try {
            // JPQL con parámetro
            String jpql = "SELECT a FROM Alumno a WHERE a.curso = :curso";

            TypedQuery<Alumno> query =
                    em.createQuery(jpql, Alumno.class);

            query.setParameter("curso", "2DAM");

            List<Alumno> alumnos = query.getResultList();

            System.out.println("Alumnos de 2DAM:");
            for (Alumno a : alumnos) {
                System.out.println(a.getNombre());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
