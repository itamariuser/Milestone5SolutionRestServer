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
	Client client;
	private String baseUriString;

	public RESTclient() {
		
	}

	private URI getBaseURI() {
		return UriBuilder.fromUri(baseUriString).build();
	}

	public ServerPlan requestSolutionForLevel(Level2D level) throws Exception {
		ClientConfig config = new ClientConfig();
		client = ClientBuilder.newClient(config);
		baseUriString = "http://localhost:8080/Milestone5SolutionRestServer/";
		
		WebTarget target = client.target(getBaseURI()).path("planDB").path("getPlanForLevel");

		Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(level, MediaType.APPLICATION_JSON));
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
		client = ClientBuilder.newClient(config);
		baseUriString = "http://localhost:8080/Milestone5SolutionRestServer/";
		
		WebTarget target = client.target(getBaseURI()).path("planDB").path("putNewPlan");

		Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(serverPlan, MediaType.APPLICATION_JSON));
		if (response.getStatus() == 200) {
			return true;
		} else
			return false;

	}

}
