package client;

import java.util.LinkedList;

import common.Level2D;
import commons.RESTClient;
import commons.ServerCommand;
import commons.ServerPlan;

public class MainClient {
	
	/**
	 * An example on using the client for REST server
	 * 
	 */
    public static void main(String[] args) {
    	RESTClient res=RESTClient.getInstance();
    	//Send a plan
    	ServerPlan planSent=new ServerPlan();
		LinkedList<ServerCommand> commands=new LinkedList<>();
		commands.add(new ServerCommand("Move left"));
		planSent.setCommands(commands);
		planSent.setLevelName("level 4");
    	try {
			res.sendPlanToDB(planSent);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
    	
    	//Receive a plan and print its commands to the screen
    	ServerPlan planReceived=new ServerPlan();
    	try {
    		Level2D level=new Level2D();
    		level.setName("level 3");
    		planReceived=res.getPlanForLevelName(level.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
    	for (ServerCommand serverCommand : planReceived.getCommands()) {
    		System.out.println(serverCommand.getDescription());
		}
    }
}