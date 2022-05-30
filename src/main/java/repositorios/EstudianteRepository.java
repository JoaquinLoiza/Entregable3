package repositorios;


import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Query;
import entidades.Carrera;
import entidades.Estudiante;
import entitymanagerfactory.Emf;
import interfaces.DAO;
import jakarta.persistence.EntityManager;

public class EstudianteRepository implements DAO<Estudiante, Integer>{
	private static EstudianteRepository daoEstudiante;
	private EntityManager em;
	private EstudianteRepository(){
		this.em= Emf.createEntityManager();
	}
	
	public static EstudianteRepository getInstance() {
		if(daoEstudiante == null) {
			daoEstudiante = new EstudianteRepository();
		}
		return daoEstudiante;
	}

	@Override
	public Estudiante persist(Estudiante entity) {
		this.em.getTransaction().begin();
		Estudiante estudiante=em.find(Estudiante.class, entity.getDni());
		if(estudiante==null) {
			this.em.persist(entity);
			this.em.getTransaction().commit();
			this.em.close();
			return entity;
		} else {
			this.em.close();
			return null;
		}
		
	}

	@Override
	public Estudiante findById(Integer id) {
		Estudiante estudiante=this.em.find(Estudiante.class, id);
		this.em.close();
		return estudiante;
	}
	
	public Estudiante findByNroLibreta(Integer nroLibreta) {
		this.em.getTransaction().begin();
		String jpql= "SELECT e FROM Estudiante e WHERE e.nroLibreta = ?1";
		Query query = this.em.createQuery(jpql);
		query.setParameter(1, nroLibreta);
		@SuppressWarnings("unchecked")
		List<Estudiante> e = query.getResultList();
		this.em.close();
		if(!e.isEmpty()) {
			Estudiante est = e.get(0);
			return est;
		} else return null;
	}
	
	public Estudiante findByGenero(String genero) {
		this.em.getTransaction().begin();
		String jpql= "SELECT e FROM Estudiante e WHERE e.genero = ?1";
		Query query = this.em.createQuery(jpql);
		query.setParameter(1, genero);
		@SuppressWarnings("unchecked")
		List<Estudiante> e = query.getResultList();
		this.em.close();
		if(!e.isEmpty()) {
			Estudiante est = e.get(0);
			return est;
		} else return null;
	}

	@Override
	public List<Estudiante> findAll() {
		this.em.getTransaction().begin();
		String jpql = "SELECT e FROM Estudiante e ORDER BY e.apellido ASC";
		Query query = this.em.createQuery(jpql);
		@SuppressWarnings("unchecked")
		List<Estudiante> resultados = query.getResultList();
		this.em.getTransaction().commit();
		return resultados;
	}

	public ArrayList<Estudiante> getEstudiantesPorCarrera(Carrera c) {
		this.em.getTransaction().begin();
		String jpql = "SELECT e FROM CarreraEstudiante ce JOIN ce.estudiante e JOIN ce.carrera c WHERE c.idCarrera = ?1 ORDER BY e.apellido ASC";
		Query query = this.em.createQuery(jpql);
		query.setParameter(1, c.getIdCarrera());
		@SuppressWarnings("unchecked")
		List<Estudiante> resultados = query.getResultList();
		this.em.getTransaction().commit();
		return (ArrayList<Estudiante>) resultados;
	}

	public ArrayList<Estudiante> findByCarreraByCuidad(int idc, String ciudad) {
		this.em.getTransaction().begin();
		@SuppressWarnings("unchecked")
		List<Estudiante> result = 
		this.em.createQuery("SELECT e FROM CarreraEstudiante ce JOIN ce.carrera c JOIN ce.estudiante e WHERE c.idCarrera = ?1 AND e.ciudad = ?2")
		.setParameter(1, idc)
		.setParameter(2, ciudad)
		.getResultList();
		this.em.close();
		return (ArrayList<Estudiante>) result;		
	}
}
