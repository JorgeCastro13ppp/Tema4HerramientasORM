package aemet;

import java.io.File;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MainAemet {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EntityManagerFactory emf =
		        Persistence.createEntityManagerFactory("UPclima");

		EntityManager em = emf.createEntityManager();

		try {
		    List<ClimaMensual> datos = leerJson("datos.json");

		    Long count = em.createQuery(
		            "SELECT COUNT(c) FROM ClimaMensual c",
		            Long.class
		    ).getSingleResult();

		    if (count == 0) {
		        guardarDatos(em, datos);
		    }

		    ClimaMensual r = em.createQuery(
		            "SELECT c FROM ClimaMensual c WHERE c.fecha <> '2004-13' ORDER BY c.p_mes DESC",
		            ClimaMensual.class
		    )
		    .setMaxResults(1)
		    .getSingleResult();

		    System.out.println("Mes más lluvioso: " + r.getFecha());

		}catch(Exception e){
			e.printStackTrace();
		}	finally {
		    em.close();
		    emf.close();
		}
			

	}
	
	private static List<ClimaMensual> leerJson(String ruta) throws Exception {
	    ObjectMapper mapper = new ObjectMapper();
	    return mapper.readValue(
	            new File(ruta),
	            new TypeReference<List<ClimaMensual>>() {}
	    );
	}
	
	private static void guardarDatos(EntityManager em, List<ClimaMensual> datos) {

	    try {
	        em.getTransaction().begin();

	        for (ClimaMensual c : datos) {
	            em.persist(c);
	        }

	        em.getTransaction().commit();

	    } catch (Exception e) {

	        if (em.getTransaction().isActive()) {
	            em.getTransaction().rollback();
	        }

	        throw e; // Muy importante: relanza la excepción
	    }
	}


	


}
