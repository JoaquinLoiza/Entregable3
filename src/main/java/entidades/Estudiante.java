package entidades;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Estudiante {
	@Id
	private int dni;
	private String nombre;
	private String apellido;
	private int anios;
	private String genero;
	private String ciudad;
	private int nroLibreta;
	@OneToMany(mappedBy = "estudiante") // Referencia al atributo de la entidad CarreraEstudiante
    private Set<CarreraEstudiante> carreraEstudiante;
	
	public Estudiante() {
	}
	
	public Estudiante(int dni, String nombre, String apellido, int anios, String genero, String ciudad,
			int nroLibreta) {
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.anios = anios;
		this.genero = genero;
		this.ciudad = ciudad;
		this.nroLibreta = nroLibreta;
	}

	public Estudiante(int b) {
		this.dni = b;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getAnios() {
		return anios;
	}

	public void setAnios(int anios) {
		this.anios = anios;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getCuidad() {
		return ciudad;
	}

	public void setCuidad(String ciudad) {
		this.ciudad = ciudad;
	}

	public int getNroLibreta() {
		return nroLibreta;
	}

	public void setNroLibreta(int nroLibreta) {
		this.nroLibreta = nroLibreta;
	}

	@Override
	public String toString() {
		return "Estudiante [dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + ", anios=" + anios
				+ ", genero=" + genero + ", cuidad=" + ciudad + ", nroLibreta=" + nroLibreta + ", carreraEstudiante="
				+ carreraEstudiante + "]";
	}

}
