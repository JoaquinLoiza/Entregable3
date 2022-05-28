package entidades;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class CarreraEstudiante implements Serializable{
	
	@Id 
	@ManyToOne 
	@JoinColumn (name="dni")
	private Estudiante estudiante; 

    @Id 
    @ManyToOne
    @JoinColumn(name="idCarrera")
    private Carrera carrera;

    private boolean graduado;
    private int aniosAntiguedad;
    private int anioGraduacion;
    
    public CarreraEstudiante() {}

	public CarreraEstudiante(Estudiante estudiante, Carrera carrera, boolean graduado,
			int aniosAntiguedad, int anioGraduacion) {
		super();
		this.estudiante = estudiante;
		this.carrera = carrera;
		this.graduado = graduado;
		this.aniosAntiguedad = aniosAntiguedad;
		this.anioGraduacion = anioGraduacion;
	}
	
	public CarreraEstudiante(Carrera c, Estudiante e) {
		this.estudiante = e;
		this.carrera = c;
	}
	
	public boolean isGraduado() {
		return this.graduado;
	}

	public int getAniosAntiguedad() {
		return aniosAntiguedad;
	}
	
	public void setAniosAntiguedad(int aniosAntiguedad) {
		this.aniosAntiguedad = aniosAntiguedad;
	}
	
	public int getAnioGraduacion() {
		return anioGraduacion;
	}
	
	public void setAnioGraduacion(int anioGraduacion) {
		this.anioGraduacion = anioGraduacion;
	}
	
	public Estudiante getEstudiante() {
		return estudiante;
	}
	
	public Carrera getCarrera() {
		return carrera;
	}

	@Override
	public int hashCode() {
		return Objects.hash(anioGraduacion, aniosAntiguedad, carrera, estudiante, graduado);
	}

	@Override
	public String toString() {
		return "[Estudiante = " + estudiante.getNombre() + ", Carrera =" + carrera.getNombre() + ". Graduado = " 
				+ graduado + ", A�os de antiguedad =" + aniosAntiguedad + ", A�o de graduacion = " + anioGraduacion +"]";
	}
	
	
	
	

	
	
}