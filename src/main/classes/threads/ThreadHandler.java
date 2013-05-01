package main.classes.threads;

import java.io.File;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;

import main.classes.utils.Config;


public class ThreadHandler {

	private static ThreadHandler instance = null;
	
	private ThreadHandler() { }
	
	public static synchronized ThreadHandler getInstance()
	{
		if (instance == null)
			instance = new ThreadHandler();
		
		return instance;
	}
	
	public void pingEndpoints() throws Exception
	{
		Hashtable<String, String> endpoints =  Config.getInstance().getAllEndpoints();
		Enumeration<String> keysEnum = endpoints.keys();
		
		while (keysEnum.hasMoreElements()) {
			
			String key = keysEnum.nextElement();
			String value = endpoints.get(key);
			Thread ping = new Thread(new PingEndpointsBackground(key,value));
			if (!ping.isAlive())
				ping.start();
		}
	}
	public void createIndexFile(File f, Date currentDate)
	{
		Thread t = new Thread(new IndexFileCreatorThread(f, currentDate));
		t.start();
	}


	
	
}
