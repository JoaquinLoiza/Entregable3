package dto;

import java.util.ArrayList;
import java.util.HashMap;

import entidades.Carrera;
import entidades.Estudiante;

public class Registro {
	private Carrera c;
	private ArrayList<Estudiante> inscriptos;
	private HashMap<Integer, Integer> anioGraduados; 
	
	public Registro() {
		// TODO Auto-generated constructor stub
	}

	public Carrera getC() {
		return c;
	}

	public void setC(Carrera c) {
		this.c = c;
	}

	public ArrayList<Estudiante> getInscriptos() {
		return inscriptos;
	}

	public void setInscriptos(ArrayList<Estudiante> inscriptos) {
		this.inscriptos = inscriptos;
	}

	public HashMap<Integer, Integer> getAnioGraduados() {
		return anioGraduados;
	}

	public void setAnioGraduados(HashMap<Integer, Integer> anioGraduados) {
		this.anioGraduados = anioGraduados;
	}

	@Override
	public String toString() {
		return "Registro [c=" + c.getNombre() +  ". Los alumnos graduados por año son =" + anioGraduados + "]";
	}
}
