package serviciosRest;

import java.util.ArrayList;

import dto.DTOdao;
import dto.Registro;
import jakarta.servlet.http.HttpServlet;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/reporte")
public class DtoRest extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Registro> getRegistros(){
		DTOdao.getInstance().crearReporte();
		return DTOdao.getInstance().getRegistros();
	}

}
