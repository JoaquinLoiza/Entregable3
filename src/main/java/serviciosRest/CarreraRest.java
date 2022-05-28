package serviciosRest;

import java.util.List;

import entidades.Carrera;
import jakarta.servlet.http.HttpServlet;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import repositorios.CarreraRepository;

@Path("/carreras")
public class CarreraRest extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Carrera> getCarrera(){
		return CarreraRepository.getInstance().findAll();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Carrera getCarreraById(@PathParam("id") String msg) {
		int id = Integer.valueOf(msg);
		Carrera carrera = CarreraRepository.getInstance().findById(id);
		if(carrera != null) {
			return carrera;			
		}
		else {
			throw new RecursoNoExiste(id);
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createCarrera(Carrera c) {
		Carrera result= CarreraRepository.getInstance().persist(c);
		if(result==null) {
			throw new RecursoDuplicado(c.getIdCarrera());
		}else {
			return Response.status(201).entity(c).build();
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
}
