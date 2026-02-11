package ejercicios;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import objetos.Nota;

/*
 * INSERT INTO nota (alumno, modulo, nota) VALUES
(1, 'PROG', 8.5),
(1, 'BBDD', 7.2),
(2, 'PROG', 6.0),
(2, 'ENT', 9.0),
(3, 'BBDD', 5.5);


*/

public class Ej13MostrarNotas {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("UPPersonas");
        EntityManager em = emf.createEntityManager();

        try {

            List<Nota> notas =
                em.createQuery("SELECT n FROM Nota n", Nota.class)
                  .getResultList();

            for (Nota n : notas) {
                System.out.println(
                    n.getAlumno().getNombre() + " - " +
                    n.getModulo().getNombre() + " - " +
                    n.getNota()
                );
            }

        } finally {
            em.close();
            emf.close();
        }
    }
}

