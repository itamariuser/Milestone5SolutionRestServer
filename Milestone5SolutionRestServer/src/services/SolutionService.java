package services;

import java.util.LinkedList;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import commons.ServerCommand;
import commons.ServerPlan;
import database.SolutionDBManager;

@Path("planDB")
public class SolutionService {
	
	private SolutionDBManager dbManager;
	public SolutionService() {
		dbManager=SolutionDBManager.getInstance();
		startPlan1();
		startPlan2();
		startPlan3();
		startPlan4();
	}
	private void startPlan1()
	{
		LinkedList<ServerCommand> list=new LinkedList<>();
		ServerCommand c11=new ServerCommand();
		c11.setDescription("Move right");
		list.add(c11);
		ServerCommand c12=new ServerCommand();
		c12.setDescription("Move right");
		list.add(c12);
		ServerPlan plan1= new ServerPlan();
		plan1.setLevelName("level 1");
		plan1.setCommands(list);
		this.putNewPlan(plan1);
	}
	
	private void startPlan2()
	{
		LinkedList<ServerCommand> list=new LinkedList<>();
		ServerCommand c11=new ServerCommand();
		c11.setDescription("Move right");
		list.add(c11);
		ServerCommand c12=new ServerCommand();
		c12.setDescription("Move right");
		list.add(c12);
		ServerCommand c13=new ServerCommand();
		c13.setDescription("Move right");
		list.add(c13);
		ServerCommand c14=new ServerCommand();
		c14.setDescription("Move right");
		list.add(c14);
		ServerCommand c15=new ServerCommand();
		c15.setDescription("Move right");
		list.add(c15);
		ServerCommand c16=new ServerCommand();
		c16.setDescription("Move up");
		list.add(c16);
		ServerPlan plan1= new ServerPlan();
		plan1.setLevelName("level 2");
		plan1.setCommands(list);
		this.putNewPlan(plan1);
	}
	
	
	
	private void startPlan3()
	{
		LinkedList<ServerCommand> list=new LinkedList<>();
		ServerCommand c11=new ServerCommand();
		c11.setDescription("Move up");
		list.add(c11);
		ServerCommand c12=new ServerCommand();
		c12.setDescription("Move left");
		list.add(c12);
		ServerCommand c13=new ServerCommand();
		c13.setDescription("Move up");
		list.add(c13);
		ServerCommand c14=new ServerCommand();
		c14.setDescription("Move right");
		list.add(c14);
		ServerPlan plan1= new ServerPlan();
		plan1.setLevelName("level 3");
		plan1.setCommands(list);
		this.putNewPlan(plan1);
	}
	private void startPlan4()
	{
		LinkedList<ServerCommand> list=new LinkedList<>();
		ServerCommand c11=new ServerCommand();
		c11.setDescription("Move up");
		list.add(c11);
		ServerCommand c12=new ServerCommand();
		c12.setDescription("Move right");
		list.add(c12);
		ServerCommand c13=new ServerCommand();
		c13.setDescription("Move right");
		list.add(c13);
		ServerCommand c14=new ServerCommand();
		c14.setDescription("Move right");
		list.add(c14);
		ServerCommand c15=new ServerCommand();
		c15.setDescription("Move up");
		list.add(c15);
		ServerCommand c16=new ServerCommand();
		c16.setDescription("Move right");
		list.add(c16);
		ServerCommand c17=new ServerCommand();
		c17.setDescription("Move right");
		list.add(c17);
		ServerCommand c18=new ServerCommand();
		c18.setDescription("Move down");
		list.add(c18);
		ServerCommand c19=new ServerCommand();
		c19.setDescription("Move up");
		list.add(c19);
		ServerCommand c110=new ServerCommand();
		c110.setDescription("Move left");
		list.add(c110);
		ServerCommand c111=new ServerCommand();
		c111.setDescription("Move left");
		list.add(c111);
		ServerCommand c112=new ServerCommand();
		c112.setDescription("Move down");
		list.add(c112);
		ServerCommand c113=new ServerCommand();
		c113.setDescription("Move down");
		list.add(c113);
		ServerCommand c114=new ServerCommand();
		c114.setDescription("Move right");
		list.add(c114);
		ServerCommand c115=new ServerCommand();
		c115.setDescription("Move up");
		list.add(c115);
		ServerPlan plan1= new ServerPlan();
		plan1.setLevelName("level 4");
		plan1.setCommands(list);
		this.putNewPlan(plan1);
	}
	
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