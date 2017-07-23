package client;

import java.util.LinkedList;

import commons.Level2D;
import commons.RestClient;
import commons.ServerCommand;
import commons.ServerPlan;

public class MainClient {
	
	/**
	 * An example on using the client for REST server
	 * @param args
	 */
    public static void main(String[] args) {
  
    	ServerPlan plan=new ServerPlan(new LinkedList<>());
		LinkedList<ServerCommand> commands=new LinkedList<>();
		commands.add(new ServerCommand("Move right"));
		plan.setCommands(commands);
		plan.setLevelName("level 6");
    	RestClient res=RestClient.getInstance();
    	try {
    		Level2D level=new Level2D();
    		level.setName("level 3");
			plan=res.getPlanForLevelName(level.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
    	for (ServerCommand serverCommand : plan.getCommands()) {
    		System.out.println(serverCommand.getDescription());
		}
    	

    }
}