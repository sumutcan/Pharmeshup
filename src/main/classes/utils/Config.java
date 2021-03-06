package main.classes.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;


public class Config {

	private static Config instance = null;
	private String configSource =System.getProperty("user.dir")+"/config";
	private Map<String,String> availableEndpoints = new ConcurrentHashMap<String,String>();
	
	public static synchronized Config getInstance()
	{
		if (instance == null)
			instance = new Config();
		
		return instance;
	}
	
	private Config()
	{
		
	}

	public synchronized Date getLastUpdateDate() throws Exception {
		
		try {
			Properties props = new Properties();
			props.clear();
			FileInputStream fis = new FileInputStream(new File(configSource+"/config.properties"));
			props.load(fis);
			SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			
			fis.close();
			
			return df.parse(props.getProperty("lastAccessDate"));
			
		} catch (FileNotFoundException e) {
			
			throw new Exception("Config file cannot be found: "+ e.getMessage());
		}
	}

	public synchronized void setLastUpdateDate(Date currentDate) throws Exception {
		
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		
		try {
			Properties props = new Properties();
			props.clear();
			FileInputStream fis = new FileInputStream(new File(configSource+"/config.properties"));
			props.load(fis);
			fis.close();
			
			props.setProperty("lastAccessDate", df.format(currentDate)); 
			FileOutputStream fos = new FileOutputStream(configSource+"/config.properties");
			props.store(fos, null);
			fos.close();
			
		} catch (FileNotFoundException e) {
			
			throw new Exception("Config file cannot be found: "+ e.getMessage());
		}
		
	}

	public synchronized String getEndpoint(String name) throws Exception {
		
		try {
			Properties props = new Properties();
			props.clear();
			FileInputStream fis = new FileInputStream(new File(configSource+"/endpoints.properties"));
			props.load(fis);
			fis.close();
			return props.getProperty(name);
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new Exception("Config file cannot be found: "+ e.getMessage());
	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new Exception("Error occured when reading endpoint file: "+ e.getMessage());
		}
		
		
	}
	
	public synchronized Hashtable<String, String> getAllEndpoints() throws Exception {
		
		Hashtable<String, String> endpoints = new Hashtable<String, String>();
		Properties props = null;
		try {
			props = new Properties();
			props.clear();
			FileInputStream fis = new FileInputStream(new File(configSource+"/endpoints.properties"));
			props.load(fis);
			fis.close();

		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new Exception("Config file cannot be found: "+ e.getMessage());
	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new Exception("Error occured when reading endpoint file: "+ e.getMessage());
		}
		
		@SuppressWarnings("rawtypes")
		Enumeration keysEnum = props.propertyNames();
		
		while (keysEnum.hasMoreElements())
		{
			String key = (String) keysEnum.nextElement();
			endpoints.put(key, props.getProperty(key));
		}
		
		return endpoints;
		
	}


	public synchronized Hashtable<String, String> getAllPrefixes() throws Exception {
		
		Hashtable<String, String> prefixes = new Hashtable<String, String>();
		Properties props = null;
		try {
			props = new Properties();
			props.clear();
			
			FileInputStream fis = new FileInputStream(new File(configSource+"/prefixes.properties"));
			props.load(fis);
			fis.close();
			
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				throw new Exception("Config file cannot be found: "+ e.getMessage());
		
			} catch (IOException e) {
				// TODO Auto-generated catch block
				throw new Exception("Error occured when reading prefixes file: "+ e.getMessage());
			}
		
		@SuppressWarnings("rawtypes")
		Enumeration keysEnum = props.propertyNames();
		
		while (keysEnum.hasMoreElements())
		{
			String key = (String) keysEnum.nextElement();
			prefixes.put(key, props.getProperty(key));
		}
		
		return prefixes;
		
	}

	public synchronized boolean checkAvailableEndpoints(String endpointName) {
		
		
		return availableEndpoints.containsKey(endpointName);
	}

	public synchronized void addAvailableEndpoint(String key, String value) {
		
		if (!availableEndpoints.containsKey(key))
			availableEndpoints.put(key, value);
		
	}

	public synchronized void removeAvailableEndpoint(String key) {
		
		if (availableEndpoints.containsKey(key))
			availableEndpoints.remove(key);
	}
}
