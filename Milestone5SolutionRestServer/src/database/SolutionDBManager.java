package database;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import commons.ServerCommand;
import commons.ServerPlan;

public class SolutionDBManager {

	private static SolutionDBManager instance;

	File databaseFiles;

	public static SolutionDBManager getInstance() {
		if (instance == null) {
			instance = new SolutionDBManager();
		}
		return instance;
	}

	private SolutionDBManager() {
		databaseFiles = new File("database");
		databaseFiles.mkdir();
	}

	public ServerPlan getPlanForLevelName(String levelName)  {
		File[] files = databaseFiles.listFiles();
		if (files != null) {
			for (File file : files) {
				if (file.getName().equals(levelName)) {
					XMLDecoder xmlDecoder=null;
					try {
						xmlDecoder = new XMLDecoder(new FileInputStream(file));
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
					ServerPlan plan = (ServerPlan) xmlDecoder.readObject();
					xmlDecoder.close();
					return plan;
				}
			}
		}
		return null;
	}

	public void addServerPlan(ServerPlan plan) {

		if ((getPlanForLevelName(plan.getLevelName()) != null)) {
			// clear file
			PrintWriter out=null;
			try {
				out = new PrintWriter(databaseFiles.getPath() + "/" + plan.getLevelName());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			out.write("");
			out.close();
		}
		// write commands in file
		XMLEncoder xmlEncoder=null;
		try {
			File f = new File(databaseFiles.getPath(),plan.getLevelName());
			xmlEncoder = new XMLEncoder(new FileOutputStream(f));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		for (ServerCommand command : plan.getCommands()) {
			xmlEncoder.writeObject(command);
		}
		xmlEncoder.close();

		XMLEncoder encoder=null;
		try {
			encoder = new XMLEncoder(
					new BufferedOutputStream(new FileOutputStream(databaseFiles.getPath() + "/" + plan.getLevelName())));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		encoder.writeObject(plan);
		encoder.close();
	}
}
