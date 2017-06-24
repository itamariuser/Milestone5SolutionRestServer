package services;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.jdt.internal.compiler.parser.Scanner;

@Path("hello")
public class HelloWorldService {
	@GET
	public String getMessage() {
		return "Hello World!!";
	}
	
	@GET
    @Path("/getvalue/{word}")
	@Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Response sayHello(@PathParam("word") String word){

        String output = " Count of word " + word;
        Scanner s=new Scanner();
        return Response.status(200).entity(s).build();
    }
//	
//	@GET
//	@Path("/am")
//    @Produces( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
//    public Employee getXML() {
//		Employee e = new Employee();
//		e.b=new StringBuilder("SS");
//		e.i=new Integer(2);
//        return e;s
//    } 
	
}