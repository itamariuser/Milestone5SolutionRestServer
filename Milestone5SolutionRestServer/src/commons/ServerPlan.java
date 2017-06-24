package commons;

import java.util.LinkedList;

import javax.xml.bind.annotation.XmlRootElement;
import controller.commands.Command;

@XmlRootElement
public class ServerPlan {
	private LinkedList<Command> commands;

	public LinkedList<Command> getCommands() {
		return commands;
	}

	public void setCommands(LinkedList<Command> commands) {
		this.commands = commands;
	}

	@Override
	public boolean equals(Object obj) {
		boolean eq = true;
		if (obj instanceof ServerPlan) {
			for (Command c : ((ServerPlan) obj).commands) {
				if (this.commands.contains(c))
					eq = false;
			}
		}
		return eq;
	}

}
