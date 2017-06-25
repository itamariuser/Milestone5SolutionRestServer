package services;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import commons.Level2D;
import commons.ServerPlan;
import database.SolutionDBManager;

@Path("hello")
public class SolutionService {
	
	SolutionDBManager dbManager;
	public SolutionService() {
		dbManager=SolutionDBManager.getInstance();
	}
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
    @Path("/getSolution")//TODO: Export commons from JavaProj (because Level2D is mapped to xml there)
	@Consumes(MediaType.TEXT_XML)
    @Produces(MediaType.TEXT_XML)
    public ServerPlan getServerPlan(Level2D level){
		List<ServerPlan> plans=dbManager.getPlanForLevelName(level.getName());
		if(plans.isEmpty())
		{
			return null;
		}
		return plans.get(0);
    }
	
	@PUT
	@Path("/newSolution")//TODO: How to send a serverPlan through http?
	@Consumes(MediaType.TEXT_XML)
	public void putNewServerPlan(ServerPlan plan)
	{
		dbManager.addServerPlan(plan);
	}
	
}