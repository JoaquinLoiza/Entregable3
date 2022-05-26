package dao;


import java.util.List;

import entidades.Carrera;
import entidades.CarreraEstudiante;
import entitymanagerfactory.Emf;
import interfaces.DAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class CarreraEstudianteDao implements DAO<CarreraEstudiante, Integer> {
	private static CarreraEstudianteDao daoCarreraEstudiante;
	
	private CarreraEstudianteDao(){}
	
	public static CarreraEstudianteDao getInstance() {
		if( daoCarreraEstudiante == null) {
			 daoCarreraEstudiante = new CarreraEstudianteDao();
		}
		return daoCarreraEstudiante;
	}
	@Override
	public CarreraEstudiante persist(CarreraEstudiante entity) {
		EntityManager em=Emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(entity);
		em.getTransaction().commit();
		em.close();
		return entity;
	}

	@Override
	public CarreraEstudiante update(Integer id, CarreraEstudiante newEntityValues) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CarreraEstudiante findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CarreraEstudiante> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Carrera> getCarrerasInscriptos() {
		EntityManager em = Emf.createEntityManager();
		em.getTransaction().begin();
		String jpql = "SELECT c FROM CarreraEstudiante ce JOIN ce.carrera c GROUP BY c.idCarrera ORDER BY COUNT(c.idCarrera) DESC";
		Query query = em.createQuery(jpql);
		@SuppressWarnings("unchecked")
		List<Carrera> r = query.getResultList(); 
		return r;
	}

}
