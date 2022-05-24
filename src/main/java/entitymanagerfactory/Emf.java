package entitymanagerfactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class Emf implements ServletContextListener{
	
	private static EntityManagerFactory emf;
	
	public void contextInitialized(ServletContextEvent arg0) {
		emf = Persistence.createEntityManagerFactory("sql");
	}
	
	public void contextDestroyed(ServletContextEvent arg0) {
		emf.close();
	}
	
	public static EntityManager createEntityManager() {
		if(emf == null) {
			throw new IllegalStateException("Context is not initialized yet.");
		}
		return emf.createEntityManager();
	}
	
	
}
