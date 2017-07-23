package services;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import commons.ServerPlan;
import database.SolutionDBManager;

/**
 * A class for the REST service through HTTP.
 *
 */
@Path("planDB")
public class SolutionService {
	
	private SolutionDBManager dbManager;
	public SolutionService() {
		dbManager=SolutionDBManager.getInstance();
	}
	
	/**
	 * 
	 * @param levelName
	 * @return An HTTP response with status 200, containing a plan for the level or an empty plan if none was found, or an HTTP response with status 400 if level name is null.
	 */
	@PUT
	@Path("getPlanForLevel")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public Response getPlanForLevel(String levelName) {
		if(levelName.equals(null))
		{
			return Response.status(400).build();//Bad Request
		}
		ServerPlan plan;
		plan = dbManager.getPlanForLevelName(levelName);
		System.out.println();
		return Response.status(200).entity(plan).build();//Successful, returning server plan

	}

	/**
	 * Insert a plan into the DB.
	 * @param plan
	 * @return An HTTP response with status of:
	 * 200 - if the insertion was successful.
	 * 400 - if the plan doesn't contain a level name.
	 * 409 - if the insertion failed.
	 */
	@PUT
	@Path("putNewPlan")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML )
	public Response putNewPlan(ServerPlan plan)
	{
		if(plan.getLevelName().equals(null))
		{
			return Response.status(400).build();//Bad Request
		}
		try {
			dbManager.addServerPlan(plan);
		} catch (Exception e) {
			return Response.status(409).build();//Conflict: Couldn't add to DB
		}
		return Response.status(200).build();
	}
	
}