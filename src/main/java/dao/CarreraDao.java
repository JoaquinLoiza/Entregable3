package dao;

import java.util.List;

import entidades.Carrera;
import entitymanagerfactory.Emf;
import interfaces.DAO;
import jakarta.persistence.EntityManager;

public class CarreraDao implements DAO<Carrera, Integer>{
	private static CarreraDao daoCarrera;
	
	public CarreraDao(){}
	
	public static CarreraDao getInstance() {
		if(daoCarrera == null) {
			daoCarrera = new CarreraDao();
		}
		return daoCarrera;
	}
	
	@Override
	//Guardar una carrera
	public Carrera persist(Carrera entity) {
		EntityManager em=Emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(entity);
		em.getTransaction().commit();
		em.close();
		return entity;
	}

	@Override
	//Editar una carrera
	public Carrera update(Integer id, Carrera carrea) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	//Buscar carrera por id
	public Carrera findById(Integer id) {
		EntityManager entityManager=Emf.createEntityManager();
		Carrera carrera=entityManager.find(Carrera.class, id);
		entityManager.close();
		return carrera;
	}

	@Override
	//Traer todas las carreras
	public List<Carrera> findAll() {
		EntityManager e = Emf.createEntityManager();
		@SuppressWarnings("unchecked")
		List<Carrera> list = e.createQuery("SELECT c FROM Carrera c").getResultList();
		return list;
	}

	@Override
	//Borrar una carrera
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean encontro(Integer id) {
		boolean result=false;
		EntityManager em=Emf.createEntityManager();
		em.getTransaction().begin();
		Carrera c=em.find(Carrera.class, id);
		if (c!=null) {
			result=true;
		}
		return result;
	}

}
