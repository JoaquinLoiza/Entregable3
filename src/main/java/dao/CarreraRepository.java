package dao;

import java.util.List;

import entidades.Carrera;
import entitymanagerfactory.Emf;
import interfaces.DAO;
import jakarta.persistence.EntityManager;

public class CarreraRepository implements DAO<Carrera, Integer>{
	private static CarreraRepository daoCarrera;
	private EntityManager em;
	private CarreraRepository(){
		this.em=Emf.createEntityManager();
	}
	
	public static CarreraRepository getInstance() {
		if(daoCarrera == null) {
			daoCarrera = new CarreraRepository();
		}
		return daoCarrera;
	}
	
	@Override
	//Guardar una carrera
	public Carrera persist(Carrera entity) {
		this.em.getTransaction().begin();
		this.em.persist(entity);
		this.em.getTransaction().commit();
		this.em.close();
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
		this.em.getTransaction().begin();
		Carrera carrera= this.em.find(Carrera.class, id);
		this.em.close();
		return carrera;
	}

	@Override
	//Traer todas las carreras
	public List<Carrera> findAll() {
		this.em.getTransaction().begin();
		@SuppressWarnings("unchecked")
		List<Carrera> list = this.em.createQuery("SELECT c FROM Carrera c").getResultList();
		this.em.getTransaction().commit();
		return list;
	}

	@Override
	//Borrar una carrera
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

}
