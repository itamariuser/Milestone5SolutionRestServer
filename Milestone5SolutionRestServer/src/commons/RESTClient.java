package commons;


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

public class RESTClient {

	private static RESTClient instance;
	
	private ClientConfig config;
	private Client client;
	private URI serverURI;
	
	
	/**
	 * 
	 * The URI of the REST server
	 * @return URI rest server's URI
	 */
	public URI getServerURI() {
		return serverURI;
	}

	public void setServerURI(String newURI) {
		this.serverURI=UriBuilder.fromUri(newURI).build();
	}

	public static RESTClient getInstance()
	{
		if(instance==null)
		{
			instance=new RESTClient();
		}
		return instance;
	}
	
	private RESTClient() {
		setServerURI("http://localhost:8080/Milestone5SolutionRestServer/");
		config = new ClientConfig();
		client = ClientBuilder.newClient(config);
	}
	

	public RESTClient(URI serverURI) {
		super();
		this.serverURI = serverURI;
	}

	/**
	 * Checks in DB if there's a plan for a level with the given name (level names are unique).
	 * returns a plan for solving the level.
	 *
	 * @param levelName - The name of the level.
	 * @return plan for the level, or an empty plan if not found in DB.
	 * @throws Exception - In case any of the level data is missing.
	 */
	public ServerPlan getPlanForLevelName(String levelName) throws Exception{
		WebTarget target = client.target(serverURI).path("planDB").path("getPlanForLevel");

		Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_XML);
		Response response = invocationBuilder.put(Entity.entity(levelName, MediaType.APPLICATION_XML));
		if (response.getStatus() == 200) {
			return response.readEntity(ServerPlan.class);
		} else if (response.getStatus() == 204) {
			return null;
		} else if (response.getStatus() == 400) {
			throw new Exception("Bad Request: Check level data");
		} else
			return null;
	}

	
	/**
	 * Insert a plan in the DB for a specific level.
	 * @param serverPlan - The plan to send.
	 * @return - true if successful, false otherwise.
	 */
	public boolean sendPlanToDB(ServerPlan serverPlan) {
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		
		WebTarget target = client.target(serverURI).path("planDB").path("putNewPlan");

		Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_XML);
		Response response = invocationBuilder.put(Entity.entity(serverPlan, MediaType.APPLICATION_XML));
		if (response.getStatus() == 200) {
			return true;
		} else
			return false;
	}

}
