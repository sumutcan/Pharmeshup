package main.classes.threads;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


import main.classes.datasets.ADataSet;
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
	
	public void retrieve(ADataSet... datasets) throws Exception
	{
		
		ExecutorService service = Executors.newFixedThreadPool(datasets.length);
		ArrayList<Future<Object>> futures = new ArrayList<Future<Object>>();
		for (ADataSet dataset : datasets)
			futures.add(service.submit(new DataRetriever(dataset)));
		
		for (Future<Object> future: futures)
		{
			try
			{
				 if (!future.isDone())
					future.get(3,TimeUnit.SECONDS);
			}
			catch (TimeoutException timeout)
			{

			}
		}

	}

	public void construct(ADataSet... datasets) throws Exception {
		// TODO Auto-generated method stub
		
		for (ADataSet dataset : datasets)
		{
			dataset.constructData();
		}
		
	}

	
	
}
