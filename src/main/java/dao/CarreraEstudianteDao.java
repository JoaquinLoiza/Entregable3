package dao;


import java.util.ArrayList;
import java.util.List;
import entidades.Carrera;
import entidades.CarreraEstudiante;
import entidades.Estudiante;
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
		EntityManager em = Emf.createEntityManager();
		em.getTransaction().begin();
		String jpql = "SELECT ce FROM CarreraEstudiante ce";
		Query query = em.createQuery(jpql);
		@SuppressWarnings("unchecked")
		List<CarreraEstudiante> r = query.getResultList(); 
		return r;
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

	public ArrayList<Integer> traerAnios(Carrera c) {
		EntityManager em = Emf.createEntityManager();
		em.getTransaction().begin();
		String jpql = "SELECT ce.anioGraduacion FROM CarreraEstudiante ce JOIN ce.estudiante e JOIN ce.carrera c WHERE c.idCarrera = ?1 ORDER BY ce.anioGraduacion ASC";
		Query query = em.createQuery(jpql);
		query.setParameter(1, c.getIdCarrera());
		@SuppressWarnings("unchecked")
		ArrayList<Integer> e = (ArrayList<Integer>) query.getResultList();
		return e;
	}

	public ArrayList<Estudiante> getCantidadGraduadosPorAnio(Integer i, Carrera c) {
		EntityManager em = Emf.createEntityManager();
		em.getTransaction().begin();
		String jpql= "SELECT e FROM CarreraEstudiante ce JOIN ce.carrera c JOIN ce.estudiante e WHERE c.idCarrera = ?1 AND ce.anioGraduacion = ?2 AND ce.graduado = true";
		Query query = em.createQuery(jpql);
		query.setParameter(1, c.getIdCarrera());
		query.setParameter(2, i);
		@SuppressWarnings("unchecked")
		ArrayList<Estudiante> e = (ArrayList<Estudiante>) query.getResultList();
		return e;
	}

}
