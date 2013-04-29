package main.classes.threads;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import main.classes.Config;

public class PingEndpointsBackground implements Runnable {

	@Override
	public void run() {
		
		Hashtable<String, String> endpoints = null;
		try {
			endpoints = Config.getInstance().getAllEndpoints();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Enumeration<String> keysEnum = endpoints.keys();
		
		while (keysEnum.hasMoreElements())
		{
			String key = keysEnum.nextElement();
			String value = endpoints.get(key);
			if(endpointCheckerService(value))
				Config.getInstance().addAvailableEndpoint(key, value);
			else
				Config.getInstance().removeAvailableEndpoint(key);
			
			
		}
		
		
		
	}
	private boolean endpointCheckerService(String value) {
		ExecutorService service = Executors.newSingleThreadExecutor();
		Future<?> f = service.submit(new PingEndpointsThread(value));
		try {
			f.get(10,TimeUnit.SECONDS);
			
			return true;
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			return false;
		}
		return false;
	}

}
