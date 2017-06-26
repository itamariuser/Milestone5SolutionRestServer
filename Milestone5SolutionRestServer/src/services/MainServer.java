package services;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/")
public class MainServer extends ResourceConfig {
	public MainServer() {
		super(SolutionService.class);
	}
}