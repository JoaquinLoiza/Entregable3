package repositorios;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import entidades.Carrera;
import entidades.CarreraEstudiante;
import entidades.Estudiante;
import entitymanagerfactory.Emf;
import interfaces.DAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import otros.Registro;

public class CarreraEstudianteRepository implements DAO<CarreraEstudiante, Integer> {
	private static CarreraEstudianteRepository daoCarreraEstudiante;
	private EntityManager em;
	private CarreraEstudianteRepository(){
		this.em=Emf.createEntityManager();
	}
	
	public static CarreraEstudianteRepository getInstance() {
		if( daoCarreraEstudiante == null) {
			 daoCarreraEstudiante = new CarreraEstudianteRepository();
		}
		return daoCarreraEstudiante;
	}
	@Override
	public CarreraEstudiante persist(CarreraEstudiante entity) {
		this.em.getTransaction().begin();
		this.em.persist(entity);
		this.em.getTransaction().commit();
		return entity;
	}

	@Override
	public List<CarreraEstudiante> findAll() {
		this.em.getTransaction().begin();
		String jpql = "SELECT ce FROM CarreraEstudiante ce";
		Query query = this.em.createQuery(jpql);
		@SuppressWarnings("unchecked")
		List<CarreraEstudiante> r = query.getResultList();
		this.em.getTransaction().commit();
		return r;
	}


	public List<Carrera> getCarrerasInscriptos() {
		this.em.getTransaction().begin();
		String jpql = "SELECT c FROM CarreraEstudiante ce JOIN ce.carrera c GROUP BY c.idCarrera ORDER BY COUNT(c.idCarrera) DESC";
		Query query = this.em.createQuery(jpql);
		@SuppressWarnings("unchecked")
		List<Carrera> r = query.getResultList(); 
		this.em.getTransaction().commit();
		return r;
	}

	public ArrayList<Integer> traerAnios(Carrera c) {
		this.em.getTransaction().begin();
		String jpql = "SELECT ce.anioGraduacion FROM CarreraEstudiante ce JOIN ce.estudiante e JOIN ce.carrera c WHERE c.idCarrera = ?1 ORDER BY ce.anioGraduacion ASC";
		Query query = this.em.createQuery(jpql);
		query.setParameter(1, c.getIdCarrera());
		@SuppressWarnings("unchecked")
		ArrayList<Integer> e = (ArrayList<Integer>) query.getResultList();
		this.em.getTransaction().commit();
		return e;
	}

	public ArrayList<Estudiante> getCantidadGraduadosPorAnio(Integer i, Carrera c) {
		this.em.getTransaction().begin();
		String jpql= "SELECT e FROM CarreraEstudiante ce JOIN ce.carrera c JOIN ce.estudiante e WHERE c.idCarrera = ?1 AND ce.anioGraduacion = ?2 AND ce.graduado = true";
		Query query = this.em.createQuery(jpql);
		query.setParameter(1, c.getIdCarrera());
		query.setParameter(2, i);
		@SuppressWarnings("unchecked")
		ArrayList<Estudiante> e = (ArrayList<Estudiante>) query.getResultList();
		this.em.getTransaction().commit();
		return e;
	}
	
	//Reporte
	
	public ArrayList<Registro> crearReporte() {
		ArrayList<Registro> registros = new ArrayList<Registro>();
		ArrayList<Carrera> carreras = (ArrayList<Carrera>) CarreraRepository.getInstance().findAll();
		for(Carrera c : carreras) {
			Registro r = new Registro();
			r.setC(c);
			r.setInscriptos(EstudianteRepository.getInstance().getEstudiantesPorCarrera(c));
			HashMap<Integer, Integer> x = this.traerGraduadosPorAnio(c);
			r.setAnioGraduados(x);
			registros.add(r);
		}
		return registros;
	}

	private HashMap<Integer, Integer> traerGraduadosPorAnio(Carrera c) {
		ArrayList<Integer> anios = CarreraEstudianteRepository.getInstance().traerAnios(c);
		HashMap<Integer, Integer> result = new HashMap<>();
		for(Integer i : anios) {
			result.put(i, CarreraEstudianteRepository.getInstance().getCantidadGraduadosPorAnio(i,c).size());
		}		
		return result;
	}

	@Override
	public CarreraEstudiante findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
