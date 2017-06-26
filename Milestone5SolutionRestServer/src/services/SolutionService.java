package services;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import commons.Level2D;
import commons.ServerPlan;
import database.SolutionDBManager;

@Path("planDB")
public class SolutionService {
	
	SolutionDBManager dbManager;
	public SolutionService() {
//		dbManager=SolutionDBManager.getInstance();
	}

	@GET
	@Path("getPlanForLevel")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPlanForLevel(Level2D level) {
		if(level.getName().equals(null))
		{
			return Response.status(400).build();//Bad Request
		}
		List<ServerPlan> plans;
		try {
			plans = dbManager.getPlanForLevelName(level.getName());
			if(plans.isEmpty())
			{
				return Response.status(204).build();//No content
			}
		} catch (Exception e) {
			return Response.status(409).build();//Conflict: Couldn't get plans from DB
		}
		
		return Response.status(200).entity(plans.get(0)).build();//Successful, returning server plan

	}

	@PUT
	@Path("putNewPlan")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML )
	public Response putNewServerPlan(ServerPlan plan)
	{
		if(plan.getLevelName().equals(null))
		{
			return Response.status(400).build();//Bad Request
		}
//		try {
//			dbManager.addServerPlan(plan);
//		} catch (Exception e) {
//			return Response.status(409).build();//Conflict: Couldn't add to DB
//		}
		return Response.status(200).build();
	}
	
	//Tests
//	@POST
//	@Path("/stringu")
//	@Consumes(MediaType.TEXT_XML)
//	public Response sendLevelAndGetSolution(String s) {
//		return Response.status(200).entity(s).build();//Successful, returning serve plan
//
//	}
//	
//	@POST
//	@Path("/stringu")
//	@Consumes(MediaType.TEXT_PLAIN)
//	@Produces(MediaType.TEXT_PLAIN)
//	public Response getStringu(String s) {
//		return Response.status(200).entity(s).build();//Successful, returning serve plan
//
//	}
//	
//	@GET
//    @Path("/getSolution")//TODO: Export commons from JavaProj (because Level2D is mapped to xml there)
//	@Consumes(MediaType.TEXT_XML)
//    @Produces(MediaType.TEXT_XML)
//    public ServerPlan getServerPlan(Level2D level){
//		List<ServerPlan> plans=dbManager.getPlanForLevelName(level.getName());
//		if(plans.isEmpty())
//		{
//			return null;
//		}
//		return plans.get(0);
//    }
	//End of tests
}