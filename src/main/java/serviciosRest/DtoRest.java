package serviciosRest;

import dto.DTOdao;
import jakarta.servlet.http.HttpServlet;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/reporte")
public class DtoRest extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRegistros(){
		return Response.status(201).entity(DTOdao.getInstance().crearReporte().toString()).build();
	}

}
