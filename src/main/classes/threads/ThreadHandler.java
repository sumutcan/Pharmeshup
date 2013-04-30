package main.classes.threads;

import java.io.File;
import java.util.Date;


public class ThreadHandler {

	private static ThreadHandler instance = null;
	
	private ThreadHandler() { }
	
	public static synchronized ThreadHandler getInstance()
	{
		if (instance == null)
			instance = new ThreadHandler();
		
		return instance;
	}
	
	public void startThreads()
	{
		Thread ping = new Thread(new PingEndpointsBackground());
		if (!ping.isAlive())
			ping.start();
		
	}
	public void createIndexFile(File f, Date currentDate)
	{
		Thread t = new Thread(new IndexFileCreatorThread(f, currentDate));
		t.start();
	}


	
	
}
