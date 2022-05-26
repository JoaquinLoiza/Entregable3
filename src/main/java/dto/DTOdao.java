package dto;

import java.util.ArrayList;
import java.util.HashMap;

import dao.CarreraDao;
import dao.CarreraEstudianteDao;
import dao.EstudianteDao;
import entidades.Carrera;

public class DTOdao{
	
	private static DTOdao daodto;
	
	private ArrayList<Registro> registros = new ArrayList<>();
	
	private DTOdao(){}
	
	public static DTOdao getInstance() {
		if( daodto == null) {
			daodto = new DTOdao();
		}
		return daodto;
	}
	
	
	public void crearReporte() {
		ArrayList<Carrera> carreras = (ArrayList<Carrera>) CarreraDao.getInstance().findAll();
		for(Carrera c : carreras) {
			Registro r = new Registro();
			r.setC(c);
			r.setInscriptos(EstudianteDao.getInstance().getEstudiantesPorCarrera(c));
			HashMap<Integer, Integer> x = this.traerGraduadosPorAnio(c);
			r.setAnioGraduados(x);
			this.registros.add(r);
		}
	}

	private HashMap<Integer, Integer> traerGraduadosPorAnio(Carrera c) {
		ArrayList<Integer> anios = CarreraEstudianteDao.getInstance().traerAnios(c);
		HashMap<Integer, Integer> result = new HashMap<>();
		for(Integer i : anios) {
			result.put(i, CarreraEstudianteDao.getInstance().getCantidadGraduadosPorAnio(i,c).size());
		}		
		return result;
	}

	public ArrayList<Registro> getRegistros() {
		return registros;
	}

	public void setRegistros(ArrayList<Registro> registros) {
		this.registros = registros;
	}
}
