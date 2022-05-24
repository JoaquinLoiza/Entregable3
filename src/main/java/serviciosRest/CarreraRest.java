package serviciosRest;

import java.util.List;

import dao.CarreraDao;
import entidades.Carrera;
import jakarta.servlet.http.HttpServlet;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/carreras")
public class CarreraRest extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Carrera> getCarrera(){
		return CarreraDao.getInstance().findAll();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Carrera getCarreraById(@PathParam("id") String msg) {
		int id = Integer.valueOf(msg);
		Carrera carrera = CarreraDao.getInstance().findById(id);
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
		Carrera result= CarreraDao.getInstance().persist(c);
		
		if(result==null) {
			throw new RecursoDuplicado(c. getIdCarrera());
		}else {
			return Response.status(201).entity(c).build();
		}
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteCarrera(@PathParam("id") int id) {
		/*Perro p = PerroDaoImplem.getInstance().findById(id);
		if(p != null) {
			PerroDaoImplem.getInstance().delete(id);
			return Response.ok().build();
			}
		else {
			return Response.status(Response.Status.NOT_FOUND).build();
			
		}*/
		return null;
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateCarrera(@PathParam("id") int id, Carrera c) {
		/*Perro result= PerroDaoImplem.getInstance().findById(perro.getId());
		if(result!=null) {
			PerroDaoImplem.getInstance().update(id, perro);
			return Response.status(200).entity(perro).build();
		}else {
			throw new RecursoDuplicado(perro.getId());
		}*/
		return null;
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
