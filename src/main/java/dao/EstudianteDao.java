package dao;


import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Query;
import entidades.Carrera;
import entidades.Estudiante;
import entitymanagerfactory.Emf;
import interfaces.DAO;
import jakarta.persistence.EntityManager;

public class EstudianteDao implements DAO<Estudiante, Integer>{
	private static EstudianteDao daoEstudiante;
	
	private EstudianteDao(){}
	
	public static EstudianteDao getInstance() {
		if(daoEstudiante == null) {
			daoEstudiante = new EstudianteDao();
		}
		return daoEstudiante;
	}

	@Override
	public Estudiante persist(Estudiante entity) {
		EntityManager em=Emf.createEntityManager();
		em.getTransaction().begin();
		Estudiante estudiante=em.find(Estudiante.class, entity.getDni());
		if(estudiante==null) {
			em.persist(entity);
			em.getTransaction().commit();
			em.close();
			return entity;
		} else return null;
	}

	@Override
	public Estudiante update(Integer id, Estudiante newEntityValues) {
		return null;
	}

	@Override
	public Estudiante findById(Integer id) {
		EntityManager entityManager=Emf.createEntityManager();
		Estudiante estudiante=entityManager.find(Estudiante.class, id);
		entityManager.close();
		return estudiante;
	}
	
	public Estudiante findByNroLibreta(Integer nroLibreta) {
		EntityManager entityManager= Emf.createEntityManager();
		entityManager.getTransaction().begin();
		String jpql= "SELECT e FROM Estudiante e WHERE e.nroLibreta = ?1";
		Query query =entityManager.createQuery(jpql);
		query.setParameter(1, nroLibreta);
		@SuppressWarnings("unchecked")
		List<Estudiante> e = query.getResultList();
		if(!e.isEmpty()) {
			Estudiante est = e.get(0);
			return est;
		} else return null;
	}
	
	public Estudiante findByGenero(String genero) {
		EntityManager entityManager= Emf.createEntityManager();
		entityManager.getTransaction().begin();
		String jpql= "SELECT e FROM Estudiante e WHERE e.genero = ?1";
		Query query =entityManager.createQuery(jpql);
		query.setParameter(1, genero);
		@SuppressWarnings("unchecked")
		List<Estudiante> e = query.getResultList();
		if(!e.isEmpty()) {
			Estudiante est = e.get(0);
			return est;
		} else return null;
	}

	@Override
	public List<Estudiante> findAll() {
		EntityManager e = Emf.createEntityManager();
		e.getTransaction().begin();
		String jpql = "SELECT e FROM Estudiante e ORDER BY e.apellido ASC";
		Query query = e.createQuery(jpql);
		@SuppressWarnings("unchecked")
		List<Estudiante> resultados = query.getResultList();
		e.close();
		return resultados;
	}

	@Override
	public boolean delete(Integer id) {
		return false;
	}

	public ArrayList<Estudiante> getEstudiantesPorCarrera(Carrera c) {
		EntityManager em = Emf.createEntityManager();
		em.getTransaction().begin();
		String jpql = "SELECT e FROM CarreraEstudiante ce JOIN ce.estudiante e JOIN ce.carrera c WHERE c.idCarrera = ?1 ORDER BY e.apellido ASC";
		Query query = em.createQuery(jpql);
		query.setParameter(1, c.getIdCarrera());
		@SuppressWarnings("unchecked")
		List<Estudiante> resultados = query.getResultList();
		return (ArrayList<Estudiante>) resultados;
	}
}
