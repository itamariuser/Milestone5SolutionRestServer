package client;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

import commons.Level2D;
import commons.ServerPlan;

public class RESTclient {

	private static RESTclient instance;
	
	private ClientConfig config;
	private Client client;
	
	public static RESTclient getInstance()
	{
		if(instance==null)
		{
			instance=new RESTclient();
		}
		return instance;
	}
	
	private RESTclient() {
		config = new ClientConfig();
		client = ClientBuilder.newClient(config);
	}

	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:8080/Milestone5SolutionRestServer/").build();
	}

	public ServerPlan getSolutionForLevel(Level2D level) throws Exception {
		
		
		WebTarget target = client.target(getBaseURI()).path("planDB").path("getPlanForLevel");

		Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_XML);
		Response response = invocationBuilder.put(Entity.entity(level, MediaType.APPLICATION_XML));
		if (response.getStatus() == 200) {
			return response.readEntity(ServerPlan.class);
		} else if (response.getStatus() == 204) {
			return null;
		} else if (response.getStatus() == 400) {
			throw new Exception("Bad Request: Check level data");
		} else
			return null;
	}

	public boolean sendPlanToDB(ServerPlan serverPlan) throws Exception {
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		
		WebTarget target = client.target(getBaseURI()).path("planDB").path("putNewPlan");

		Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_XML);
		Response response = invocationBuilder.put(Entity.entity(serverPlan, MediaType.APPLICATION_XML));
		if (response.getStatus() == 200) {
			return true;
		} else
			return false;

	}

}
