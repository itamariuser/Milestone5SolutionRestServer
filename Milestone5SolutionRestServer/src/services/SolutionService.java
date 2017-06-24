package services;

import java.util.LinkedList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import algorithm.Plan;
import commons.Level2D;
import commons.ServerPlan;
import model.data.Position2D;

@Path("hello")
public class SolutionService {
	@GET
	public String getMessage() {
		return "Hello World!!";
	}
	
	/*@GET
    @Path("/getSolution/{word}")
	@Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_XML)
    public ServerPlan sayHello(@PathParam("word") Level2D level){
		//if not in DB, return empty plan
        return new ServerPlan(new LinkedList<>());
    }*/
	
	@GET
    @Path("/getSolution")//TODO: How to send a level through http?
	@Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_XML)
    public ServerPlan sayHello(Level2D level){
		//if not in DB, return empty plan
        return new ServerPlan(new LinkedList<>());
    }
	
	@PUT
	@Path("/newSolution")
	public void putNewSolution(ServerPlan plan,Level2D forLevel)
	{
		//Insert plan for the level to DB
	}
	
}