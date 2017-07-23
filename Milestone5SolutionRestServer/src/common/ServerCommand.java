package common;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;


/**
 * 
 * A single command, to be exchanged between the user and the server.
 *
 */
@XmlRootElement
@SuppressWarnings("serial")
public class ServerCommand implements Serializable {	
	private String description;
	public ServerCommand() {
		this.description="DEFAULT COMMAND";
	}
	
	public ServerCommand(String description) {
		super();
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public boolean equals(Object obj) {
		return this.description.equals(((ServerCommand)obj).description);
	}
	
}
