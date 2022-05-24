package serviciosRest;

import java.util.List;

import dao.EstudianteDao;
import entidades.Estudiante;
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

@Path("/estudiantes")
public class EstudianteRest {
	//Traer los estudientes ordenados(asc) por el apellido
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Estudiante> getEstudiante(){
		return EstudianteDao.getInstance().findAll();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Estudiante getEstudianteById(@PathParam("id") String msg) {
		int id = Integer.valueOf(msg);
		Estudiante e = EstudianteDao.getInstance().findById(id);
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
		Estudiante e = EstudianteDao.getInstance().findByNroLibreta(nroLibreta);
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
		Estudiante e = EstudianteDao.getInstance().findByGenero(msg);
		if(e != null) {
			return e;			
		}
		else {
			throw new RecursoNoExisteGenero(msg);
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createEstudiante(Estudiante e) {
		Estudiante result=EstudianteDao.getInstance().persist(e);
	
		if(result==null) {
			throw new RecursoDuplicado(e.getDni());
		}else {
			return Response.status(201).entity(e).build();
		}
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteEstudiante(@PathParam("id") int id) {
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
	public Response updateEstudiante(@PathParam("id") int id, Estudiante e) {
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
