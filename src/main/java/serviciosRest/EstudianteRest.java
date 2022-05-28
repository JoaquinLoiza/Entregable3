package serviciosRest;

import java.util.ArrayList;
import java.util.List;

import entidades.Estudiante;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import repositorios.EstudianteRepository;

@Path("/estudiantes")
public class EstudianteRest {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Estudiante> getEstudiante(){
		return EstudianteRepository.getInstance().findAll();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Estudiante getEstudianteById(@PathParam("id") String msg) {
		int id = Integer.valueOf(msg);
		Estudiante e = EstudianteRepository.getInstance().findById(id);
		if(e != null) {
			return e;			
		}
		else {
			throw new RecursoNoExiste(id);
		}
	}
	
	@GET
	@Path("/nroLibreta/{nroLibreta}")
	@Produces(MediaType.APPLICATION_JSON)
	public Estudiante getEstudianteByNroLibreta(@PathParam("nroLibreta") String msg) {
		int nroLibreta = Integer.valueOf(msg);
		Estudiante e = EstudianteRepository.getInstance().findByNroLibreta(nroLibreta);
		if(e != null) {
			return e;			
		}
		else {
			throw new RecursoNoExisteLibreta(nroLibreta);
		}
	}
	@GET
	@Path("/genero/{genero}")
	@Produces(MediaType.APPLICATION_JSON)
	public Estudiante getEstudianteByGenero(@PathParam("genero") String msg) {
		Estudiante e = EstudianteRepository.getInstance().findByGenero(msg);
		if(e != null) {
			return e;			
		}
		else {
			throw new RecursoNoExisteGenero(msg);
		}
	}
	
	@GET
	@Path("/{idcarrera}/{ciudad}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Estudiante> getEstudiantesPorCiudadYCarrera(@PathParam("idcarrera") String carrera, @PathParam("ciudad") String ciudad) {
		int idc = Integer.valueOf(carrera);
		ArrayList<Estudiante> e = EstudianteRepository.getInstance().findByCarreraByCuidad(idc, ciudad);
		if(e != null) {
			return e;			
		}
		else {
			throw new RecursoNoExisteLibreta(idc);
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createEstudiante(Estudiante e) {
		Estudiante result=EstudianteRepository.getInstance().persist(e);
	
		if(result==null) {
			throw new RecursoDuplicado(e.getDni());
		}else {
			return Response.status(201).entity(e).build();
		}
	}
	
	public class RecursoDuplicado extends WebApplicationException {

		private static final long serialVersionUID = 1L;

		public RecursoDuplicado(int i) {
	         super(Response.status(Response.Status.CONFLICT)
	             .entity("El recurso con ID "+i+" ya existe").type(MediaType.TEXT_PLAIN).build());
	     }
	}
	
	public class RecursoNoExiste extends WebApplicationException {

		private static final long serialVersionUID = 1L;

		public RecursoNoExiste(int id) {
	         super(Response.status(Response.Status.NOT_FOUND)
	             .entity("El recurso con id "+id+" no fue encontrado").type(MediaType.TEXT_PLAIN).build());
	     }
	}
	public class RecursoNoExisteLibreta extends WebApplicationException {

		private static final long serialVersionUID = 1L;

		public RecursoNoExisteLibreta(int nroLibreta) {
	         super(Response.status(Response.Status.NOT_FOUND)
	             .entity("El estudiante con nro de Libreta "+nroLibreta+" no existe").type(MediaType.TEXT_PLAIN).build());
	     }
	}
	public class RecursoNoExisteGenero extends WebApplicationException {

		private static final long serialVersionUID = 1L;

		public RecursoNoExisteGenero(String genero) {
	         super(Response.status(Response.Status.NOT_FOUND)
	             .entity("No existen estudiantes de genero  "+genero).type(MediaType.TEXT_PLAIN).build());
	     }
	}
}
