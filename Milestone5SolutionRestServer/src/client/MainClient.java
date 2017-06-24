package client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.ClientConfig;

import testPckg.Todo;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

public class MainClient {

    public static void main(String[] args) {
        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);

        WebTarget target = client.target(getBaseURI()).path("todo");
        // Get XML
        Todo xmlResponse = target.request()
                .accept(MediaType.TEXT_XML).get(Todo.class);
        // Get XML for application
//        String xmlAppResponse =target.path("todo").request()
//                .accept(MediaType.APPLICATION_XML).get(String.class);

        // For JSON response also add the Jackson libraries to your webapplication
                // In this case you would also change the client registration to
                // ClientConfig config = new ClientConfig().register(JacksonFeature.class);
                // Get JSON for application
                // System.out.println(target.path("rest").path("todo").request()
                // .accept(MediaType.APPLICATION_JSON).get(String.class));

        System.out.println(xmlResponse.getDescription());
//        System.out.println(xmlAppResponse);
    }

    private static URI getBaseURI() {
        return UriBuilder.fromUri(
                "http://localhost:8080/Milestone5SolutionRestServer/").build();
    }

}