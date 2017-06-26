package commons;


import java.util.LinkedList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity(name="ServerPlans")
public class ServerPlan {
	@OneToMany
	@JoinColumn(name="ServerCommandID", nullable=false)
	private LinkedList<ServerCommand> commands;
	
	public ServerPlan() {
		// TODO Auto-generated constructor stub
	}
	
	
	@Column(name="LevelName")
	private String levelName;
	
	
	@Column(name="ServerCommandID")
	private Integer serverCommandID;
	
	public ServerPlan(LinkedList<ServerCommand> commands) {
		super();
		this.commands = commands;
	}

	public LinkedList<ServerCommand> getCommands() {
		return commands;
	}

	public void setCommands(LinkedList<ServerCommand> commands) {
		this.commands = commands;
	}

	@Override
	public boolean equals(Object obj) {
		boolean eq = true;
		if (obj instanceof ServerPlan) {
			for (ServerCommand c : ((ServerPlan) obj).commands) {
				if (this.commands.contains(c))
					eq = false;
			}
		}
		return eq;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public Integer getServerCommandID() {
		return serverCommandID;
	}

	public void setServerCommandID(Integer serverCommandID) {
		this.serverCommandID = serverCommandID;
	}
	
	

}
