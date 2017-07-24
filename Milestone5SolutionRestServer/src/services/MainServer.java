package services;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;


/**
 * A class for the initialization of the REST server.
 *
 */
@ApplicationPath("/")
public class MainServer extends ResourceConfig {
	public MainServer() {
		super(SolutionService.class);
		System.out.println("Server is running at 'http://localhost:8080/Milestone5SolutionRestServer/'");
	}
}