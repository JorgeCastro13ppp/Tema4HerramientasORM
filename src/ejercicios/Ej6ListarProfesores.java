package ejercicios;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import objetos.Profesor;

/*INSERT INTO profesor (nombre) VALUES
('Carlos López'),
('Ana Martínez');
*/

public class Ej6ListarProfesores {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//1.Crear la factoría
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UPPersonas");
		
		//2. Crear el EntityManager
		EntityManager em = emf.createEntityManager();
		
		try {
			//Consulta JPQL (usamos la clase no la tabla)
			List<Profesor> profesores = em.createQuery("SELECT p FROM Profesor p", Profesor.class).getResultList();
			
			//4. Mostrar resultados
			System.out.println("Listado de profesores: ");
			for(Profesor p : profesores) {
				System.out.println(p);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			em.close();
			emf.close();
		}
	}

}
