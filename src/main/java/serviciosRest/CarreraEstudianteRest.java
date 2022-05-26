package serviciosRest;


import dao.CarreraEstudianteDao;
import dao.EstudianteDao;

import java.util.List;

import dao.CarreraDao;
import entidades.Carrera;
import entidades.CarreraEstudiante;

import jakarta.servlet.http.HttpServlet;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/carreraEstudiante")
public class CarreraEstudianteRest extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createCarreraEstudiante(CarreraEstudiante c) {
		if(CarreraDao.getInstance().findAll().contains(c.getCarrera()) && EstudianteDao.getInstance().findAll().contains(c.getEstudiante())){
			CarreraEstudiante result = CarreraEstudianteDao.getInstance().persist(c);
			if(result != null) {
				return Response.status(201).entity(c).build();				
			}
			else {
				return Response.status(Response.Status.CONFLICT).entity("devuelve vacio").type(MediaType.TEXT_PLAIN).build();
			}
		}
		else {
			return Response.status(Response.Status.CONFLICT).entity("la carrera con el id  " + c.getCarrera().getIdCarrera()
			+ "o el estudiante con el  " + c.getEstudiante().getDni() + "no existen")
			.type(MediaType.TEXT_PLAIN).build();
			}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Carrera> getCarrerasInscriptos() {
		return CarreraEstudianteDao.getInstance().getCarrerasInscriptos();
	}
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CarreraEstudiante> getAll() {
		return CarreraEstudianteDao.getInstance().findAll();
	}
	
	public class RecursoDuplicado extends WebApplicationException {

		private static final long serialVersionUID = 1L;

		public RecursoDuplicado(int i) {
	         super(Response.status(Response.Status.CONFLICT)
	             .entity("El recurso con ID "+i+" ya existe").type(MediaType.TEXT_PLAIN).build());
	     }
		
		public RecursoDuplicado(int idC, int idE) {
			super(Response.status(Response.Status.CONFLICT)
		             .entity("El recurso con ID "+idC+"y "+idE+"ya existen").type(MediaType.TEXT_PLAIN).build());
		}
	}
	
	public class RecursoNoExiste extends WebApplicationException {

		private static final long serialVersionUID = 1L;

		public RecursoNoExiste(int id) {
	         super(Response.status(Response.Status.NOT_FOUND)
	             .entity("El recurso con id "+id+" no fue encontrado").type(MediaType.TEXT_PLAIN).build());
	     }
	}
}
