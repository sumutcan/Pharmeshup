package main.classes.threads;


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


	
	
}
