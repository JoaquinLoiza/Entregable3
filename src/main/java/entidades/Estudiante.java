package entidades;

import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
    @JsonBackReference
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

	@Override
	public int hashCode() {
		return Objects.hash(anios, apellido, ciudad, dni, genero, nombre, nroLibreta);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Estudiante other = (Estudiante) obj;
		return anios == other.anios && Objects.equals(apellido, other.apellido) && Objects.equals(ciudad, other.ciudad)
				&& dni == other.dni && Objects.equals(genero, other.genero) && Objects.equals(nombre, other.nombre)
				&& nroLibreta == other.nroLibreta;
	}
	
	

}
