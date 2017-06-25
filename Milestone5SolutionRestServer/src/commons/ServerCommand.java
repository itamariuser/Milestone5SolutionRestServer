package commons;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="ServerCommands")
public class ServerCommand {
	@Id
	@Column(name="ServerCommandID")
	private Integer ServerCommandID;
	
	@Column(name="Description",nullable=true)
	String description;
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof ServerCommand)
		{
			return false;
		}
		return ((ServerCommand)obj).ServerCommandID.equals(this.ServerCommandID);
	}
}
