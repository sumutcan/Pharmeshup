package main.classes.threads;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import main.classes.utils.Config;



public class PingEndpointsBackground implements Runnable {

	private String endpoint;
	private String key;
	public PingEndpointsBackground(String key, String value) {
		
		this.endpoint = value;
		this.key = key;
	}
	@Override
	public void run() {
		
			
			if(endpointCheckerService(endpoint))
				Config.getInstance().addAvailableEndpoint(key, endpoint);
			else
				Config.getInstance().removeAvailableEndpoint(key);
			
			
		
		
		
		
	}
	private boolean endpointCheckerService(String value) {
		ExecutorService service = Executors.newSingleThreadExecutor();
		Future<?> f = service.submit(new PingEndpointsThread(value));
		try {
			f.get(5,TimeUnit.SECONDS);
			
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
