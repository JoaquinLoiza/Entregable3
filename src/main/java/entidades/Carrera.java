package entidades;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Carrera {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idCarrera;
	private String nombre;
	@OneToMany(mappedBy = "carrera", fetch= FetchType.EAGER)
	@JsonBackReference
    private List<CarreraEstudiante> carreraEstudiante;
	
	public Carrera() {}
	
	public Carrera( String nombre) {
		this.nombre = nombre;

	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getIdCarrera() {
		return idCarrera;
	}

	public void setIdCarrera(int idCarrera) {
		this.idCarrera = idCarrera;
	}

	public List<CarreraEstudiante> getCarreraEstudiante() {
		return carreraEstudiante;
	}

	public void setCarreraEstudiante(List<CarreraEstudiante> carreraEstudiante) {
		this.carreraEstudiante = carreraEstudiante;
	}

	@Override
	public String toString() {
		return "Carrera [idCarrera=" + idCarrera + ", nombre=" + nombre + ", carreraEstudiante=" + carreraEstudiante
				+ "]";
	}
}

