package otros;

import java.util.ArrayList;
import java.util.HashMap;

import entidades.Carrera;
import entidades.Estudiante;

public class Registro {
	private Carrera c;
	private ArrayList<Estudiante> inscriptos;
	private ArrayList<String> anioGraduados; 
	
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

	public ArrayList<String> getAnioGraduados() {
		return anioGraduados;
	}

	public void setAnioGraduados(ArrayList<String> anioGraduados) {
		this.anioGraduados = anioGraduados;
	}

	@Override
	public String toString() {
		return "Carrera = " + c.getNombre() + ". Graduados = [Año = Cantidad] = [" +anioGraduados+"].";
	}
	
}
