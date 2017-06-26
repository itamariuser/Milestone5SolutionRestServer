package client;

import java.net.URI;
import java.util.LinkedList;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

import commons.ServerPlan;
import database.SolutionDBManager;

public class MainClient {
    public static void main(String[] args) {
    	//Emulate a client
//        ClientConfig config = new ClientConfig();
//        Client client = ClientBuilder.newClient(config);
//        WebTarget target = client.target(getBaseURI()).path("solution").path("getPlanForLevel");
//        Invocation.Builder invocationBuilder =  target.request(MediaType.APPLICATION_XML);
//        Response response = invocationBuilder.post(Entity.entity("STRINGU", MediaType.APPLICATION_XML));
//        System.out.println(response.getStatus());
//        System.out.println(response.readEntity(String.class));
        //End of emulation
        
//        RESTclient client=new RESTclient();
//        TextLevel2DLoader loader=new TextLevel2DLoader();
//        Level2D level=null;
//		try {
//			level = (Level2D)(loader.loadLevelFromStream(new FileInputStream(new File("C:/Users/shefferit/Desktop/ddsd.txt"))));
//			ServerPlan p=new ServerPlan(new LinkedList<>());
//			p.setLevelName("SS");
//			boolean bool=client.sendPlanToDB(p);
//			System.out.println(bool);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    	SolutionDBManager dbm=SolutionDBManager.getInstance();
		
		ServerPlan p=new ServerPlan(new LinkedList<>());
		p.setLevelName("SS");
		
		
		Client zc;
		ClientConfig config = new ClientConfig();
		zc = ClientBuilder.newClient(config);
		WebTarget target = zc.target(getBaseURI()).path("planDB").path("putNewPlan");

		Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_XML);
		Response response = invocationBuilder.put(Entity.entity(p, MediaType.APPLICATION_XML));
		if (response.getStatus() == 200) {
			System.out.println(true);
		} else
			System.out.println(false);

		
        
//      // Get XML
//      Todo xmlResponse = target.request()
//              .accept(MediaType.TEXT_XML).get(Todo.class);
        
        // Get XML for application
//        String xmlAppResponse =target.path("todo").request()
//                .accept(MediaType.APPLICATION_XML).get(String.class);

        // For JSON response also add the Jackson libraries to your webapplication
                // In this case you would also change the client registration to
                // ClientConfig config = new ClientConfig().register(JacksonFeature.class);
                // Get JSON for application
                // System.out.println(target.path("rest").path("todo").request()
                // .accept(MediaType.APPLICATION_JSON).get(String.class));

//        System.out.println(xmlResponse.getDescription());
//        System.out.println(xmlAppResponse);
    }

    private static URI getBaseURI() {
        return UriBuilder.fromUri(
                "http://localhost:8080/Milestone5SolutionRestServer/").build();
    }

}